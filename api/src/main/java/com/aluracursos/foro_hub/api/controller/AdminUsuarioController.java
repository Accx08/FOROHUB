package com.aluracursos.foro_hub.api.controller;

import com.aluracursos.foro_hub.api.domain.usuario.UsuarioRepository;
import com.aluracursos.foro_hub.api.domain.usuario.UsuarioService;
import com.aluracursos.foro_hub.api.domain.usuario.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

public class AdminUsuarioController {

    @Controller
    @ResponseBody
    @RequestMapping("/admin/usuarios")
    @SecurityRequirement(name = "bearer-key")
//cambiar nombre a clase, agregar actualizar perfil
    public class UsuarioController {

        @Autowired
        UsuarioService service;
        @Autowired
        UsuarioRepository repository;

        //ingresar un usuario

        @PostMapping
        @Transactional
        public ResponseEntity<DatosResponseUsuario> ingresarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {

            var response = service.registrar(datos);
            return ResponseEntity.ok(response);
        }

        //muestra lista de usuarios

        @GetMapping()
        public ResponseEntity<Page<DatosListadoUsuarios>> listaUsuarios(Pageable paginacion){
            var response = repository.findByActivoTrue(paginacion).map(DatosListadoUsuarios::new);
            return ResponseEntity.ok(response);
        }

        //muestra todos los datos de 1 usuario

        @GetMapping("/{id}")
        public ResponseEntity consultarUsuario (@PathVariable Long id){
            service.validaUsuarioIdAndActivo(id);
            var usuario  =  repository.getReferenceById(id);
            var response = new DatosResponseUsuario(usuario);
            return ResponseEntity.ok(response);
        }

        //actualiza un usuario

        @PutMapping
        @Transactional
        public ResponseEntity actualizaUsuario (@RequestBody @Valid DatosActualizaUsuario datos){

            var response = service.actualizar(datos);
            return ResponseEntity.ok(response);
        }
        //actualiza un contarseña de usuario

        @PutMapping("/{id}")
        @Transactional
        public ResponseEntity actualizaContraseña (@PathVariable Long id, @RequestBody @Valid DatosCambiaContraseñaUsuario datos){

            var response = service.cambiaContraseña(id,datos);
            return ResponseEntity.ok(response);
        }

        //eliminar usuario (delete logico)

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity eliminarUsuario (@PathVariable Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

}
