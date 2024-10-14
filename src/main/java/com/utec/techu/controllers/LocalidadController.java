package com.utec.techu.controllers;

import com.utec.techu.dtos.LocalidadDTO;
import com.utec.techu.entities.Localidad;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadController {

    @Autowired
    private LocalidadRepository localidadRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<LocalidadDTO>> obtenerTodasLasLocalidades() {
        List<LocalidadDTO> localidades = localidadRepository.findAll()
                .stream()
                .map(localidad -> new LocalidadDTO(
                        localidad.getIdLocalidad(),
                        localidad.getProvincia(),
                        localidad.getDepartamento(),
                        localidad.getDistrito()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(localidades, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LocalidadDTO> crearLocalidad(@RequestBody LocalidadDTO localidadDTO) {
        Localidad localidad = new Localidad();
        localidad.setProvincia(localidadDTO.getProvincia());
        localidad.setDepartamento(localidadDTO.getDepartamento());
        localidad.setDistrito(localidadDTO.getDistrito());
        Localidad nuevaLocalidad = localidadRepository.save(localidad);
        return new ResponseEntity<>(new LocalidadDTO(
                nuevaLocalidad.getIdLocalidad(),
                nuevaLocalidad.getProvincia(),
                nuevaLocalidad.getDepartamento(),
                nuevaLocalidad.getDistrito()
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarLocalidad(@PathVariable Long id) {
        if (!localidadRepository.existsById(id)) {
            throw new ResourceNotFoundException("Localidad no encontrada con el ID: " + id);
        }
        localidadRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}