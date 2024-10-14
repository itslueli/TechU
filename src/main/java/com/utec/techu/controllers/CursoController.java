package com.utec.techu.controllers;

import com.utec.techu.dtos.CursoDTO;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CursoDTO>> obtenerTodosLosCursos() {
        List<CursoDTO> cursos = cursoService.obtenerTodosLosCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoDTO> crearCurso(@RequestBody CursoDTO cursoDTO) {
        CursoDTO nuevoCurso = cursoService.crearCurso(cursoDTO);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoDTO> actualizarCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        if (!cursoService.existeCurso(id)) {
            throw new ResourceNotFoundException("Curso con ID " + id + " no encontrado");
        }
        CursoDTO cursoActualizado = cursoService.actualizarCurso(id, cursoDTO);
        return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        if (!cursoService.existeCurso(id)) {
            throw new ResourceNotFoundException("Curso con ID " + id + " no encontrado");
        }
        cursoService.eliminarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarPorArea/{areaId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CursoDTO>> buscarCursosPorArea(@PathVariable Long areaId) {
        List<CursoDTO> cursos = cursoService.buscarCursosPorArea(areaId);
        if (cursos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cursos para el área con ID " + areaId);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/buscarPorModalidad/{modalidad}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CursoDTO>> buscarCursosPorModalidad(@PathVariable String modalidad) {
        List<CursoDTO> cursos = cursoService.buscarCursosPorModalidad(modalidad);
        if (cursos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cursos para la modalidad: " + modalidad);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/buscarPorPalabraClave")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CursoDTO>> buscarCursosPorPalabraClave(@RequestParam String keyword) {
        List<CursoDTO> cursos = cursoService.buscarCursosPorPalabraClave(keyword);
        if (cursos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cursos para la palabra clave: " + keyword);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
}