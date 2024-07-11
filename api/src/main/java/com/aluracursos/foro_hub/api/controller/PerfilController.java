package com.aluracursos.foro_hub.api.controller;

import com.aluracursos.foro_hub.api.domain.perfil.*;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosActualizaPerfil;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosListadoPerfiles;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosRegistroPerfil;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosResponsePerfil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("admin/perfiles")
@SecurityRequirement(name = "bearer-key")

public class PerfilController {

    @Autowired
    PerfilService service;
    @Autowired
    PerfilRepository repository;

    //registra un perfil
    @PostMapping
    @Transactional
    public ResponseEntity<DatosResponsePerfil> ingresarPerfil(@RequestBody @Valid DatosRegistroPerfil datos) {
        var response = service.registrar(datos);

        return ResponseEntity.ok(response);
    }

    //muestra lista de perfiles
    @GetMapping
    public ResponseEntity<Page<DatosListadoPerfiles>> listaPerfiles(Pageable paginacion){
        var response = repository.findByActivoTrue(paginacion).map(DatosListadoPerfiles::new);

        return ResponseEntity.ok(response);
    }

    //muestra todos los datos de 1 perfil
    @GetMapping("/{id}")
    public ResponseEntity consultarPerfil (@PathVariable Long id){
        service.validaIdAndActivo(id);
        var perfil  =  service.perfilById(id);
        var response = new DatosResponsePerfil(perfil);

        return ResponseEntity.ok(response);
    }

    //actualiza un perfil
    @PutMapping
    @Transactional
    public ResponseEntity actualizaPerfil (@RequestBody @Valid DatosActualizaPerfil datos){
        var response = service.actualizar(datos);

        return ResponseEntity.ok(response);
    }

    //eliminar perfil (delete logico)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPerfil (@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
