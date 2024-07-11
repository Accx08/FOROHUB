package com.aluracursos.foro_hub.api.domain.curso.dto;


import jakarta.validation.constraints.NotNull;

public record DatosActualizaCurso(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
}
