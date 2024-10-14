package com.utec.techu.repositories;

import com.utec.techu.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByRol(String rol);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    Usuario findByEmail(String email);
    List<Usuario> findByLocalidadDepartamentoAndRol(String departamento, String rol);
}
