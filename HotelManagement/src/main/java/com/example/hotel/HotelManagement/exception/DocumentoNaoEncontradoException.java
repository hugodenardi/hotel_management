package com.example.hotel.HotelManagement.exception;

import com.example.hotel.HotelManagement.model.Hospede;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class DocumentoNaoEncontradoException extends RuntimeException {
    public DocumentoNaoEncontradoException(String numeroDocumento) {
        super("Nenhuma reserva encontrada para o documento " + numeroDocumento);
    }
}
