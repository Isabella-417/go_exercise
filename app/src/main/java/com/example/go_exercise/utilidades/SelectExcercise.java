package com.example.go_exercise.utilidades;

import android.os.Parcel;
import android.os.Parcelable;

public class SelectExcercise implements Parcelable {

    String minutos;
    int cantEjercicios;

    public SelectExcercise(String minutos, int cantEjercicios) {
        this.minutos = minutos;
        this.cantEjercicios = cantEjercicios;
    }

    public SelectExcercise(Parcel in) {
       readFromParcel(in);
    }

    public static final Creator<SelectExcercise> CREATOR = new Creator<SelectExcercise>() {
        @Override
        public SelectExcercise createFromParcel(Parcel in) {
            return new SelectExcercise(in);
        }

        @Override
        public SelectExcercise[] newArray(int size) {
            return new SelectExcercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(minutos);
        dest.writeInt(cantEjercicios);
    }

  
    public void readFromParcel(Parcel dest) {
        minutos =dest.readString();
        cantEjercicios = dest.readInt();
    }


    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public int getCantEjercicios() {
        return cantEjercicios;
    }

    public void setCantEjercicios(int cantEjercicios) {
        this.cantEjercicios = cantEjercicios;
    }



    @Override
    public String toString() {
        return "ListSelectExcercise{" +
                "minutos='" + minutos + '\'' +
                ", cantEjercicios=" + cantEjercicios +
                '}';
    }
}
