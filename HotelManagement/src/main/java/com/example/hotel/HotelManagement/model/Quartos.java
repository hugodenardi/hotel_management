package com.example.hotel.HotelManagement.model;

import com.example.hotel.HotelManagement.DTO.QuartosAtualizarDTO;
import com.example.hotel.HotelManagement.DTO.QuartosCriarDTO;
import jakarta.persistence.*;

@Entity
public class Quartos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipo;
    @Enumerated(EnumType.STRING)
    private StatusQuarto status;
    private double precoDiaria;

    public Quartos() {
    }

    public Quartos(QuartosCriarDTO quartosDTO) {
        setNumero(quartosDTO.getNumero());
        setTipo(quartosDTO.getTipo());
        setStatus(StatusQuarto.DISPONIVEL);
        setPrecoDiaria(quartosDTO.getPrecoDiaria());
    }
    public void Atualizar(QuartosAtualizarDTO quartosDTO) {
        setNumero(quartosDTO.getNumero());
        setTipo(quartosDTO.getTipo());
        setStatus(quartosDTO.getStatus());
        setPrecoDiaria(quartosDTO.getPrecoDiaria());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public StatusQuarto getStatus() {
        return status;
    }

    public void setStatus(StatusQuarto status) {
        this.status = status;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
}
