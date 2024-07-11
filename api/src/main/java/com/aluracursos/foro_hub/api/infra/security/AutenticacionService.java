package com.aluracursos.foro_hub.api.infra.security;

import com.aluracursos.foro_hub.api.domain.usuario.UsuarioRepository;
import com.aluracursos.foro_hub.api.infra.errores.ValidacionDeIntegridad;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {

        System.out.println("paso por Autenticatio service");
        if(usuarioRepository.existsByCorreoElectronicoAndActivoFalse(correoElectronico)){
           throw new ValidacionDeIntegridad("correo electronico registrado pero inactivo");
        }

        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }


}