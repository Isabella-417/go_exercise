package com.example.go_exercise.persistencia.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.go_exercise.persistencia.DAO.EjercicioDAO;
import com.example.go_exercise.persistencia.GoExerciseDataBase;
import com.example.go_exercise.persistencia.entidades.Ejercicio;

import java.util.List;

public class EjercicioViewModel extends AndroidViewModel {

    private EjercicioDAO ejercicioDao;
    private List<Ejercicio> ejercicios;


    public EjercicioViewModel(@NonNull Application application) {
        super(application);
        ejercicioDao = GoExerciseDataBase.getDatabase(application).ejercicioDao();
        ejercicios = ejercicioDao.getEjercicios();
    }
    public void insert(Ejercicio... ejercicios) {
        ejercicioDao.insert(ejercicios);
    }

    public void update(Ejercicio ejercicio) {
        ejercicioDao.update(ejercicio);
    }

    public void deleteAll() {
        ejercicioDao.deleteAll();
    }

    public List<Ejercicio> getAll(){return ejercicioDao.getEjercicios();}
}
