package com.example.joaoelolis.medicinesearchv2.modelos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String id;
    private String email;
    private String favoritos;
    private List<Medicamento> favorita = new ArrayList<Medicamento>();


    public Usuario(String id, String email, String favoritos) {
        this.id = id;
        this.email = email;
        this.favoritos = favoritos;
    }

    public Usuario(String id, String email, String favoritos, List<Medicamento> favorita) {
        this.id = id;
        this.email = email;
        this.favoritos = favoritos;
        this.favorita = favorita;
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

    public String getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(String favoritos) {
        this.favoritos = favoritos;
    }

    public List<Medicamento> getFavorita() {
        return favorita;
    }

    public void setFavorita(List<Medicamento> favorita) {
        this.favorita = favorita;
    }
}
