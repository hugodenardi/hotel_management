package com.example.hotel.HotelManagement.service;
import com.example.hotel.HotelManagement.DTO.ReservaCriarDTO;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.model.StatusReserva;
import com.example.hotel.HotelManagement.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservasRepository reservasRepository;
    @Autowired
    public ReservaService(ReservasRepository reservasRepository) {this.reservasRepository = reservasRepository;}
    public List<Reserva> buscarReserva(){return reservasRepository.findAll();}
    public Reserva buscarReservaDetalhada(Long id){
        return reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
    }
    public Reserva criarReserva(ReservaCriarDTO reservaCriarDTO) {
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(reservaCriarDTO.getDataEntrada());
        reserva.setDataSaida(reservaCriarDTO.getDataSaida());
        reserva.setStatus(StatusReserva.ATIVA);
        reserva.setHospede(reservaCriarDTO.getHospede());
        reserva.setQuarto(reservaCriarDTO.getQuarto());
        reservasRepository.save(reserva);
        return reserva;
    }
    public Reserva atualizarReservaCancelada(Long id) {
        Reserva reserva = reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        reserva.setStatus(StatusReserva.CANCELADA);
        reservasRepository.save(reserva);
        return reserva;
    }
    public Reserva atualizarReservaConcluida(Long id) {
        Reserva reserva = reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        reserva.setStatus(StatusReserva.CONCLUIDA);
        reservasRepository.save(reserva);
        return reserva;
    }
}
