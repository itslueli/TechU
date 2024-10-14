package com.utec.techu.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDTO {
    private Long idCalificacion;

    @NotNull(message = "El valor no puede ser nulo")
    @Min(value = 1, message = "El valor mínimo es 1")
    @Max(value = 5, message = "El valor máximo es 5")
    private int valor;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long usuarioId;

    @NotNull(message = "El ID del curso no puede ser nulo")
    private Long cursoId;
}