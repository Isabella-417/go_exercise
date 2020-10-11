package com.example.go_exercise.persistencia.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "rango_dificultad")
public class RangoDificultad  implements Serializable  {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String nombre;
    private int tiempo;
    private int series;

    public RangoDificultad(String nombre, int tiempo, int series) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.series = series;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "RangoDificultad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                ", series=" + series +
                '}';
    }
}
