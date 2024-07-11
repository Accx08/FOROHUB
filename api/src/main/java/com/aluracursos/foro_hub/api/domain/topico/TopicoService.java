package com.aluracursos.foro_hub.api.domain.topico;

import com.aluracursos.foro_hub.api.domain.curso.CursoRepository;
import com.aluracursos.foro_hub.api.domain.topico.dto.DatosActualizaTopico;
import com.aluracursos.foro_hub.api.domain.topico.dto.DatosCierraTopico;
import com.aluracursos.foro_hub.api.domain.topico.dto.DatosRegistraTopico;
import com.aluracursos.foro_hub.api.domain.topico.dto.DatosResponseTopico;
import com.aluracursos.foro_hub.api.domain.user.UserNameRepository;
import com.aluracursos.foro_hub.api.domain.usuario.UsuarioRepository;
import com.aluracursos.foro_hub.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UserNameRepository userNameRepository;

    //Registra un nuevo topico
    public DatosResponseTopico ingresarTopico (DatosRegistraTopico datos){
        validaUsuarioIdAndActivo(datos.usuarioId());
        validaCursoId(datos.cursoId());
        var usuario = usuarioRepository.findById(datos.usuarioId()).get();
        var curso = cursoRepository.findById(datos.cursoId()).get();
        var fecha = LocalDateTime.now();
        var topico = new Topico(null, datos.titulo(), datos.mensaje(), fecha,null, Estado.CREADO, usuario, curso, true,false, null);
        topicoRepository.save(topico);

        return new DatosResponseTopico(topico);
    }

    //Actualiza topico por id
    public DatosResponseTopico actualizar(DatosActualizaTopico datos) {
        validaTopicoIdAndActivo(datos.id());
        validaTopicoIdAndClose(datos.id());
        var topico = topicoById(datos.id());
        topico.actualizarInformacion(datos);

        return new DatosResponseTopico(topico);
    }

    //Cierra topico
    public DatosResponseTopico cerrar(DatosCierraTopico datos){
        validaTopicoIdAndActivo(datos.id());
        validaTopicoIdAndUsuarioId(datos.id(), datos.usuarioId());
        var topico = topicoById(datos.id());
        topico.cerrarTopico();

        return new DatosResponseTopico(topico);
    }

    //Inactiva topico
    public void delete(Long id) {
        validaTopicoIdAndActivo(id);
        var topico = topicoById(id);
        topico.inactivarTopico();
    }

    //Metododos de validacion de topico, usuario y curso.
    public void validaUsuarioIdAndActivo(Long id){
        if (!usuarioRepository.existsByIdAndActivoTrue(id)) {
            throw new ValidacionDeIntegridad("este id para el usuario no fue encontrado");
        }
    }

    public void validaCursoId(Long id){
        if(!cursoRepository.findById(id).isPresent()){
            throw new ValidacionDeIntegridad("este id para el curso no fue encontrado");
        }
    }

    public void validaTopicoIdAndActivo(Long id) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new ValidacionDeIntegridad("este id de topico no existe");
        }
    }

    //valida que topico indicado no este cerrado
    public void validaTopicoIdAndClose(Long id) {
        if (topicoRepository.existsByIdAndCloseTrue(id)) {
            throw new ValidacionDeIntegridad("Este topico esta cerrado. No se puede actualizar");
        }
    }

    //valida que usuario indicado sea el creador del topico
    public void validaTopicoIdAndUsuarioId (Long topicoId, Long usuarioId){
        if(!topicoRepository.existsByIdAndUsuarioId(topicoId,usuarioId)){
            throw new ValidacionDeIntegridad("este id de topico no corresponde con id de usuario proporcionado");
        }
    }

    public Topico topicoById (Long id){
        var topico = topicoRepository.getReferenceById(id);

        return topico;
    }
    //compara id del token con el id del usuario indicado
    public Boolean comparaId (Long tokenId, Long id ){
        var userName = userNameRepository.getReferenceById(tokenId);
        var topico = topicoRepository.getReferenceById(id);
        var usuarioId = topico.getUsuario().getId();
        if(!userName.getTokenId().equals(usuarioId)){
            throw new ValidacionDeIntegridad("id no corresponde a usuario autenticado");
        }
        return true;
    }
}
