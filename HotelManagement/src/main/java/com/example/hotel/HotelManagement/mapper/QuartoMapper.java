package com.example.hotel.HotelManagement.mapper;

import com.example.hotel.HotelManagement.DTO.QuartoDTO;
import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import org.springframework.stereotype.Component;

@Component
public class QuartoMapper {
    public QuartosCriarDTO toDTOCriar(Quartos quartos) {
        QuartosCriarDTO quartosCriarDTO = new QuartosAtualizarDTO();
        quartosCriarDTO.setNumero(quartos.getNumero());
        quartosCriarDTO.setTipo(quartos.getTipo());
        quartosCriarDTO.setPrecoDiaria(quartos.getPrecoDiaria());
        return quartosCriarDTO;
    }
    public QuartosAtualizarDTO toDTOAtualizar(Quartos quartos) {
        QuartosAtualizarDTO quartoAtualizarDTO = new QuartosAtualizarDTO();
        quartoAtualizarDTO.setNumero(quartos.getNumero());
        quartoAtualizarDTO.setTipo(quartos.getTipo());
        quartoAtualizarDTO.setStatus((quartos.getStatus()));
        quartoAtualizarDTO.setPrecoDiaria(quartos.getPrecoDiaria());
        return quartoAtualizarDTO;
    }
    public QuartoDTO toDTO(Quartos quarto) {
        QuartoDTO quartoDTO = new QuartoDTO();
        quartoDTO.setId(quarto.getId());
        quartoDTO.setNumero(quarto.getNumero());
        quartoDTO.setTipo(quartoDTO.getTipo());
        quartoDTO.setStatus(quartoDTO.getStatus());
        quartoDTO.setPrecoDiaria(quartoDTO.getPrecoDiaria());
        return quartoDTO;
    }
}
