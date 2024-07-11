package com.aluracursos.foro_hub.api.domain.usuario.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosActualizaUsuario(

        @NotNull(message = "El id es obligatorio")
        Long id,
        @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "Este campo solo puede contener letras")
        String nombre) {
}
