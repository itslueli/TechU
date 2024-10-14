package com.utec.techu.controllers;

import com.utec.techu.dtos.CalificacionDTO;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CalificacionDTO>> obtenerTodasLasCalificaciones() {
        List<CalificacionDTO> calificaciones = calificacionService.obtenerTodasLasCalificaciones();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CalificacionDTO> crearCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        CalificacionDTO nuevaCalificacion = calificacionService.crearCalificacion(calificacionDTO);
        return new ResponseEntity<>(nuevaCalificacion, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorCurso/{cursoId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CalificacionDTO>> buscarCalificacionesPorCurso(@PathVariable Long cursoId) {
        List<CalificacionDTO> calificaciones = calificacionService.buscarCalificacionesPorCurso(cursoId);
        if (calificaciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron calificaciones para el curso con ID: " + cursoId);
        }
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/buscarPorUsuario/{usuarioId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CalificacionDTO>> buscarCalificacionesPorUsuario(@PathVariable Long usuarioId) {
        List<CalificacionDTO> calificaciones = calificacionService.buscarCalificacionesPorUsuario(usuarioId);
        if (calificaciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron calificaciones para el usuario con ID: " + usuarioId);
        }
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/buscarRecientes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CalificacionDTO>> buscarCalificacionesRecientes(@RequestParam LocalDate fecha) {
        List<CalificacionDTO> calificaciones = calificacionService.buscarCalificacionesRecientes(fecha);
        if (calificaciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron calificaciones recientes a partir de la fecha: " + fecha);
        }
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCalificacion(@PathVariable Long id) {
        if (!calificacionService.existeCalificacion(id)) {
            throw new ResourceNotFoundException("Calificación no encontrada con el ID: " + id);
        }
        calificacionService.eliminarCalificacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}