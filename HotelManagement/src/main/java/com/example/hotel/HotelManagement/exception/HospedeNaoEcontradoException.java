package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HospedeNaoEcontradoException extends RuntimeException {
    public HospedeNaoEcontradoException() {
        super("Hóspede não encontrado");
    }
}
