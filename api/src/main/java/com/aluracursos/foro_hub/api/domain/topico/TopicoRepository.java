package com.aluracursos.foro_hub.api.domain.topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //Page <Topico> findByActivoTrue(Pageable paginacion);

    Boolean existsByIdAndActivoTrue(Long id);

    Boolean existsByIdAndUsuarioId(Long topicoId, Long usuarioId);

    Boolean existsByIdAndCloseTrue(Long id);

    @Query("SELECT t FROM Topico t WHERE t.usuario.id = :usuarioId AND t.activo = true")
    Page<Topico> findByUsuarioIdActivoTrue(Long usuarioId, Pageable paginacion);
}
