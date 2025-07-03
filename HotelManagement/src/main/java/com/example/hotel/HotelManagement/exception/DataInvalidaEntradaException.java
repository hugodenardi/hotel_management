package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

public class DataInvalidaEntradaException extends RuntimeException{
    public DataInvalidaEntradaException() {
        super("Data de reserva necessita ser posterior a data atual");
    }
}
