package com.example.go_exercise.persistencia.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.example.go_exercise.persistencia.DAO.RangoDificultadDAO;
import com.example.go_exercise.persistencia.GoExerciseDataBase;
import com.example.go_exercise.persistencia.entidades.Necesidad;
import com.example.go_exercise.persistencia.entidades.RangoDificultad;

import java.util.List;

public class RangoDificultadViewModel extends AndroidViewModel {

    private RangoDificultadDAO rangoDificultadDAO;
    private List<RangoDificultad> rangos;

    public RangoDificultadViewModel(@NonNull Application application) {
        super(application);
        rangoDificultadDAO = GoExerciseDataBase.getDatabase(application).rangoDao();
        rangos = rangoDificultadDAO.getRangos();
    }

    public void insert(RangoDificultad... rangos) {
        rangoDificultadDAO.insert(rangos);
    }

    public void update(RangoDificultad rango) {
        rangoDificultadDAO.update(rango);
    }

    public void deleteAll() {
        rangoDificultadDAO.deleteAll();
    }

    public List<RangoDificultad> getAll(){return rangoDificultadDAO.getRangos();}
}
