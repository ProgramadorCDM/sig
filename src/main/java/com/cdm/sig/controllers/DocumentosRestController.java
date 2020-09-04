package com.cdm.sig.controllers;

import com.cdm.sig.models.Documento;
import com.cdm.sig.services.apis.DocumentoServiceAPI;
import com.cdm.sig.services.apis.EmpleadoServiceAPI;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/api/documentos")
public class DocumentosRestController {

    private final DocumentoServiceAPI serviceAPI;
    private final EmpleadoServiceAPI empleadoServiceAPI;

    public DocumentosRestController(DocumentoServiceAPI serviceAPI, EmpleadoServiceAPI empleadoServiceAPI) {
        this.serviceAPI = serviceAPI;
        this.empleadoServiceAPI = empleadoServiceAPI;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Documento> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Documento find(@PathVariable Long id) {
        return serviceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Documento entity, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Documento entity = serviceAPI.get(id);
        if (entity != null) {
            serviceAPI.delete(id);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(
                err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @PostMapping("/save-file/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> guardarDocumento(@Valid Documento entity, BindingResult result,
                                              @RequestParam MultipartFile archivo,
                                              @PathVariable Long id) throws IOException {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Documento documento = serviceAPI.get(id);
        if (documento == null) {
            return ResponseEntity.notFound().build();
        }
        if (!archivo.isEmpty()) {
            documento.setArchivo(archivo.getBytes());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAPI.save(documento));
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> viewPDF(@PathVariable Long id) {
        Documento documento = serviceAPI.get(id);

        if (documento == null) return ResponseEntity.notFound().build();
        if (documento.getArchivo() == null) return ResponseEntity.notFound().build();

        Resource pdf = new ByteArrayResource(documento.getArchivo());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf);
    }

    @GetMapping("/empleado/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> BuscarDocumentoPorEmpleado(@PathVariable String id) {
        List<Documento> documentos = serviceAPI.findDocumentoByEmpleado(id);
        return ResponseEntity.ok(documentos);
    }
}
