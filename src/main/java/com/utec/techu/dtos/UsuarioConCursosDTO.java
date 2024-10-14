package com.utec.techu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioConCursosDTO {
    private Long idUsuario;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El email no puede ser nulo")
    @Size(min = 5, max = 100, message = "El email debe tener entre 5 y 100 caracteres")
    private String email;

    @NotNull(message = "El rol no puede ser nulo")
    private String rol;

    private List<CursoDTO> cursos;
}