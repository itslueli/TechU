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
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArea;

    private String nombre;

    @OneToMany(mappedBy = "area")
    private List<Curso> cursos;
}