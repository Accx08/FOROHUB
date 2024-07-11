package com.aluracursos.foro_hub.api.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {

    Page<Respuesta> findByActivoTrue(Pageable paginacion);
    //Page<Respuesta> findByIdAndActivoTrue(Long id, Pageable paginacion);
    Boolean existsByIdAndActivoTrue(Long id);

    @Query("SELECT r FROM Respuesta r WHERE r.autorRespuesta.id = :usuarioId AND r.activo = true")
   Page<Respuesta> findByAutorRespuestaAndActivoTrue(Long usuarioId, Pageable paginacion);
}
