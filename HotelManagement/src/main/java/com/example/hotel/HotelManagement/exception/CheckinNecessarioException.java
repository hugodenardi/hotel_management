package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

public class CheckinNecessarioException extends RuntimeException {
    public CheckinNecessarioException() {
        super("O checkout só poder feito se tiver sido feito checkin");
    }
}
