package com.utec.techu.dtos;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Long idCurso;

    @NotNull(message = "El título no puede ser nulo")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
    private String titulo;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @Positive(message = "El precio por hora debe ser positivo")
    private double precioPorHora;

    @NotNull(message = "La modalidad no puede ser nula")
    private String modalidad;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;
}