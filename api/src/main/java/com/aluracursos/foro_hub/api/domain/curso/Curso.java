package com.aluracursos.foro_hub.api.domain.curso;

import com.aluracursos.foro_hub.api.domain.curso.dto.DatosActualizaCurso;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosRegistroCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private Boolean activo;

    public Curso(DatosRegistroCurso curso){
        this.id = getId();
        this.nombre = curso.nombre();
        this.categoria = curso.categoria();
        this.activo = true;
    }

    public void actualizarInformacion(DatosActualizaCurso datos) {
        if (datos.nombre() != null)
            this.nombre = datos.nombre();
        if (datos.categoria() != null)
            this.categoria = datos.categoria();
    }

    public void inactivarCurso(){
        this.activo = false;
    }
    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
