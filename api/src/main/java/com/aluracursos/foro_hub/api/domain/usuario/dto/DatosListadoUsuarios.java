package com.aluracursos.foro_hub.api.domain.usuario.dto;

import com.aluracursos.foro_hub.api.domain.usuario.Usuario;

public record DatosListadoUsuarios (

        Long id,
        String nombre,
        String correoElectronico,
        String perfil
){
    public DatosListadoUsuarios(Usuario usuario) {
        this (usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfil().getNombre());
    }
}
