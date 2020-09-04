package com.cdm.sig.controllers;

import com.cdm.sig.core.request.LoginRequest;
import com.cdm.sig.core.request.SignupRequest;
import com.cdm.sig.core.response.JwtResponse;
import com.cdm.sig.core.response.MessageResponse;
import com.cdm.sig.models.Role;
import com.cdm.sig.models.User;
import com.cdm.sig.models.integrations.ERole;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthController {
    final
    AuthenticationManager authenticationManager;

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder encoder;

    final
    JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        if (result.hasErrors()) return this.validar(result);
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
    public ResponseEntity<?> responseEntity(@Valid @RequestBody SignupRequest signupRequest, BindingResult result) {
        if (result.hasErrors()) return this.validar(result);
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: El Usuario ya Existe!"));
        }
        if (userRepository.existsByUsername(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Este Email ya esta en uso")
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
        return ResponseEntity.ok(new MessageResponse("Usuario Registrado Con Exito!"));
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
