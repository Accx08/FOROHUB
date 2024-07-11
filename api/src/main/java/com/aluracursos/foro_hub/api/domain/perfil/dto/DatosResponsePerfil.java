package com.aluracursos.foro_hub.api.domain.perfil.dto;

import com.aluracursos.foro_hub.api.domain.perfil.Perfil;

public record DatosResponsePerfil(

        Long id,
        String nombre
) {
    public DatosResponsePerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombre());
    }
}
