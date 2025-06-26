package com.example.hotel.HotelManagement.DTO;

import com.example.hotel.HotelManagement.model.Hospede;
import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.StatusReserva;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class ReservaCriarDTO {
    @NotNull(message = "A data de entrada é obrigatória")
    private Date dataEntrada;
    @NotNull(message = "A data de saída é obrigatória")
    private Date dataSaida;
    @NotNull(message = "O hóspede é obrigatório")
    private Hospede hospede;
    @NotNull(message = "O quarto é obrigatório")
    private Quartos quarto;

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quartos getQuarto() {
        return quarto;
    }

    public void setQuarto(Quartos quarto) {
        this.quarto = quarto;
    }
}
