package com.aluracursos.foro_hub.api.controller;

import com.aluracursos.foro_hub.api.domain.respuesta.dto.DatosListadoRespuesta;
import com.aluracursos.foro_hub.api.domain.topico.*;
import com.aluracursos.foro_hub.api.domain.topico.dto.*;
import com.aluracursos.foro_hub.api.domain.user.UserNameRepository;
import com.aluracursos.foro_hub.api.domain.usuario.UsuarioRepository;
import com.aluracursos.foro_hub.api.domain.usuario.UsuarioService;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    TopicoRepository repository;
    @Autowired
    TopicoService service;
    @Autowired
    UserNameRepository userNameRepository;

    //registra un nuevo topico

    @PostMapping
    @Transactional
    public ResponseEntity ingresarTopico(@RequestBody @Valid DatosRegistraTopico datos) {
        var response = service.ingresarTopico(datos);

        return ResponseEntity.ok(response);
    }

    //obtener datos de un topico registrado por usuario autenticado

    @GetMapping("/{id}")
    public ResponseEntity obtenerTopico(@PathVariable Long id){
        service.comparaId(1L, id);
        service.validaTopicoIdAndActivo(id);
        var topico = service.topicoById(id);
        var response = new DatosResponseTopico(topico);

        return ResponseEntity.ok(response);
    }

    //muestra lista de topicos

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listaTopicos(Pageable paginacion){
        var id = userNameRepository.getReferenceById(1L);
        var usuarioId = id.getTokenId();
        var response = repository.findByUsuarioIdActivoTrue(usuarioId,paginacion).map(DatosListadoTopicos::new);

        return ResponseEntity.ok(response);
    }

    //actualiza informacion topico y estado

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico (@RequestBody @Valid DatosActualizaTopico datos){
        service.comparaId(1L, datos.id());
        var response = service.actualizar(datos);

        return ResponseEntity.ok(response);
    }


    //cierra el topico
    @PutMapping("/cerrar")
    @Transactional
    public ResponseEntity cierraTopico (@RequestBody @Valid DatosCierraTopico datos){
        service.comparaId(1L, datos.id());
        var response = service.cerrar(datos);

        return ResponseEntity.ok(response);
    }


    //eliminar topico (delete logico)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico (@PathVariable Long id){
        service.comparaId(1L, id);
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
