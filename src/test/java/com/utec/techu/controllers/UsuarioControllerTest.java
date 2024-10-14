package com.utec.techu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utec.techu.dtos.UsuarioDTO;
import com.utec.techu.services.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(1L);
        usuarioDTO.setNombre("Usuario de Prueba");
        usuarioDTO.setEmail("usuario@prueba.com");
        usuarioDTO.setRol("Estudiante");
        usuarioDTO.setFechaRegistro(java.time.LocalDate.now());
    }

    @Test
    void obtenerTodosLosUsuariosTest() throws Exception {
        List<UsuarioDTO> usuarios = Arrays.asList(usuarioDTO);

        Mockito.when(usuarioService.obtenerTodosLosUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Usuario de Prueba"));
    }

    @Test
    void crearUsuarioTest() throws Exception {
        Mockito.when(usuarioService.crearUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Usuario de Prueba"));
    }

    @Test
    void actualizarUsuarioTest() throws Exception {
        Mockito.when(usuarioService.actualizarUsuario(anyLong(), any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Usuario de Prueba"));
    }

    @Test
    void eliminarUsuarioTest() throws Exception {
        Mockito.doNothing().when(usuarioService).eliminarUsuario(anyLong());

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarUsuariosPorRolTest() throws Exception {
        List<UsuarioDTO> usuarios = Arrays.asList(usuarioDTO);

        Mockito.when(usuarioService.buscarUsuariosPorRol("Estudiante")).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios/buscarPorRol/Estudiante"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rol").value("Estudiante"));
    }

    @Test
    void buscarUsuariosPorNombreTest() throws Exception {
        List<UsuarioDTO> usuarios = Arrays.asList(usuarioDTO);

        Mockito.when(usuarioService.buscarUsuariosPorNombre("Usuario")).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios/buscarPorNombre").param("nombre", "Usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Usuario de Prueba"));
    }

    @Test
    void buscarUsuarioPorEmailTest() throws Exception {
        Mockito.when(usuarioService.buscarUsuarioPorEmail("usuario@prueba.com")).thenReturn(usuarioDTO);

        mockMvc.perform(get("/api/usuarios/buscarPorEmail").param("email", "usuario@prueba.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("usuario@prueba.com"));
    }
}