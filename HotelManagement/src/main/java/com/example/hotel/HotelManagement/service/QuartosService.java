package com.example.hotel.HotelManagement.service;

import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.repository.QuartosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartosService {
    private final QuartosRepository quartosRepository;
    @Autowired
    public QuartosService(QuartosRepository quartosRepository) {
        this.quartosRepository = quartosRepository;
    }

    public List<Quartos> buscarQuartos() {
        return quartosRepository.findAll();
    }
    public List<Quartos> buscarQuartosDisponiveis() {
        return quartosRepository.findAllByStatus(StatusQuarto.DISPONIVEL);
    }
    public Quartos buscarQuartoDetalhado(Long id){
        return quartosRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
    }
    public Quartos criarQuarto(QuartosCriarDTO quartosDTO) {
        Quartos quartos = new Quartos();
        quartos.setNumero(quartosDTO.getNumero());
        quartos.setTipo(quartosDTO.getTipo());
        quartos.setStatus(StatusQuarto.DISPONIVEL);
        quartos.setPrecoDiaria(quartosDTO.getPrecoDiaria());
        quartosRepository.save(quartos);
        return quartos;
    }
    public Quartos atualizarQuarto(QuartosAtualizarDTO quartoAtualizado, Long id) {
        Quartos quarto = quartosRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        quarto.setNumero(quartoAtualizado.getNumero());
        quarto.setTipo(quartoAtualizado.getTipo());
        quarto.setStatus(quartoAtualizado.getStatus());
        quarto.setPrecoDiaria(quartoAtualizado.getPrecoDiaria());
        //BeanUtils.copyProperties(quartoAtualizado, quarto);
        return quartosRepository.save(quarto);
    }
    public void deletarQuarto(Long id) {
        Quartos quarto = quartosRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        quartosRepository.deleteById(id);
    }
}
