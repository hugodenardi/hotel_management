package com.example.hotel.HotelManagement.controller;

import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.service.QuartosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quartos")
public class QuartosController {
    @Autowired
    private final QuartosService quartosService;
    public QuartosController(QuartosService quartosService) {
        this.quartosService = quartosService;
    }
    @GetMapping
    public List<Quartos> buscarQuartos() {
        return quartosService.buscarQuartos();
    }
    @GetMapping("/disponiveis")
    public List<Quartos> buscarQuartosDisponiveis() {
        return quartosService.buscarQuartosDisponiveis();
    }
    @GetMapping("/{id}")
    public Quartos buscarQuartoDetalhado(@PathVariable Long id) {
        return quartosService.buscarQuartoDetalhado(id);
    }
    @PostMapping
    public Quartos criarQuarto(@RequestBody @Valid QuartosCriarDTO quartos) {
        return quartosService.criarQuarto(quartos);
    }
    @PutMapping("/{id}")
    public Quartos atualizarQuarto(@RequestBody @Valid QuartosAtualizarDTO quartoAtualizado, @PathVariable Long id) {
        return quartosService.atualizarQuarto(quartoAtualizado, id);
    }
    @DeleteMapping("/{id}")
    public void deletarQuarto(@PathVariable Long id) {
        quartosService.deletarQuarto(id);
    }
}
