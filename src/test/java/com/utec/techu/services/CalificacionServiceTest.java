package com.utec.techu.services;

import com.utec.techu.dtos.CalificacionDTO;
import com.utec.techu.entities.Calificacion;
import com.utec.techu.entities.Curso;
import com.utec.techu.entities.Usuario;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.repositories.CalificacionRepository;
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

public class CalificacionServiceTest {

    @Mock
    private CalificacionRepository calificacionRepository;

    @InjectMocks
    private CalificacionService calificacionService;

    private Calificacion calificacion;
    private CalificacionDTO calificacionDTO;

    private Usuario usuario;
    private Curso curso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Usuario de Prueba");

        curso = new Curso();
        curso.setIdCurso(1L);
        curso.setTitulo("Curso de Prueba");

        calificacion = new Calificacion();
        calificacion.setIdCalificacion(1L);
        calificacion.setValor(5);
        calificacion.setFecha(LocalDate.now());
        calificacion.setUsuario(usuario);
        calificacion.setCurso(curso);

        calificacionDTO = new CalificacionDTO();
        calificacionDTO.setIdCalificacion(1L);
        calificacionDTO.setValor(5);
        calificacionDTO.setFecha(LocalDate.now());
        calificacionDTO.setUsuarioId(1L);
        calificacionDTO.setCursoId(1L);
    }

    @Test
    void obtenerTodasLasCalificacionesTest() {
        List<Calificacion> calificaciones = Arrays.asList(calificacion);

        Mockito.when(calificacionRepository.findAll()).thenReturn(calificaciones);

        List<CalificacionDTO> resultado = calificacionService.obtenerTodasLasCalificaciones();

        assertEquals(1, resultado.size());
        assertEquals(5, resultado.get(0).getValor());
    }

    @Test
    void crearCalificacionTest() {
        Mockito.when(calificacionRepository.save(any(Calificacion.class))).thenReturn(calificacion);

        CalificacionDTO resultado = calificacionService.crearCalificacion(calificacionDTO);

        assertNotNull(resultado);
        assertEquals(5, resultado.getValor());
    }

    @Test
    void eliminarCalificacionTest() {
        Mockito.when(calificacionRepository.findById(anyLong())).thenReturn(Optional.of(calificacion));
        Mockito.doNothing().when(calificacionRepository).delete(calificacion);

        assertDoesNotThrow(() -> calificacionService.eliminarCalificacion(1L));
    }

    @Test
    void eliminarCalificacionNotFoundTest() {
        Mockito.when(calificacionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> calificacionService.eliminarCalificacion(1L));
    }
}