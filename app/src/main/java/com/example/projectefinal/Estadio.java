package com.example.projectefinal;

public class Estadio {

    String latitud;
    String longitud;
    String direccio;
    String Categoria;
    String Partidos;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getPartidos() {
        return Partidos;
    }

    public void setPartidos(String partidos) {
        Partidos = partidos;
    }

    public Estadio(String latitud, String longitud, String direccio, String categoria, String partidos) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccio = direccio;
        Categoria = categoria;
        Partidos = partidos;
    }

    public Estadio() {
    }
}
