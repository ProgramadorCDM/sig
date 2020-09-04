package com.cdm.sig.controllers;

import com.cdm.sig.models.Capacitacion;
import com.cdm.sig.models.Empleado;
import com.cdm.sig.services.apis.CapacitacionServiceAPI;
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
@RequestMapping("/api/capacitaciones")
public class CapacitacionRestController {

    private final CapacitacionServiceAPI serviceAPI;

    @Autowired
    public CapacitacionRestController(CapacitacionServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Capacitacion> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Capacitacion find(@PathVariable Long id) {
        return serviceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Capacitacion entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Capacitacion entity = serviceAPI.get(id);
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

    @PutMapping("/{id}/asignar-empleados")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> asignarEmpleados(@RequestBody List<Empleado> empleados, @PathVariable Long id) {
        Capacitacion capacitacion = serviceAPI.get(id);
        if (capacitacion == null) return ResponseEntity.notFound().build();
        empleados.forEach(capacitacion::addEmpleado);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI.save(capacitacion));
    }

    @PutMapping("/{id}/eliminar-empleados")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarEmpleados(@RequestBody Empleado empleado, @PathVariable Long id) {
        Capacitacion capacitacion = serviceAPI.get(id);
        if (capacitacion == null) return ResponseEntity.notFound().build();
        capacitacion.removeEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI.save(capacitacion));
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> BuscarPorEmpleado(@PathVariable String id) {
        List<Capacitacion> capacitaciones = this.serviceAPI.findCapacitacionByEmpleados(id);
        return ResponseEntity.ok(capacitaciones);
    }
}
