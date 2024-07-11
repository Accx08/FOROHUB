package com.aluracursos.foro_hub.api.domain.usuario.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosActualizaPerfilUsuario(

        @NotNull(message = "El id es obligatorio")
        Long id,

        Long perfilId) {
}
