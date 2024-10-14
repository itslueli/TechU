package com.utec.techu.services;

import com.utec.techu.dtos.UsuarioDTO;
import com.utec.techu.entities.Usuario;
import com.utec.techu.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getRol(),
                        usuario.getFechaRegistro()
                ))
                .collect(Collectors.toList());
    }

    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setFechaRegistro(usuarioDTO.getFechaRegistro());
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new UsuarioDTO(
                usuarioGuardado.getIdUsuario(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getRol(),
                usuarioGuardado.getFechaRegistro()
        );
    }

    public UsuarioDTO actualizarUsuario(Long idUsuario, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setFechaRegistro(usuarioDTO.getFechaRegistro());
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new UsuarioDTO(
                usuarioActualizado.getIdUsuario(),
                usuarioActualizado.getNombre(),
                usuarioActualizado.getEmail(),
                usuarioActualizado.getRol(),
                usuarioActualizado.getFechaRegistro()
        );
    }

    public void eliminarUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public List<UsuarioDTO> buscarUsuariosPorRol(String rol) {
        return usuarioRepository.findByRol(rol)
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getRol(),
                        usuario.getFechaRegistro()
                ))
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> buscarUsuariosPorNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getRol(),
                        usuario.getFechaRegistro()
                ))
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getFechaRegistro()
        );
    }

    public List<UsuarioDTO> buscarUsuariosPorLocalidadYRol(String departamento, String rol) {
        return usuarioRepository.findByLocalidadDepartamentoAndRol(departamento, rol)
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getRol(),
                        usuario.getFechaRegistro()
                ))
                .collect(Collectors.toList());
    }

    public boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }
}
