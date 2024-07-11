package com.aluracursos.foro_hub.api.domain.respuesta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroRespuesta(


        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        Long autorRespuestaId,
        @NotBlank
        String solucion
) {
}
