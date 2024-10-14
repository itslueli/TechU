package com.utec.techu.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "localidad")
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalidad;

    private String provincia;
    private String departamento;
    private String distrito;

    @OneToMany(mappedBy = "localidad")
    private List<Usuario> usuarios;
}