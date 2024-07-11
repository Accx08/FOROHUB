package com.aluracursos.foro_hub.api.domain.perfil.dto;

import com.aluracursos.foro_hub.api.domain.perfil.Perfil;

public record DatosListadoPerfiles(
    Long id,
    String nombre
){
    public DatosListadoPerfiles(Perfil perfil) {
            this(perfil.getId(), perfil.getNombre());
        }
    }