package com.aluracursos.foro_hub.api.domain.topico.dto;

import jakarta.validation.constraints.NotNull;

public record DatosActualizaTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje
) {
}
