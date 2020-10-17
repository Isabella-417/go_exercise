package com.example.go_exercise.persistencia.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "enfoque")
public class Enfoque {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String nombre, descripcion;
    private String path_imagen;


    public Enfoque(String nombre, String descripcion,String path_imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.path_imagen = path_imagen;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPath_imagen() { return path_imagen; }

    public void setPath_imagen(String path_imagen) { this.path_imagen = path_imagen; }
}
