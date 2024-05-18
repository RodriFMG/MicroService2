package com.example.ms22.Dto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SignUpRequest {
    private String username;
    private String email;
    private String contrasena;

    private Boolean isAdmin;


    public SignUpRequest() {
    }

    public SignUpRequest(String username, String email, String contrasena, Boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.contrasena = contrasena;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}


