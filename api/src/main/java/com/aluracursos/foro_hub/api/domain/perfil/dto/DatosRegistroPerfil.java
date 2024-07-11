package com.aluracursos.foro_hub.api.domain.perfil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroPerfil(

        @NotNull(message = "El id es obligatorio")
        Long id,
        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "Este campo solo puede contener letras")
        String nombre
) {
}
