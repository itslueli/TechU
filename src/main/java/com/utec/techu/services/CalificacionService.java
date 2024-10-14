package com.utec.techu.services;

import com.utec.techu.dtos.CalificacionDTO;
import com.utec.techu.entities.Calificacion;
import com.utec.techu.entities.Curso;
import com.utec.techu.entities.Usuario;
import com.utec.techu.repositories.CalificacionRepository;
import com.utec.techu.repositories.CursoRepository;
import com.utec.techu.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<CalificacionDTO> obtenerTodasLasCalificaciones() {
        return calificacionRepository.findAll()
                .stream()
                .map(calificacion -> new CalificacionDTO(
                        calificacion.getIdCalificacion(),
                        calificacion.getValor(),
                        calificacion.getFecha(),
                        calificacion.getUsuario().getIdUsuario(),
                        calificacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public CalificacionDTO crearCalificacion(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = new Calificacion();
        calificacion.setValor(calificacionDTO.getValor());
        calificacion.setFecha(calificacionDTO.getFecha());

        Usuario usuario = usuarioRepository.findById(calificacionDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(calificacionDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        calificacion.setUsuario(usuario);
        calificacion.setCurso(curso);

        Calificacion calificacionGuardada = calificacionRepository.save(calificacion);
        return new CalificacionDTO(
                calificacionGuardada.getIdCalificacion(),
                calificacionGuardada.getValor(),
                calificacionGuardada.getFecha(),
                calificacionGuardada.getUsuario().getIdUsuario(),
                calificacionGuardada.getCurso().getIdCurso()
        );
    }

    public List<CalificacionDTO> buscarCalificacionesPorCurso(Long cursoId) {
        return calificacionRepository.findByCursoIdCurso(cursoId)
                .stream()
                .map(calificacion -> new CalificacionDTO(
                        calificacion.getIdCalificacion(),
                        calificacion.getValor(),
                        calificacion.getFecha(),
                        calificacion.getUsuario().getIdUsuario(),
                        calificacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public List<CalificacionDTO> buscarCalificacionesPorUsuario(Long usuarioId) {
        return calificacionRepository.findByUsuarioIdUsuario(usuarioId)
                .stream()
                .map(calificacion -> new CalificacionDTO(
                        calificacion.getIdCalificacion(),
                        calificacion.getValor(),
                        calificacion.getFecha(),
                        calificacion.getUsuario().getIdUsuario(),
                        calificacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public List<CalificacionDTO> buscarCalificacionesRecientes(LocalDate fecha) {
        return calificacionRepository.findByFechaAfter(fecha)
                .stream()
                .map(calificacion -> new CalificacionDTO(
                        calificacion.getIdCalificacion(),
                        calificacion.getValor(),
                        calificacion.getFecha(),
                        calificacion.getUsuario().getIdUsuario(),
                        calificacion.getCurso().getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public void eliminarCalificacion(Long idCalificacion) {
        calificacionRepository.deleteById(idCalificacion);
    }

    public boolean existeCalificacion(Long id) {
        return calificacionRepository.existsById(id);
    }
}