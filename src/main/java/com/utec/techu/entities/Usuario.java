package com.utec.techu.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String email;
    private String password;
    private String rol;
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

    @OneToMany(mappedBy = "usuario")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "usuario")
    private List<Calificacion> calificaciones;
}