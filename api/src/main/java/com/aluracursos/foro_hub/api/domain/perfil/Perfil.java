package com.aluracursos.foro_hub.api.domain.perfil;


import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosActualizaPerfil;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosRegistroPerfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    private Long id;

    private String nombre;

    private Boolean activo;

    public Perfil(DatosRegistroPerfil datos) {
        this.id = datos.id();
        this.nombre = datos.nombre();
        this.activo = true;
    }

    public void actualizarInformacion(DatosActualizaPerfil datos) {
        if (datos.nombre() != null)
            this.nombre = datos.nombre();
    }

    public void inactivarPerfil() {
        this.activo = false;
    }

}