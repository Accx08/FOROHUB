package com.aluracursos.foro_hub.api.controller;

import com.aluracursos.foro_hub.api.domain.curso.*;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosActualizaCurso;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosListadoCursos;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosRegistroCurso;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosResponseCurso;
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
@RequestMapping("admin/cursos")
@SecurityRequirement(name = "bearer-key")

public class CursoController {

    @Autowired
    CursoRepository repository;
    @Autowired
    CursoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosResponseCurso> ingresarCurso(@RequestBody @Valid DatosRegistroCurso datos) {
        var response = service.registrar(datos);
        return ResponseEntity.ok(response);
    }

    //muestra lista de cursos
    @GetMapping
    public ResponseEntity<Page<DatosListadoCursos>> listaCursos(Pageable paginacion){
        var response = repository.findByActivoTrue(paginacion).map(DatosListadoCursos::new);
        return ResponseEntity.ok(response);
    }

    //muestra todos los datos de 1 curso
    @GetMapping("/{id}")
    public ResponseEntity consultarCurso (@PathVariable Long id){
        service.validaIdAndActivo(id);
        var curso  = service.cursoById(id);
        var response = new DatosResponseCurso(curso);

        return ResponseEntity.ok(response);
    }

    //actualiza un curso
    @PutMapping
    @Transactional
    public ResponseEntity actualizaCurso (@RequestBody @Valid DatosActualizaCurso datos){
        var response = service.actualizar(datos);

        return ResponseEntity.ok(response);
    }

    //eliminar curso (delete logico)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso (@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}