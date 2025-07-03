package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

public class DataInvalidaSaidaException extends RuntimeException {
    public DataInvalidaSaidaException() {
        super("A data de saída precisa ser posterior a data de entrada");
    }
}
