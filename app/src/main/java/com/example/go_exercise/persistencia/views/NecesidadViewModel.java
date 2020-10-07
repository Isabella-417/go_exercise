package com.example.go_exercise.persistencia.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.example.go_exercise.persistencia.DAO.NecesidadDAO;
import com.example.go_exercise.persistencia.GoExerciseDataBase;
import com.example.go_exercise.persistencia.entidades.Necesidad;

import java.util.List;

public class NecesidadViewModel extends AndroidViewModel {

    private NecesidadDAO necesidadDao;
    private List<Necesidad> necesidades;

    public NecesidadViewModel(@NonNull Application application) {
        super(application);
        necesidadDao = GoExerciseDataBase.getDatabase(application).necesidadDao();
        necesidades = necesidadDao.getNecesidades();
    }

    public void insert(Necesidad... necesidades) {
        necesidadDao.insert(necesidades);
    }

    public void update(Necesidad necesidad) {
        necesidadDao.update(necesidad);
    }

    public void deleteAll() {
        necesidadDao.deleteAll();
    }

    public List<Necesidad> getAll(){return necesidadDao.getNecesidades();}
}
