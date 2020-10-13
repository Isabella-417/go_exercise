package com.example.go_exercise.utilidades;

import android.os.Parcel;
import android.os.Parcelable;


public class VariableGlobales implements Parcelable {

    public String necesidad;
    public String rangoDificultad;
    public String enfoque;
    public String equipamiento;


    public String getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(String necesidad) {
        this.necesidad = necesidad;
    }

    public String getRangoDificultad() {
        return rangoDificultad;
    }

    public void setRangoDificultad(String rangoDificultad) {
        this.rangoDificultad = rangoDificultad;
    }

    public String getEnfoque() {
        return enfoque;
    }

    public void setEnfoque(String enfoque) {
        this.enfoque = enfoque;
    }

    public String getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(String equipamiento) {
        this.equipamiento = equipamiento;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(necesidad);
        parcel.writeString(rangoDificultad);
        parcel.writeString(enfoque);
        parcel.writeString(equipamiento);
    }

    public void readFromParcel(Parcel parcel) {
        necesidad = parcel.readString();
        rangoDificultad = parcel.readString();
        enfoque = parcel.readString();
        equipamiento = parcel.readString();
    }


    public VariableGlobales() {
    }

    public VariableGlobales(String necesidad, String rangoDificultad, String enfoque, String equipamiento) {
        this.necesidad = necesidad;
        this.rangoDificultad = rangoDificultad;
        this.enfoque = enfoque;
        this.equipamiento = equipamiento;
    }

    public VariableGlobales(Parcel in) {
        readFromParcel(in);
    }


    public static final Creator<VariableGlobales> CREATOR = new Creator<VariableGlobales>() {
        @Override
        public VariableGlobales createFromParcel(Parcel in) {
            return new VariableGlobales(in);
        }

        @Override
        public VariableGlobales[] newArray(int size) {
            return new VariableGlobales[size];
        }
    };

    @Override
    public String toString() {
        return "VariableGlobales{" +
                "necesidad='" + necesidad + '\'' +
                ", rangoDificultad='" + rangoDificultad + '\'' +
                ", enfoque='" + enfoque + '\'' +
                ", equipamiento='" + equipamiento + '\'' +
                '}';
    }
}
