package com.aluracursos.foro_hub.api.domain.curso.dto;

import com.aluracursos.foro_hub.api.domain.curso.Curso;

public record DatosListadoCursos(
        Long id,
        String nombre,
        String categoria
) {
    public DatosListadoCursos(Curso curso) {
        this (curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
