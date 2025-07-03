package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class QuartoIndisponivelException extends RuntimeException{
    public QuartoIndisponivelException() {
        super("Quarto indisponível para reserva");
    }
}
