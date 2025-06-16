package com.example.hotel.HotelManagement.controller;

import com.example.hotel.HotelManagement.DTO.HospedeAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.HospedeCriarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import com.example.hotel.HotelManagement.model.Hospede;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.service.HospedeService;
import com.example.hotel.HotelManagement.service.QuartosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {
    @Autowired
    private final HospedeService hospedeService;
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }
    @GetMapping
    public List<Hospede> buscarHospedes() {
        return hospedeService.buscarHospedes();
    }
    @GetMapping("/{id}")
    public Hospede buscarHospedeDetalhado(@PathVariable Long id) {
        return hospedeService.buscarHospedeDetalhado(id);
    }
    @PostMapping
    public Hospede criarHospede (@RequestBody @Valid HospedeCriarDTO hospede) {
        return hospedeService.criarHospede(hospede);
    }
    @PutMapping("/{id}")
    public Hospede atualizarHospede(@RequestBody @Valid HospedeAtualizarDTO hospedeAtualizado, @PathVariable Long id) {
        return hospedeService.atualizarHospede(hospedeAtualizado, id);
    }
    @DeleteMapping("/{id}")
    public void deletarHospede(@PathVariable Long id) {
        hospedeService.deletarHospede(id);
    }
}
