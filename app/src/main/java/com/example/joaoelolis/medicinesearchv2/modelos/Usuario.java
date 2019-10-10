package com.example.joaoelolis.medicinesearchv2.modelos;

import java.util.List;

public class Usuario {

    private String id;
    private String email;
    private List<Medicamento> favoritos;

    public Usuario(String id, String email, List<Medicamento> favoritos) {
        this.id = id;
        this.email = email;
        this.favoritos = favoritos;
    }

    public Usuario(){

    }

    public Usuario(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Medicamento> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Medicamento> favoritos) {
        this.favoritos = favoritos;
    }
}
