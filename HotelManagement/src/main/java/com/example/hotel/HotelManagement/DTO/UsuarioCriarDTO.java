package com.example.hotel.HotelManagement.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioCriarDTO {
    @NotBlank(message = "O usuário é obrigatório")
    private String usuario;
    @NotBlank(message = "A senha é obrigatório")
    private String senha;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O documento é obrigatório")
    private String documento;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Digite um e-mail válido")
    private String email;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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
