package com.example.hotel.HotelManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Date;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SaidaNaoPermitida extends RuntimeException {
    public SaidaNaoPermitida(Date dataSaida) {
        super("Você não pode sair até a data de saída " + dataSaida);
    }
}
