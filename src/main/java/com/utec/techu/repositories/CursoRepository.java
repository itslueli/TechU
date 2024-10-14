package com.utec.techu.repositories;

import com.utec.techu.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByAreaIdArea(Long areaId);
    List<Curso> findByModalidad(String modalidad);
    List<Curso> findByTituloContainingIgnoreCase(String keyword);
}