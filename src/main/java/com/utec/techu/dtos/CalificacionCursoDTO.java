package com.utec.techu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionCursoDTO {
    @NotNull(message = "El ID del curso no puede ser nulo")
    private Long cursoId;

    @NotNull(message = "Las calificaciones no pueden ser nulas")
    private List<CalificacionDTO> calificaciones;
}