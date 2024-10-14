package com.utec.techu.services;

import com.utec.techu.dtos.CursoDTO;
import com.utec.techu.entities.Curso;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.repositories.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso curso;
    private CursoDTO cursoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso();
        curso.setIdCurso(1L);
        curso.setTitulo("Curso de Prueba");
        curso.setDescripcion("Descripcion del curso");
        curso.setPrecioPorHora(50.0);
        curso.setModalidad("Online");
        curso.setFecha(LocalDate.now());

        cursoDTO = new CursoDTO();
        cursoDTO.setIdCurso(1L);
        cursoDTO.setTitulo("Curso de Prueba");
        cursoDTO.setDescripcion("Descripcion del curso");
        cursoDTO.setPrecioPorHora(50.0);
        cursoDTO.setModalidad("Online");
        cursoDTO.setFecha(LocalDate.now());
    }

    @Test
    void obtenerTodosLosCursosTest() {
        List<Curso> cursos = Arrays.asList(curso);

        Mockito.when(cursoRepository.findAll()).thenReturn(cursos);

        List<CursoDTO> resultado = cursoService.obtenerTodosLosCursos();

        assertEquals(1, resultado.size());
        assertEquals("Curso de Prueba", resultado.get(0).getTitulo());
    }

    @Test
    void crearCursoTest() {
        Mockito.when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        CursoDTO resultado = cursoService.crearCurso(cursoDTO);

        assertNotNull(resultado);
        assertEquals("Curso de Prueba", resultado.getTitulo());
    }

    @Test
    void actualizarCursoTest() {
        Mockito.when(cursoRepository.findById(anyLong())).thenReturn(Optional.of(curso));
        Mockito.when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        CursoDTO resultado = cursoService.actualizarCurso(1L, cursoDTO);

        assertNotNull(resultado);
        assertEquals("Curso de Prueba", resultado.getTitulo());
    }

    @Test
    void actualizarCursoNotFoundTest() {
        Mockito.when(cursoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cursoService.actualizarCurso(1L, cursoDTO));
    }

    @Test
    void eliminarCursoTest() {
        Mockito.when(cursoRepository.findById(anyLong())).thenReturn(Optional.of(curso));
        Mockito.doNothing().when(cursoRepository).delete(curso);

        assertDoesNotThrow(() -> cursoService.eliminarCurso(1L));
    }

    @Test
    void eliminarCursoNotFoundTest() {
        Mockito.when(cursoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cursoService.eliminarCurso(1L));
    }
}