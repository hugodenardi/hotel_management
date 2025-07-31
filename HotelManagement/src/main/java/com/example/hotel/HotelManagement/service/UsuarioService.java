package com.example.hotel.HotelManagement.service;

import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.DTO.UsuarioCriarDTO;
import com.example.hotel.HotelManagement.model.Usuario;
import com.example.hotel.HotelManagement.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final HospedeService hospedeService;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, HospedeService hospedeService) {
        this.usuarioRepository = usuarioRepository;
        this.hospedeService = hospedeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsuario(username).orElseThrow(() ->
                new RuntimeException("Credenciais não encontradas")
        );
    }
    public Usuario criarUsuario(UsuarioCriarDTO usuarioCriarDTO, String tipo) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCriarDTO, usuario);
        Usuario novoUsuario = usuarioRepository.save(usuario);
        if (Objects.equals(tipo, "HOSPEDE")) {
            HospedeCriarDTO hospedeCriarDTO = new HospedeCriarDTO();
            hospedeCriarDTO.setUsuario(novoUsuario.getId());
            BeanUtils.copyProperties(usuarioCriarDTO, hospedeCriarDTO);
            hospedeService.criarHospede(hospedeCriarDTO);
        } else if (Objects.equals(tipo, "ADMIN")) {

        };
        return usuarioRepository.save(usuario);
    }
}
