package com.utec.techu.repositories;

import com.utec.techu.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByUsuarioIdUsuario(Long usuarioId);
    List<Publicacion> findByFechaAfter(LocalDate fecha);
}
