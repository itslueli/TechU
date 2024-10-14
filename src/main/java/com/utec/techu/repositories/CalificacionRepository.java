package com.utec.techu.repositories;

import com.utec.techu.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByCursoIdCurso(Long cursoId);

    List<Calificacion> findByUsuarioIdUsuario(Long usuarioId);

    List<Calificacion> findByFechaAfter(LocalDate fecha);
}