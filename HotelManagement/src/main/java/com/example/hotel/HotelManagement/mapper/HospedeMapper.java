package com.example.hotel.HotelManagement.mapper;

import com.example.hotel.HotelManagement.DTO.HospedeAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.model.Hospede;
import org.springframework.stereotype.Component;

@Component
public class HospedeMapper {
    public Hospede toModelCriar(HospedeCriarDTO hospedeCriarDTO){
        Hospede hospede = new Hospede();
        hospede.setNome(hospedeCriarDTO.getNome());
        hospede.setDocumento(hospedeCriarDTO.getDocumento());
        hospede.setTelefone(hospedeCriarDTO.getTelefone());
        hospede.setEmail(hospedeCriarDTO.getEmail());
        hospede.setUsuario(hospedeCriarDTO.getUsuario());
        return hospede;
    }

    public HospedeCriarDTO toDTOCriar(Hospede hospede) {
        HospedeCriarDTO hospedeCriarDTO = new HospedeCriarDTO();
        hospedeCriarDTO.setDocumento(hospede.getDocumento());
        hospedeCriarDTO.setNome(hospede.getNome());
        hospedeCriarDTO.setTelefone(hospede.getTelefone());
        hospedeCriarDTO.setEmail(hospede.getEmail());
        return hospedeCriarDTO;
    }

    public Hospede toModelAtualizar(Hospede hospede, HospedeAtualizarDTO hospedeAtualizarDTO){
        hospede.setNome(hospedeAtualizarDTO.getNome());
        hospede.setDocumento(hospedeAtualizarDTO.getDocumento());
        hospede.setTelefone(hospedeAtualizarDTO.getTelefone());
        hospede.setEmail(hospedeAtualizarDTO.getEmail());
        return hospede;
    }

    public HospedeAtualizarDTO toDTOAtualizar(Hospede hospede) {
        HospedeAtualizarDTO hospedeAtualizarDTO = new HospedeAtualizarDTO();
        hospedeAtualizarDTO.setDocumento(hospede.getDocumento());
        hospedeAtualizarDTO.setNome(hospede.getNome());
        hospedeAtualizarDTO.setTelefone(hospede.getTelefone());
        hospedeAtualizarDTO.setEmail(hospede.getEmail());
        return hospedeAtualizarDTO;
    }
}
