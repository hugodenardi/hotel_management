package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

public class QuartoReservadoException extends RuntimeException {
    public QuartoReservadoException() {
        super("Já existe uma reserva ativa para este quarto nessas datas.");
    }
}
