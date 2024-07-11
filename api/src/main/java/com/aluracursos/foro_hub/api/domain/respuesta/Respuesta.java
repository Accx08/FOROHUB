package com.aluracursos.foro_hub.api.domain.respuesta;

import com.aluracursos.foro_hub.api.domain.respuesta.dto.DatosActualizaRespuesta;
import com.aluracursos.foro_hub.api.domain.respuesta.dto.DatosRegistroRespuesta;
import com.aluracursos.foro_hub.api.domain.topico.Topico;
import com.aluracursos.foro_hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario autorRespuesta;
    private String solucion;
    private Boolean activo;

    public Respuesta(DatosRegistroRespuesta datos) {
        this.id = getId();
        this.mensaje = datos.mensaje();
        this.topico = getTopico();
        this.fechaCreacion = LocalDateTime.now();
        this.autorRespuesta = getAutorRespuesta();
        this.solucion = datos.solucion();
        this.activo = true;
    }

    public void actualizarInformacion(DatosActualizaRespuesta datos) {
        if (datos.mensaje() != null)
            this.mensaje = datos.mensaje();
        if (datos.solucion() != null)
            this.solucion = datos.solucion();
    }

    public void inactivarRespuesta(){
        this.activo = false;
    }

}
