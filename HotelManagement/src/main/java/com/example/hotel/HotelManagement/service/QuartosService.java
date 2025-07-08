package com.example.hotel.HotelManagement.service;

import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import com.example.hotel.HotelManagement.exception.QuartoNaoEncontradoException;
import com.example.hotel.HotelManagement.mapper.QuartoMapper;
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
    private final  QuartoMapper quartoMapper;
    @Autowired
    public QuartosService(QuartosRepository quartosRepository, QuartoMapper quartoMapper) {
        this.quartosRepository = quartosRepository;
        this.quartoMapper = quartoMapper;
    }

    public List<Quartos> buscarQuartos() {
        return quartosRepository.findAll();
    }
    public List<Quartos> buscarQuartosDisponiveis() {
        return quartosRepository.findAllByStatus(StatusQuarto.DISPONIVEL);
    }
    public Quartos buscarQuartoDetalhado(Long id){
        return quartosRepository.findById(id).orElseThrow(QuartoNaoEncontradoException::new
        );
    }
    public QuartosCriarDTO criarQuarto(QuartosCriarDTO quartosDTO) {
        Quartos quartos = new Quartos(quartosDTO);
        quartos = quartosRepository.save(quartos);
        return quartoMapper.toDTOCriar(quartos);
    }
    public QuartosAtualizarDTO atualizarQuarto(QuartosAtualizarDTO quartoAtualizado, Long id) {
        Quartos quarto = buscarQuartoDetalhado(id);
        quarto.Atualizar(quartoAtualizado);
        quarto =  quartosRepository.save(quarto);
        return quartoMapper.toDTOAtualizar(quarto);
    }

    public void deletarQuarto(Long id) {
        Quartos quarto = buscarQuartoDetalhado(id);
        quartosRepository.deleteById(id);
    }

    public void atualizarQuartoStatus(StatusQuarto statusQuarto, Long id) {
        Quartos quarto = buscarQuartoDetalhado(id);
        quarto.setStatus(statusQuarto);
        quartosRepository.save(quarto);
    }
}
