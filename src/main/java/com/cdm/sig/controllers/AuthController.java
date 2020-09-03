package com.cdm.sig.controllers;

import com.cdm.sig.core.request.LoginRequest;
import com.cdm.sig.core.request.SignupRequest;
import com.cdm.sig.core.response.JwtResponse;
import com.cdm.sig.core.response.MessageResponse;
import com.cdm.sig.models.Integrations.ERole;
import com.cdm.sig.models.Role;
import com.cdm.sig.models.User;
import com.cdm.sig.repositories.RoleRepository;
import com.cdm.sig.repositories.UserRepository;
import com.cdm.sig.security.jwt.JwtUtils;
import com.cdm.sig.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(
                GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getEmail(),
                roles
        ));
    }

    @PostMapping("signup")
    public ResponseEntity<?> responseEntity(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already in use!"));
        }
        if (userRepository.existsByUsername(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Email is Already in use")
            );
        }

        // Creamos un nuevo usuario
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getName(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        Set<String> srtRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (srtRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            srtRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        break;
                    case "supp":
                        Role suppRole = roleRepository.findByName(ERole.ROLE_SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
