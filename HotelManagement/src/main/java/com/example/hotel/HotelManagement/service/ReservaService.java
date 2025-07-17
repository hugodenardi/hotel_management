package com.example.hotel.HotelManagement.service;
import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.ReservaCriarDTO;
import com.example.hotel.HotelManagement.exception.*;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.model.StatusReserva;
import com.example.hotel.HotelManagement.repository.QuartosRepository;
import com.example.hotel.HotelManagement.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReservaService {
    private final ReservasRepository reservasRepository;
    private final QuartosService quartosService ;

    private final HospedeService hospedeService;
    @Autowired
    @Lazy
    public ReservaService(ReservasRepository reservasRepository, HospedeService hospedeService, QuartosService quartosService) {
        this.reservasRepository = reservasRepository;
        this.quartosService = quartosService;
        this.hospedeService = hospedeService;
    }
    public List<Reserva> buscarReserva(){return reservasRepository.findAll();}
    public Reserva buscarReservaDetalhada(Long id){
        return reservasRepository.findById(id).orElseThrow(ReservaNaoEncontradaException::new
        );
    }
    public Reserva criarReserva(ReservaCriarDTO reservaCriarDTO) {
        hospedeService.buscarHospedeDetalhado(reservaCriarDTO.getHospede().getId());
        Quartos quarto = quartosService.buscarQuartoDetalhado(reservaCriarDTO.getQuarto().getId());

        if (quarto.getStatus() != StatusQuarto.DISPONIVEL) {
            throw new QuartoIndisponivelException();
        }
        Reserva reserva = new Reserva();
        Date dataAtual = new Date();

        reserva.setDataEntrada(reservaCriarDTO.getDataEntrada());
        reserva.setDataSaida(reservaCriarDTO.getDataSaida());
        if (dataAtual.after(reserva.getDataEntrada())) {
            throw new DataInvalidaEntradaException();
        }
        if (reserva.getDataEntrada().after(reserva.getDataSaida())) {
            throw new DataInvalidaSaidaException();
        }
        boolean reservaExistente = reservasRepository.existsByQuartoIdAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatus(
                quarto.getId(),
                reserva.getDataSaida(),
                reserva.getDataEntrada(),
                StatusReserva.ATIVA
        );

        if (reservaExistente) {
            throw new QuartoReservadoException();
        }


        reserva.setStatus(StatusReserva.ATIVA);
        reserva.setHospede(reservaCriarDTO.getHospede());
        reserva.setQuarto(reservaCriarDTO.getQuarto());
        reservasRepository.save(reserva);
        return reserva;
    }
    public Reserva atualizarReservaCancelada(Long id) {
        Reserva reserva = buscarReservaDetalhada(id);
        reserva.setStatus(StatusReserva.CANCELADA);
        reservasRepository.save(reserva);
        Quartos quarto = reserva.getQuarto();
        quartosService.atualizarQuartoStatus(StatusQuarto.DISPONIVEL, quarto.getId());
        return reserva;
    }
    public Reserva atualizarReservaConcluida(Long id) {
        Reserva reserva = buscarReservaDetalhada(id);
        reserva.setStatus(StatusReserva.CONCLUIDA);
        reservasRepository.save(reserva);
        Quartos quarto = reserva.getQuarto();
        quartosService.atualizarQuartoStatus(StatusQuarto.DISPONIVEL, quarto.getId());
        return reserva;

    }

    public List<Reserva> buscarReservaHospede(String documento) {
        List<Reserva> reservas = reservasRepository.findAllByHospedeDocumentoAndStatus(documento, StatusReserva.ATIVA);
        if (reservas.isEmpty()) {
            throw new DocumentoNaoEncontradoException(documento);
        }
        return reservas;
    }
    public Reserva buscarReserva(Long id) {
        return reservasRepository.findById(id).orElseThrow(ReservaNaoEncontradaException::new
        );
    }
    public Reserva checkin(Long id) {
        Reserva reserva = buscarReserva(id);
        reserva.getQuarto().setStatus(StatusQuarto.OCUPADO);
        return reservasRepository.save(reserva);
    }

    public Reserva checkout(Long id) {
        Reserva reserva = buscarReserva(id);
        Date dataAtual = new Date();
        if (dataAtual.before(reserva.getDataSaida())) {
            throw new SaidaNaoPermitida(reserva.getDataSaida());
        }

        if (reserva.getQuarto().getStatus() != StatusQuarto.OCUPADO) {
            throw new CheckinNecessarioException();
        }
        reserva.getQuarto().setStatus(StatusQuarto.DISPONIVEL);
        reserva.setStatus(StatusReserva.CONCLUIDA);
        return reservasRepository.save(reserva);
    }
    public boolean verificarReservaPorDatas(Date dataEntrada, Date dataSaida, Long quartoId, StatusReserva status) {
        return reservasRepository.existsByQuartoIdAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatus(quartoId, dataSaida, dataEntrada, status);
    }
}
