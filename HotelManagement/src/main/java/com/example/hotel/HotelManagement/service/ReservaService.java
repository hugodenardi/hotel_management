package com.example.hotel.HotelManagement.service;
import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.ReservaCriarDTO;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.model.StatusReserva;
import com.example.hotel.HotelManagement.repository.QuartosRepository;
import com.example.hotel.HotelManagement.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ReservaService(ReservasRepository reservasRepository, HospedeService hospedeService, QuartosService quartosService) {
        this.reservasRepository = reservasRepository;
        this.quartosService = quartosService;
        this.hospedeService = hospedeService;
    }
    public List<Reserva> buscarReserva(){return reservasRepository.findAll();}
    public Reserva buscarReservaDetalhada(Long id){
        return reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
    }
    public Reserva criarReserva(ReservaCriarDTO reservaCriarDTO) {
        hospedeService.buscarHospedeDetalhado(reservaCriarDTO.getHospede().getId());
        Quartos quarto = quartosService.buscarQuartoDetalhado(reservaCriarDTO.getQuarto().getId());

        if (quarto.getStatus() != StatusQuarto.DISPONIVEL) {
            throw new RuntimeException("Quarto indisponível para reserva.");
        }
        Reserva reserva = new Reserva();
        Date dataAtual = new Date();

        reserva.setDataEntrada(reservaCriarDTO.getDataEntrada());
        reserva.setDataSaida(reservaCriarDTO.getDataSaida());
//        if (dataAtual.after(reserva.getDataEntrada())) {
//            throw new RuntimeException("A data de entrada precisa ser posterior a data atual");
//        }
        if (reserva.getDataEntrada().after(reserva.getDataSaida())) {
            throw new RuntimeException("A data de saída precisa ser posterior a data de entrada");
        }
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
        return reserva;
    }
    public Reserva atualizarReservaCancelada(Long id) {
        Reserva reserva = reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        reserva.setStatus(StatusReserva.CANCELADA);
        reservasRepository.save(reserva);
        Quartos quarto = reserva.getQuarto();
        quartosService.atualizarQuartoStatus(StatusQuarto.DISPONIVEL, quarto.getId());
        return reserva;
    }
    public Reserva atualizarReservaConcluida(Long id) {
        Reserva reserva = reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        reserva.setStatus(StatusReserva.CONCLUIDA);
        reservasRepository.save(reserva);
        Quartos quarto = reserva.getQuarto();
        quartosService.atualizarQuartoStatus(StatusQuarto.DISPONIVEL, quarto.getId());
        return reserva;

    }

    public List<Reserva> buscarReservaHospede(String documento) {
        List<Reserva> reservas = reservasRepository.findAllByHospedeDocumentoAndStatus(documento, StatusReserva.ATIVA);
        if (reservas.isEmpty()) {
            throw new RuntimeException("Nenhuma reserva encontrada para o documento " + documento);
        }
        return reservas;
    }
    public Reserva buscarReserva(Long id) {
        return reservasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Reserva não encontrada")
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
            throw new RuntimeException("Você não pode sair até a data de saída " + reserva.getDataSaida());
        }

        if (reserva.getQuarto().getStatus() != StatusQuarto.OCUPADO) {
            throw new RuntimeException("O checkout só poder feito se tiver sido feito checkin");
        }
        reserva.getQuarto().setStatus(StatusQuarto.DISPONIVEL);
        reserva.setStatus(StatusReserva.CONCLUIDA);
        return reservasRepository.save(reserva);
    }
}
