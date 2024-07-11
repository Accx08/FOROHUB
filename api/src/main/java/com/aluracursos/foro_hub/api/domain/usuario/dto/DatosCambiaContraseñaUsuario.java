package com.aluracursos.foro_hub.api.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosCambiaContraseñaUsuario(

        @NotBlank (message = "El campo es obligatorio")
        String actualContraseña,
        @NotBlank (message = "El campo es obligatorio")
        String nuevaContraseña
) {
}
