package com.aluracursos.foro_hub.api.controller;

import com.aluracursos.foro_hub.api.domain.usuario.*;
import com.aluracursos.foro_hub.api.domain.usuario.dto.*;
import com.aluracursos.foro_hub.api.infra.security.AutenticacionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")

public class UsuarioController {

    @Autowired
    UsuarioService service;
    @Autowired
    UsuarioRepository repository;
    @Autowired
    AutenticacionService autenticacionService;

    //ingresar un usuario

    @PostMapping
    @Transactional
    public ResponseEntity<DatosResponseUsuario> ingresarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {

        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    //muestra todos los datos del usuario autenticado

    @GetMapping("/{id}")
    public ResponseEntity consultarUsuario (@PathVariable Long id){

        service.comparaId(1L,id);
        service.validaUsuarioIdAndActivo(id);
        var usuario  =  repository.getReferenceById(id);
        var response = new DatosResponseUsuario(usuario);
        return ResponseEntity.ok(response);
    }

    //actualiza el usuario autenticado

    @PutMapping
    @Transactional
    public ResponseEntity actualizaUsuario (@RequestBody @Valid DatosActualizaUsuario datos){
        service.comparaId(1L, datos.id());
        var response = service.actualizar(datos);
        return ResponseEntity.ok(response);
    }
    //actualiza un contarseña de usuario autenticado

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizaContraseña (@PathVariable Long id, @RequestBody @Valid DatosCambiaContraseñaUsuario datos){
        service.comparaId(1L,id);
        var response = service.cambiaContraseña(id,datos);
        return ResponseEntity.ok(response);
    }

    //eliminar usuario autenticado (delete logico)

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario (@PathVariable Long id){
        service.comparaId(1L,id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
