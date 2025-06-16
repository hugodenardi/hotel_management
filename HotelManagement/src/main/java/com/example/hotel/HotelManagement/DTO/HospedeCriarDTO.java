package com.example.hotel.HotelManagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class HospedeCriarDTO {
    @NotBlank(message = "O nome do hóspede é obrigatório")
    private String nome;
    @NotBlank(message = "O documento do hóspede é obrigatório")
    private String documento;
    @NotBlank(message = "O telefone do hóspede é obrigatório")
    private String telefone;
    @NotBlank(message = "O email do hóspede é obrigatório")
    @Email(message = "O campo deve ser preenchido com um endereço de e-mail válido")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
