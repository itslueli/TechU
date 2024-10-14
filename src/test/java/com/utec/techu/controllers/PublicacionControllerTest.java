package com.utec.techu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utec.techu.dtos.PublicacionDTO;
import com.utec.techu.services.PublicacionService;
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

@WebMvcTest(PublicacionController.class)
public class PublicacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicacionService publicacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private PublicacionDTO publicacionDTO;

    @BeforeEach
    void setUp() {
        publicacionDTO = new PublicacionDTO();
        publicacionDTO.setIdPublicacion(1L);
        publicacionDTO.setFecha(java.time.LocalDate.now());
        publicacionDTO.setUsuarioId(1L);
        publicacionDTO.setCursoId(1L);
    }

    @Test
    void obtenerTodasLasPublicacionesTest() throws Exception {
        List<PublicacionDTO> publicaciones = Arrays.asList(publicacionDTO);

        Mockito.when(publicacionService.obtenerTodasLasPublicaciones()).thenReturn(publicaciones);

        mockMvc.perform(get("/api/publicaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(1L));
    }

    @Test
    void crearPublicacionTest() throws Exception {
        Mockito.when(publicacionService.crearPublicacion(any(PublicacionDTO.class))).thenReturn(publicacionDTO);

        mockMvc.perform(post("/api/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publicacionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuarioId").value(1L));
    }

    @Test
    void buscarPublicacionesPorUsuarioTest() throws Exception {
        List<PublicacionDTO> publicaciones = Arrays.asList(publicacionDTO);

        Mockito.when(publicacionService.buscarPublicacionesPorUsuario(anyLong())).thenReturn(publicaciones);

        mockMvc.perform(get("/api/publicaciones/buscarPorUsuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(1L));
    }

    @Test
    void buscarPublicacionesRecientesTest() throws Exception {
        List<PublicacionDTO> publicaciones = Arrays.asList(publicacionDTO);

        Mockito.when(publicacionService.buscarPublicacionesRecientes(any())).thenReturn(publicaciones);

        mockMvc.perform(get("/api/publicaciones/buscarRecientes").param("fecha", "2024-10-12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fecha").isNotEmpty());
    }

    @Test
    void eliminarPublicacionTest() throws Exception {
        Mockito.doNothing().when(publicacionService).eliminarPublicacion(anyLong());

        mockMvc.perform(delete("/api/publicaciones/1"))
                .andExpect(status().isNoContent());
    }
}
