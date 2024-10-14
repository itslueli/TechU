package com.utec.techu.services;

import com.utec.techu.dtos.CursoDTO;
import com.utec.techu.entities.Curso;
import com.utec.techu.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoDTO> obtenerTodosLosCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(curso -> new CursoDTO(
                        curso.getIdCurso(),
                        curso.getTitulo(),
                        curso.getDescripcion(),
                        curso.getPrecioPorHora(),
                        curso.getModalidad(),
                        curso.getFecha()
                ))
                .collect(Collectors.toList());
    }

    public CursoDTO crearCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setTitulo(cursoDTO.getTitulo());
        curso.setDescripcion(cursoDTO.getDescripcion());
        curso.setPrecioPorHora(cursoDTO.getPrecioPorHora());
        curso.setModalidad(cursoDTO.getModalidad());
        curso.setFecha(cursoDTO.getFecha());
        Curso cursoGuardado = cursoRepository.save(curso);
        return new CursoDTO(
                cursoGuardado.getIdCurso(),
                cursoGuardado.getTitulo(),
                cursoGuardado.getDescripcion(),
                cursoGuardado.getPrecioPorHora(),
                cursoGuardado.getModalidad(),
                cursoGuardado.getFecha()
        );
    }

    public CursoDTO actualizarCurso(Long idCurso, CursoDTO cursoDTO) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setTitulo(cursoDTO.getTitulo());
        curso.setDescripcion(cursoDTO.getDescripcion());
        curso.setPrecioPorHora(cursoDTO.getPrecioPorHora());
        curso.setModalidad(cursoDTO.getModalidad());
        curso.setFecha(cursoDTO.getFecha());
        Curso cursoActualizado = cursoRepository.save(curso);
        return new CursoDTO(
                cursoActualizado.getIdCurso(),
                cursoActualizado.getTitulo(),
                cursoActualizado.getDescripcion(),
                cursoActualizado.getPrecioPorHora(),
                cursoActualizado.getModalidad(),
                cursoActualizado.getFecha()
        );
    }

    public void eliminarCurso(Long idCurso) {
        cursoRepository.deleteById(idCurso);
    }

    public List<CursoDTO> buscarCursosPorArea(Long areaId) {
        return cursoRepository.findByAreaIdArea(areaId)
                .stream()
                .map(curso -> new CursoDTO(
                        curso.getIdCurso(),
                        curso.getTitulo(),
                        curso.getDescripcion(),
                        curso.getPrecioPorHora(),
                        curso.getModalidad(),
                        curso.getFecha()
                ))
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarCursosPorModalidad(String modalidad) {
        return cursoRepository.findByModalidad(modalidad)
                .stream()
                .map(curso -> new CursoDTO(
                        curso.getIdCurso(),
                        curso.getTitulo(),
                        curso.getDescripcion(),
                        curso.getPrecioPorHora(),
                        curso.getModalidad(),
                        curso.getFecha()
                ))
                .collect(Collectors.toList());
    }

    public List<CursoDTO> buscarCursosPorPalabraClave(String keyword) {
        return cursoRepository.findByTituloContainingIgnoreCase(keyword)
                .stream()
                .map(curso -> new CursoDTO(
                        curso.getIdCurso(),
                        curso.getTitulo(),
                        curso.getDescripcion(),
                        curso.getPrecioPorHora(),
                        curso.getModalidad(),
                        curso.getFecha()
                ))
                .collect(Collectors.toList());
    }

    public boolean existeCurso(Long id) {
        return cursoRepository.existsById(id);
    }
}