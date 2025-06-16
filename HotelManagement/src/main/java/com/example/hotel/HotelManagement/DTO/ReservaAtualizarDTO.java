package com.example.hotel.HotelManagement.DTO;

import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.model.StatusReserva;
import jakarta.validation.constraints.NotNull;

public class ReservaAtualizarDTO extends ReservaCriarDTO{
    @NotNull(message = "O status da reserva é obrigatório")
    private StatusReserva status;

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}
