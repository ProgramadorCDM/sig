package com.cdm.sig.controllers;

import com.cdm.sig.models.Examen;
import com.cdm.sig.services.apis.ExamenServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/examenes")
public class ExamenRestController {

    private final ExamenServiceAPI serviceAPI;

    @Autowired
    public ExamenRestController(ExamenServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Examen> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Examen find(@PathVariable Long id) {
        return serviceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Examen entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Examen entity = serviceAPI.get(id);
        if (entity != null) {
            serviceAPI.delete(id);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> BuscarExamenesPorEmpleado(@PathVariable String id) {
        List<Examen> examenes = serviceAPI.findExamenByEmpleado(id);
        return ResponseEntity.ok(examenes);
    }
}
