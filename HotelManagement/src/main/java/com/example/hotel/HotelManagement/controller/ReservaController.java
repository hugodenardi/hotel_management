package com.example.hotel.HotelManagement.controller;
import com.example.hotel.HotelManagement.DTO.ReservaCriarDTO;
import com.example.hotel.HotelManagement.model.Reserva;
import com.example.hotel.HotelManagement.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private final ReservaService reservaService;
    public ReservaController(ReservaService reservaService) {
        this.reservaService = ReservaController.this.reservaService;
    }
    @GetMapping
    public List<Reserva> buscarReserva() {
        return reservaService.buscarReserva();
    }
    @GetMapping("/{id}")
    public Reserva buscarReservaDetalhada(@PathVariable Long id) {
        return reservaService.buscarReservaDetalhada(id);
    }
    @PostMapping
    public Reserva criarReserva (@RequestBody @Valid ReservaCriarDTO reserva) {
        return reservaService.criarReserva(reserva);
    }
    @PutMapping("/{id}/cancelar")
    public Reserva atualizarReservaCancelada(@PathVariable Long id) {
        return reservaService.atualizarReservaCancelada(id);
    }
    @PutMapping("/{id}/concluida")
    public Reserva atualizarReservaConcluida(@PathVariable Long id) {
        return reservaService.atualizarReservaConcluida(id);
    }

}
