package com.aluracursos.foro_hub.api.domain.perfil;

import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosActualizaPerfil;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosRegistroPerfil;
import com.aluracursos.foro_hub.api.domain.perfil.dto.DatosResponsePerfil;
import com.aluracursos.foro_hub.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    PerfilRepository repository;

    //Registra un nuevo perfil
    public DatosResponsePerfil registrar(DatosRegistroPerfil datos) {
        validaPerfilId(datos.id());
        var perfil = new Perfil(datos);
        repository.save(perfil);

        return new DatosResponsePerfil(perfil);
    }

    //Actualiza perfil por id
    public DatosResponsePerfil actualizar(DatosActualizaPerfil datos) {
        validaIdAndActivo(datos.id());
        var perfil = perfilById(datos.id());
        perfil.actualizarInformacion(datos);

        return new DatosResponsePerfil(perfil);
    }

    //Inactiva un perfil
    public void delete(Long id) {
        validaIdAndActivo(id);
        var perfil = perfilById(id);
        perfil.inactivarPerfil();
    }

    //Metodos para validar y referencias de perfil
    public void validaPerfilId(Long id) {
        if (repository.existsById(id)) {
            throw new ValidacionDeIntegridad("este id de perfil ya fue registrado");
        }
    }

    public void validaIdAndActivo(Long id) {
        if (!repository.existsByIdAndActivoTrue(id)) {
            throw new ValidacionDeIntegridad("este id de perfil no existe");
        }
    }

    public Perfil perfilById (Long id){
        var perfil = repository.getReferenceById(id);

        return perfil;
    }
}
