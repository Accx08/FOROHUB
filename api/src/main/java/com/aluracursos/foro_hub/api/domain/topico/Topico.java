package com.aluracursos.foro_hub.api.domain.topico;

import com.aluracursos.foro_hub.api.domain.curso.Curso;
import com.aluracursos.foro_hub.api.domain.respuesta.Respuesta;
import com.aluracursos.foro_hub.api.domain.topico.dto.DatosActualizaTopico;
import com.aluracursos.foro_hub.api.domain.topico.dto.DatosRegistraTopico;
import com.aluracursos.foro_hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaClose;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    private Boolean activo;
    private Boolean close;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(DatosRegistraTopico datos) {
        this.id = getId();
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Estado.CREADO;
        this.usuario = getUsuario();
        this.curso = getCurso();
        this.fechaClose = null;
    }

    //Metodo para inactivar Topico
    public void inactivarTopico(){

        this.activo = false;
    }

    // Método para obtener el número total de respuestas
    public int getTotalRespuestas() {

        return respuestas != null ? respuestas.size() : 0;
    }

    public List<Long> respuestasIds (){

        return respuestas.stream()
                .map(Respuesta::getId)
                .collect(Collectors.toList());
    }



    //Metodo para actualizar informacion del topico y cambiar su estado
    public void actualizarInformacion(DatosActualizaTopico datos) {
        if (datos.id() != null)
            this.id = datos.id();
        if (datos.titulo() != null)
            this.titulo = datos.titulo();
        if (datos.mensaje() != null)
            this.mensaje = datos.mensaje();
        this.status = Estado.ACTUALIZADO;
    }

    //Metodo para cerrar el topico y no permitir mas respuestas
    public void cerrarTopico() {
        this.status = Estado.CERRADO;
        this.fechaClose = LocalDateTime.now();
        this.close = true;
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", status=" + status +
                ", usuario=" + usuario +
                ", curso=" + curso +
                ", respuestas=" + respuestas +
                '}';
    }
}
