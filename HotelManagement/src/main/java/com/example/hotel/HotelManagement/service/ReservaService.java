package com.example.hotel.HotelManagement.service;
import com.example.hotel.HotelManagement.DTO.ReservaCriarDTO;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.model.StatusReserva;
import com.example.hotel.HotelManagement.repository.QuartosRepository;
import com.example.hotel.HotelManagement.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    private final ReservasRepository reservasRepository;
    private final QuartosRepository quartosRepository;
    @Autowired
    public ReservaService(ReservasRepository reservasRepository, QuartosRepository quartosRepository) {
        this.reservasRepository = reservasRepository;
        this.quartosRepository = quartosRepository;
    }
    public List<Reserva> buscarReserva(){return reservasRepository.findAll();}
    public Reserva buscarReservaDetalhada(Long id){
        return reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
    }
    public Reserva criarReserva(ReservaCriarDTO reservaCriarDTO) {

        Quartos quarto = quartosRepository.findById(reservaCriarDTO.getQuarto().getId())
                .orElseThrow(() ->
                        new RuntimeException("Não encontrado")
                );
        if (quarto.getStatus() != StatusQuarto.DISPONIVEL) {
            throw new RuntimeException("Quarto indisponível para reserva.");
        }
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(reservaCriarDTO.getDataEntrada());
        reserva.setDataSaida(reservaCriarDTO.getDataSaida());
        boolean reservaExistente = reservasRepository.existsByQuartoIdAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatus(
                quarto.getId(),
                reserva.getDataSaida(),
                reserva.getDataEntrada(),
                StatusReserva.ATIVA
        );

        if (reservaExistente) {
            throw new RuntimeException("Já existe uma reserva ativa para este quarto nessas datas.");
        }


        reserva.setStatus(StatusReserva.ATIVA);
        reserva.setHospede(reservaCriarDTO.getHospede());
        reserva.setQuarto(reservaCriarDTO.getQuarto());
        reservasRepository.save(reserva);
        quarto.setStatus(StatusQuarto.OCUPADO);
        quartosRepository.save(quarto);
        return reserva;
    }
    public Reserva atualizarReservaCancelada(Long id) {
        Reserva reserva = reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        reserva.setStatus(StatusReserva.CANCELADA);
        reservasRepository.save(reserva);
        Quartos quarto = reserva.getQuarto();
        quarto.setStatus(StatusQuarto.DISPONIVEL);
        quartosRepository.save(quarto);
        return reserva;
    }
    public Reserva atualizarReservaConcluida(Long id) {
        Reserva reserva = reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        reserva.setStatus(StatusReserva.CONCLUIDA);
        reservasRepository.save(reserva);
        Quartos quarto = reserva.getQuarto();
        quarto.setStatus(StatusQuarto.DISPONIVEL);
        quartosRepository.save(quarto);
        return reserva;

    }
}
