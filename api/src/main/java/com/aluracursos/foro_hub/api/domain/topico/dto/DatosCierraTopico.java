package com.aluracursos.foro_hub.api.domain.topico.dto;

import jakarta.validation.constraints.NotNull;

public record DatosCierraTopico(
        @NotNull
        Long id,
        @NotNull
        Long usuarioId
) {
}
