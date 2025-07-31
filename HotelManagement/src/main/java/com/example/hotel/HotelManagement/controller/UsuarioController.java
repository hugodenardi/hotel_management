package com.example.hotel.HotelManagement.controller;

import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.DTO.UsuarioCriarDTO;
import com.example.hotel.HotelManagement.model.Usuario;
import com.example.hotel.HotelManagement.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/hospede")
    public Usuario criarHospede(@RequestBody @Valid UsuarioCriarDTO usuarioCriarDTO) {
        return usuarioService.criarUsuario(usuarioCriarDTO, "HOSPEDE");
    }
}
