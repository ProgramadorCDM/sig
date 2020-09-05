package com.cdm.sig.controllers.utils;

import com.cdm.sig.models.Foto;
import com.cdm.sig.services.apis.FotoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/fotos")
public class FotoRestController {

    private final FotoServiceAPI serviceAPI;

    @Autowired
    public FotoRestController(FotoServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Foto> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Foto find(@PathVariable String id) {
        return serviceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid Foto entity,
                                  @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            entity.setFoto(archivo.getBytes());
            return ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Foto entity = serviceAPI.get(id);
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

    @GetMapping("/image/{id}")
    public ResponseEntity<?> viewPhoto(@PathVariable String id) {
        Foto foto = this.serviceAPI.get(id);
        if (foto == null || foto.getFoto() == null) {

            Foto defaultPhoto = serviceAPI.get("0");
            Resource imagen = new ByteArrayResource(defaultPhoto.getFoto());

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);

        }
        Resource imagen = new ByteArrayResource(foto.getFoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }
}
