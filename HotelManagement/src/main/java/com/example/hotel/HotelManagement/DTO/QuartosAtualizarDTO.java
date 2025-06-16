package com.example.hotel.HotelManagement.DTO;

import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.model.TipoQuarto;
import jakarta.validation.constraints.NotNull;

public class QuartosAtualizarDTO extends QuartosCriarDTO{
    @NotNull(message = "O status do quarto é obrigatório")
    private StatusQuarto status;

    public StatusQuarto getStatus() {
        return status;
    }

    public void setStatus(StatusQuarto status) {
        this.status = status;
    }
}
