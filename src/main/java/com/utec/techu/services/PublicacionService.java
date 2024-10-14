package com.utec.techu.services;

import com.utec.techu.dtos.PublicacionDTO;
import com.utec.techu.entities.Curso;
import com.utec.techu.entities.Publicacion;
import com.utec.techu.entities.Usuario;
import com.utec.techu.repositories.CursoRepository;
import com.utec.techu.repositories.PublicacionRepository;
import com.utec.techu.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
        return publicacionRepository.findAll()
                .stream()
                .map(publicacion -> new PublicacionDTO(
                        publicacion.getIdPublicacion(),
                        publicacion.getFecha(),
                        publicacion.getUsuario().getIdUsuario(),
                        publicacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = new Publicacion();
        publicacion.setFecha(publicacionDTO.getFecha());

        Usuario usuario = usuarioRepository.findById(publicacionDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(publicacionDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        publicacion.setUsuario(usuario);
        publicacion.setCurso(curso);

        Publicacion publicacionGuardada = publicacionRepository.save(publicacion);
        return new PublicacionDTO(
                publicacionGuardada.getIdPublicacion(),
                publicacionGuardada.getFecha(),
                publicacionGuardada.getUsuario().getIdUsuario(),
                publicacionGuardada.getCurso().getIdCurso()
        );
    }

    public List<PublicacionDTO> buscarPublicacionesPorUsuario(Long usuarioId) {
        return publicacionRepository.findByUsuarioIdUsuario(usuarioId)
                .stream()
                .map(publicacion -> new PublicacionDTO(
                        publicacion.getIdPublicacion(),
                        publicacion.getFecha(),
                        publicacion.getUsuario().getIdUsuario(),
                        publicacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public List<PublicacionDTO> buscarPublicacionesRecientes(LocalDate fecha) {
        return publicacionRepository.findByFechaAfter(fecha)
                .stream()
                .map(publicacion -> new PublicacionDTO(
                        publicacion.getIdPublicacion(),
                        publicacion.getFecha(),
                        publicacion.getUsuario().getIdUsuario(),
                        publicacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public void eliminarPublicacion(Long idPublicacion) {
        publicacionRepository.deleteById(idPublicacion);
    }

    public boolean existePublicacion(Long id) {
        return publicacionRepository.existsById(id);
    }
}