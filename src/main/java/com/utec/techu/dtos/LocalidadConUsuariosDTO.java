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
public class LocalidadConUsuariosDTO {
    private Long idLocalidad;

    @NotNull(message = "La provincia no puede ser nula")
    @Size(min = 2, max = 100, message = "La provincia debe tener entre 2 y 100 caracteres")
    private String provincia;

    @NotNull(message = "El departamento no puede ser nulo")
    @Size(min = 2, max = 100, message = "El departamento debe tener entre 2 y 100 caracteres")
    private String departamento;

    @NotNull(message = "El distrito no puede ser nulo")
    @Size(min = 2, max = 100, message = "El distrito debe tener entre 2 y 100 caracteres")
    private String distrito;

    private List<UsuarioDTO> usuarios;
}