package com.example.hotel.HotelManagement.repository;

import com.example.hotel.HotelManagement.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByUsuario (String usuario);
}
