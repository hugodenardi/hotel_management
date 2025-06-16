package com.example.hotel.HotelManagement.DTO;

import com.example.hotel.HotelManagement.model.StatusQuarto;
import com.example.hotel.HotelManagement.model.TipoQuarto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuartosCriarDTO {
    @NotBlank(message = "O número do quarto é obrigatório")
    private String numero;
    @NotNull(message = "O tipo do quarto é obrigatório")
    private TipoQuarto tipo;


    private double precoDiaria;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
}
