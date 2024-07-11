package com.aluracursos.foro_hub.api.domain.curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotNull
        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
