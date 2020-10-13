package com.example.go_exercise.persistencia.entidades;

public class LapsoTiempo {
    String nombre;
    int minutos;

    public LapsoTiempo() {
    }

    public LapsoTiempo(String nombre, int minutos) {
        this.nombre = nombre;
        this.minutos = minutos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
}
