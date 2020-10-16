package com.example.go_exercise.utilidades;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.go_exercise.persistencia.entidades.Ejercicio;

import java.util.List;

public class EjerciciosSeleccionados implements Parcelable {

    public List<Ejercicio> ejercicios;
    public int series;
    public int tiempo;

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(ejercicios);
        parcel.writeInt(series);
        parcel.writeInt(tiempo);
    }

    public void readFromParcel(Parcel parcel) {
        ejercicios  = parcel.readParcelableList(ejercicios,Ejercicio.class.getClassLoader());
        series = parcel.readInt();
        tiempo = parcel.readInt();
    }

    public EjerciciosSeleccionados() {
    }

    public EjerciciosSeleccionados(List<Ejercicio> ejercicios, int series, int tiempo) {
        this.ejercicios = ejercicios;
        this.series = series;
        this.tiempo = tiempo;
    }

    protected EjerciciosSeleccionados(Parcel in) {
        readFromParcel(in);
    }


    public static Creator<EjerciciosSeleccionados> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<EjerciciosSeleccionados> CREATOR = new Creator<EjerciciosSeleccionados>() {
        @Override
        public EjerciciosSeleccionados createFromParcel(Parcel in) {
            return new EjerciciosSeleccionados(in);
        }

        @Override
        public EjerciciosSeleccionados[] newArray(int size) {
            return new EjerciciosSeleccionados[size];
        }
    };

    @Override
    public String toString() {
        return "EjerciciosSeleccionados{" +
                "ejercicios=" + ejercicios +
                ", series=" + series +
                ", tiempo=" + tiempo +
                '}';
    }
}
