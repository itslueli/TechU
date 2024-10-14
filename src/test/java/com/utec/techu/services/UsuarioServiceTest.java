package com.utec.techu.services;

import com.utec.techu.dtos.UsuarioDTO;
import com.utec.techu.entities.Usuario;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan Perez");
        usuario.setEmail("juan.perez@example.com");
        usuario.setRol("Estudiante");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(1L);
        usuarioDTO.setNombre("Juan Perez");
        usuarioDTO.setEmail("juan.perez@example.com");
        usuarioDTO.setRol("Estudiante");
    }

    @Test
    void obtenerTodosLosUsuariosTest() {
        List<Usuario> usuarios = Arrays.asList(usuario);

        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioDTO> resultado = usuarioService.obtenerTodosLosUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());
    }

    @Test
    void crearUsuarioTest() {
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.crearUsuario(usuarioDTO);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    void actualizarUsuarioTest() {
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.actualizarUsuario(1L, usuarioDTO);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    void actualizarUsuarioNotFoundTest() {
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.actualizarUsuario(1L, usuarioDTO));
    }

    @Test
    void eliminarUsuarioTest() {
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        Mockito.doNothing().when(usuarioRepository).delete(usuario);

        assertDoesNotThrow(() -> usuarioService.eliminarUsuario(1L));
    }

    @Test
    void eliminarUsuarioNotFoundTest() {
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.eliminarUsuario(1L));
    }
}