package com.example.hotel.HotelManagement.repository;

import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Long> {
    boolean existsByQuartoIdAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatus(
            Long quartoId,
            Date dataSaida,
            Date dataEntrada,
            StatusReserva status
    );

    List<Reserva> findAllByHospedeDocumentoAndStatus(
            String documento,
            StatusReserva status
    );

}
