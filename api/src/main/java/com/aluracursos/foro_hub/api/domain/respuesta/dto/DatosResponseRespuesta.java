package com.aluracursos.foro_hub.api.domain.respuesta.dto;

import com.aluracursos.foro_hub.api.domain.respuesta.Respuesta;
import com.aluracursos.foro_hub.api.domain.topico.Topico;
import java.time.LocalDateTime;

public record DatosResponseRespuesta(

        Long id,

        String mensaje,

        Long topicoId,

        LocalDateTime fechaCreacion,

        Long usuarioId,

        String solucion
) {

    public DatosResponseRespuesta(Respuesta respuesta) {

        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                respuesta.getAutorRespuesta().getId(),
                respuesta.getSolucion()
        );
    }
}
