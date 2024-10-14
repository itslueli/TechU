package com.utec.techu.controllers;

import com.utec.techu.dtos.AreaDTO;
import com.utec.techu.entities.Area;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;    

@RestController
@RequestMapping("/api/areas")
public class    AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public ResponseEntity<List<AreaDTO>> obtenerTodasLasAreas() {
        List<AreaDTO> areas = areaRepository.findAll()
                .stream()
                .map(area -> new AreaDTO(
                        area.getIdArea(),
                        area.getNombre()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AreaDTO> crearArea(@RequestBody AreaDTO areaDTO) {
        Area area = new Area();
        area.setNombre(areaDTO.getNombre());
        Area nuevaArea = areaRepository.save(area);
        return new ResponseEntity<>(new AreaDTO(
                nuevaArea.getIdArea(),
                nuevaArea.getNombre()
        ), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArea(@PathVariable Long id) {
        if (!areaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Área no encontrada con el ID: " + id);
        }
        areaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
