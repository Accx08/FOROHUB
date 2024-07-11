package com.aluracursos.foro_hub.api.domain.curso;

import com.aluracursos.foro_hub.api.domain.curso.dto.DatosActualizaCurso;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosRegistroCurso;
import com.aluracursos.foro_hub.api.domain.curso.dto.DatosResponseCurso;
import com.aluracursos.foro_hub.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    CursoRepository repository;

    //Registrar un nuevo curso
    public DatosResponseCurso registrar(DatosRegistroCurso datos) {
        validaCursoPorIdYActivoFalse(datos.id());
        validaCursoPorId(datos.id());
        var curso = new Curso(datos);
        repository.save(curso);

        return new DatosResponseCurso(curso);
    }

    //Actualizar un curso por id
    public DatosResponseCurso actualizar(DatosActualizaCurso datos) {
        validaIdAndActivo(datos.id());
        var curso = cursoById(datos.id());
        curso.actualizarInformacion(datos);

        return new DatosResponseCurso(curso);
    }

    //Inactivar un curso
    public void delete(Long id) {
        validaIdAndActivo(id);
        var curso = cursoById(id);
        curso.inactivarCurso();
    }

    //Metodos de validacion y referencia
    public void validaCursoPorIdYActivoFalse(Long id){
        if (repository.existsByIdAndActivoFalse(id)) {
            throw new ValidacionDeIntegridad("este curso ya fue registrado, pero esta inactivo");
        }
    }

    public void validaCursoPorId (Long id){
        if (repository.existsById(id)) {
            throw new ValidacionDeIntegridad("este curso ya fue registrado");
        }
    }

    public void validaIdAndActivo(Long id) {
        if (!repository.existsByIdAndActivoTrue(id)) {
            throw new ValidacionDeIntegridad("este id de curso no existe");
        }
    }

    public Curso cursoById (Long id){
       var curso = repository.getReferenceById(id);

        return curso;
    }
}
