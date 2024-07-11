package com.aluracursos.foro_hub.api.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findByActivoTrue(Pageable paginacion);

    Boolean existsByIdAndActivoTrue(Long id);

    Boolean existsByIdAndActivoFalse(Long id);
}
