package com.utec.techu.controllers;

import com.utec.techu.dtos.PublicacionDTO;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.services.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PublicacionDTO>> obtenerTodasLasPublicaciones() {
        List<PublicacionDTO> publicaciones = publicacionService.obtenerTodasLasPublicaciones();
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublicacionDTO> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO nuevaPublicacion = publicacionService.crearPublicacion(publicacionDTO);
        return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorUsuario/{usuarioId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PublicacionDTO>> buscarPublicacionesPorUsuario(@PathVariable Long usuarioId) {
        List<PublicacionDTO> publicaciones = publicacionService.buscarPublicacionesPorUsuario(usuarioId);
        if (publicaciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron publicaciones para el usuario con ID: " + usuarioId);
        }
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/buscarRecientes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PublicacionDTO>> buscarPublicacionesRecientes(@RequestParam LocalDate fecha) {
        List<PublicacionDTO> publicaciones = publicacionService.buscarPublicacionesRecientes(fecha);
        if (publicaciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron publicaciones recientes a partir de la fecha: " + fecha);
        }
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        if (!publicacionService.existePublicacion(id)) {
            throw new ResourceNotFoundException("Publicación no encontrada con el ID: " + id);
        }
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
