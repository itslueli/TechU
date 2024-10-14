package com.utec.techu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utec.techu.dtos.CursoDTO;
import com.utec.techu.services.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    private CursoDTO cursoDTO;

    @BeforeEach
    void setUp() {
        cursoDTO = new CursoDTO();
        cursoDTO.setIdCurso(1L);
        cursoDTO.setTitulo("Curso de Prueba");
        cursoDTO.setDescripcion("Descripcion del curso");
        cursoDTO.setPrecioPorHora(50.0);
        cursoDTO.setModalidad("Online");
        cursoDTO.setFecha(java.time.LocalDate.now());
    }

    @Test
    void obtenerTodosLosCursosTest() throws Exception {
        List<CursoDTO> cursos = Arrays.asList(cursoDTO);

        Mockito.when(cursoService.obtenerTodosLosCursos()).thenReturn(cursos);

        mockMvc.perform(get("/api/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Curso de Prueba"));
    }

    @Test
    void crearCursoTest() throws Exception {
        Mockito.when(cursoService.crearCurso(any(CursoDTO.class))).thenReturn(cursoDTO);

        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cursoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Curso de Prueba"));
    }

    @Test
    void actualizarCursoTest() throws Exception {
        Mockito.when(cursoService.existeCurso(anyLong())).thenReturn(true);
        Mockito.when(cursoService.actualizarCurso(anyLong(), any(CursoDTO.class))).thenReturn(cursoDTO);

        mockMvc.perform(put("/api/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cursoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Curso de Prueba"));
    }

    @Test
    void eliminarCursoTest() throws Exception {
        Mockito.when(cursoService.existeCurso(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/cursos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarCursosPorModalidadTest() throws Exception {
        List<CursoDTO> cursos = Arrays.asList(cursoDTO);

        Mockito.when(cursoService.buscarCursosPorModalidad("Online")).thenReturn(cursos);

        mockMvc.perform(get("/api/cursos/buscarPorModalidad/Online"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].modalidad").value("Online"));
    }

    @Test
    void buscarCursosPorPalabraClaveTest() throws Exception {
        List<CursoDTO> cursos = Arrays.asList(cursoDTO);

        Mockito.when(cursoService.buscarCursosPorPalabraClave("Prueba")).thenReturn(cursos);

        mockMvc.perform(get("/api/cursos/buscarPorPalabraClave").param("keyword", "Prueba"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Curso de Prueba"));
    }
}