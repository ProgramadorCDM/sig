package com.cdm.sig.controllers;

import com.cdm.sig.models.EntregaDyE;
import com.cdm.sig.models.integrations.Items;
import com.cdm.sig.services.apis.EntregaDyEServiceAPI;
import com.cdm.sig.services.apis.utils.ItemServiceAPI;
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
@RequestMapping("/api/entregas")
public class EntregaDyERestController {

    private final EntregaDyEServiceAPI serviceAPI;

    private final ItemServiceAPI itemServiceAPI;

    @Autowired
    public EntregaDyERestController(EntregaDyEServiceAPI serviceAPI, ItemServiceAPI itemServiceAPI) {
        this.serviceAPI = serviceAPI;
        this.itemServiceAPI = itemServiceAPI;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<EntregaDyE> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public EntregaDyE find(@PathVariable Long id) {
        return serviceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody EntregaDyE entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        EntregaDyE entity = serviceAPI.get(id);
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

    @PutMapping("/{id}/items/cargar")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> CargarItems(@RequestBody List<Items> items, @PathVariable Long id) {
        EntregaDyE entregaDyE = serviceAPI.get(id);
        if (entregaDyE == null) return ResponseEntity.notFound().build();
        items.forEach(entregaDyE::addItem);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.serviceAPI.save(entregaDyE));
    }

    @PutMapping("/{id}/items/eliminar")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> EliminarItem(@RequestBody Items items, @PathVariable Long id) {
        EntregaDyE entregaDyE = serviceAPI.get(id);
        Items item = itemServiceAPI.get(items.getIdItem());
        if (entregaDyE == null) return ResponseEntity.notFound().build();
        entregaDyE.removeItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceAPI.save(entregaDyE));
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> BuscarEntregaDyEPorEmpleado(@PathVariable String id) {
        List<EntregaDyE> entregaDyES = this.serviceAPI.findEntregaDyEByEmpleado(id);
        return ResponseEntity.ok(entregaDyES);
    }
}
