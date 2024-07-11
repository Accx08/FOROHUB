package com.aluracursos.foro_hub.api.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario(
        @NotBlank(message = "Nombre es obligatorio")
        @Pattern(regexp = "^[a-zA-ZñÑ\\s]*$", message = "Este campo solo puede contener letras")
        String nombre,
        @NotBlank(message = "email es obligatorio")
        @Email(message = "Formato de email invalido")
        String correoElectronico,
        @NotBlank(message = "Contraseña es obligatoria")
        String contraseña
        //@NotNull
        //Long perfilId
) {
}
