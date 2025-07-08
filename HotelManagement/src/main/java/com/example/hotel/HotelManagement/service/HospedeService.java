package com.example.hotel.HotelManagement.service;

import com.example.hotel.HotelManagement.DTO.HospedeAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.exception.HospedeNaoEcontradoException;
import com.example.hotel.HotelManagement.mapper.HospedeMapper;
import com.example.hotel.HotelManagement.model.Hospede;
import com.example.hotel.HotelManagement.repository.HospedesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {
    private final HospedesRepository hospedesRepository;
    private final HospedeMapper hospedeMapper;
    @Autowired
    public HospedeService(HospedesRepository hospedesRepository, HospedeMapper hospedeMapper) {
        this.hospedesRepository = hospedesRepository;
        this.hospedeMapper = hospedeMapper;
    }
    public List<Hospede> buscarHospedes() {
        return hospedesRepository.findAll();
    }
    public Hospede buscarHospedeDetalhado(Long id){
        return hospedesRepository.findById(id).orElseThrow(HospedeNaoEcontradoException::new
        );
    }
    public HospedeCriarDTO criarHospede(HospedeCriarDTO hospedeDTO) {
        Hospede hospede = hospedeMapper.toModelCriar(hospedeDTO);
        hospede = hospedesRepository.save(hospede);
        return hospedeMapper.toDTOCriar(hospede);
    }
    public HospedeAtualizarDTO atualizarHospede(HospedeAtualizarDTO hospedeAtualizarDTO, Long id) {
        Hospede hospede = buscarHospedeDetalhado(id);
        hospede = hospedeMapper.toModelAtualizar(hospede, hospedeAtualizarDTO);
        hospede = hospedesRepository.save(hospede);
        return hospedeMapper.toDTOAtualizar(hospede);
    }
    public void deletarHospede(Long id) {
        Hospede hospede = buscarHospedeDetalhado(id);
        hospedesRepository.deleteById(id);
    }
}
