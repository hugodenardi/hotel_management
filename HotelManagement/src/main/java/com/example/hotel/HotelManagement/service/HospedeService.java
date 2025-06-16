package com.example.hotel.HotelManagement.service;

import com.example.hotel.HotelManagement.DTO.HospedeAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import com.example.hotel.HotelManagement.model.Hospede;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.repository.HospedesRepository;
import com.example.hotel.HotelManagement.repository.QuartosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {
    private final HospedesRepository hospedesRepository;
    @Autowired
    public HospedeService(HospedesRepository hospedesRepository) {
        this.hospedesRepository = hospedesRepository;
    }
    public List<Hospede> buscarHospedes() {
        return hospedesRepository.findAll();
    }
    public Hospede buscarHospedeDetalhado(Long id){
        return hospedesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
    }
    public Hospede criarHospede(HospedeCriarDTO hospedeCriarDTO) {
        Hospede hospede = new Hospede();
        hospede.setNome(hospedeCriarDTO.getNome());
        hospede.setDocumento(hospedeCriarDTO.getDocumento());
        hospede.setTelefone(hospedeCriarDTO.getTelefone());
        hospede.setEmail(hospedeCriarDTO.getEmail());
        hospedesRepository.save(hospede);
        return hospede;
    }
    public Hospede atualizarHospede(HospedeAtualizarDTO hospedeAtualizado, Long id) {
        Hospede hospede = hospedesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        hospede.setNome(hospedeAtualizado.getNome());
        hospede.setDocumento(hospedeAtualizado.getDocumento());
        hospede.setTelefone(hospedeAtualizado.getTelefone());
        hospede.setEmail(hospedeAtualizado.getEmail());
        //BeanUtils.copyProperties(quartoAtualizado, quarto);
        return hospedesRepository.save(hospede);
    }
    public void deletarHospede(Long id) {
        Hospede hospede = hospedesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontrado")
        );
        hospedesRepository.deleteById(id);
    }
}
