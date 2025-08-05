package com.example.hotel.HotelManagement.service;

import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.DTO.UsuarioCriarDTO;
import com.example.hotel.HotelManagement.model.RoleEnum;
import com.example.hotel.HotelManagement.model.Usuario;
import com.example.hotel.HotelManagement.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final HospedeService hospedeService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    @Lazy
    public UsuarioService(UsuarioRepository usuarioRepository, HospedeService hospedeService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.hospedeService = hospedeService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
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
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario novoUsuario = new Usuario();

        if (Objects.equals(tipo, "HOSPEDE")) {
            usuario.setRole(RoleEnum.ROLE_HOSPEDE);
            novoUsuario = usuarioRepository.save(usuario);
            HospedeCriarDTO hospedeCriarDTO = new HospedeCriarDTO();
            hospedeCriarDTO.setUsuario(novoUsuario.getId());
            BeanUtils.copyProperties(usuarioCriarDTO, hospedeCriarDTO);
            hospedeService.criarHospede(hospedeCriarDTO);
        } else if (Objects.equals(tipo, "ADMIN")) {
            usuario.setRole(RoleEnum.ROLE_ADMIN);
            novoUsuario = usuarioRepository.save(usuario);
        };
        return usuarioRepository.save(novoUsuario);
    }
    public String login (Usuario usuario) {
        UsernamePasswordAuthenticationToken usuarioSenha = new UsernamePasswordAuthenticationToken(usuario.getUsuario(),usuario.getSenha());
        Authentication auth = authenticationManager.authenticate(usuarioSenha);
        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }
}
