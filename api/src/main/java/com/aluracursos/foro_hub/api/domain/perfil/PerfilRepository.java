package com.aluracursos.foro_hub.api.domain.perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    Boolean existsByIdAndActivoTrue(Long id);

    Page<Perfil> findByActivoTrue(Pageable paginacion);

}
