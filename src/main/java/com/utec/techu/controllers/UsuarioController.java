package com.utec.techu.controllers;

import com.utec.techu.dtos.UsuarioDTO;
import com.utec.techu.exceptions.ResourceNotFoundException;
import com.utec.techu.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        if (!usuarioService.existeUsuario(id)) {
            throw new ResourceNotFoundException("Usuario con ID " + id + " no encontrado");
        }
        UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioService.existeUsuario(id)) {
            throw new ResourceNotFoundException("Usuario con ID " + id + " no encontrado");
        }
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarPorRol/{rol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuariosPorRol(@PathVariable String rol) {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosPorRol(rol);
        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios con el rol: " + rol);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscarPorNombre")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuariosPorNombre(@RequestParam String nombre) {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosPorNombre(nombre);
        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios con el nombre: " + nombre);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscarPorEmail")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorEmail(email);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuario no encontrado con el email: " + email);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/buscarPorLocalidadYRol")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuariosPorLocalidadYRol(@RequestParam String departamento, @RequestParam String rol) {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosPorLocalidadYRol(departamento, rol);
        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios en el departamento: " + departamento + " con el rol: " + rol);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}