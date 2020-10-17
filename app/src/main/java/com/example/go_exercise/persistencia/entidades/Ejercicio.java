package com.example.go_exercise.persistencia.entidades;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ejercicio")
public class Ejercicio implements Serializable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String nombre, necesidad, enfoque, equipamento;
    private String descripcion_corta;
    private String ruta_gif;

    @Ignore
    public Ejercicio(String nombre, String necesidad, String enfoque, String equipamento) {
        this.nombre = nombre;
        this.necesidad = necesidad;
        this.enfoque = enfoque;
        this.equipamento = equipamento;
    }

    public Ejercicio(String nombre, String descripcion_corta, String necesidad, String enfoque, String equipamento,String ruta_gif) {
        this.nombre = nombre;
        this.descripcion_corta = descripcion_corta;
        this.necesidad = necesidad;
        this.enfoque = enfoque;
        this.equipamento = equipamento;
        this.ruta_gif = ruta_gif;
    }


    protected Ejercicio(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion_corta = in.readString();
        necesidad = in.readString();
        enfoque = in.readString();
        equipamento = in.readString();
        ruta_gif = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(descripcion_corta);
        dest.writeString(necesidad);
        dest.writeString(enfoque);
        dest.writeString(equipamento);
        dest.writeString(ruta_gif);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ejercicio> CREATOR = new Creator<Ejercicio>() {
        @Override
        public Ejercicio createFromParcel(Parcel in) {
            return new Ejercicio(in);
        }

        @Override
        public Ejercicio[] newArray(int size) {
            return new Ejercicio[size];
        }
    };

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

    public String getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(String necesidad) {
        this.necesidad = necesidad;
    }

    public String getEnfoque() {
        return enfoque;
    }

    public void setEnfoque(String enfoque) {
        this.enfoque = enfoque;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getRuta_gif() {return ruta_gif; }

    public void setRuta_gif(String ruta_gif) {this.ruta_gif = ruta_gif; }

    public String getDescripcion_corta() {return descripcion_corta; }

    public void setDescripcion_corta(String descripcion_corta) {this.descripcion_corta = descripcion_corta; }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", necesidad='" + necesidad + '\'' +
                ", enfoque='" + enfoque + '\'' +
                ", equipamento='" + equipamento + '\'' +
                ", descripcion_corta='" + descripcion_corta + '\'' +
                ", ruta_gif='" + ruta_gif + '\'' +
                '}';
    }
}
