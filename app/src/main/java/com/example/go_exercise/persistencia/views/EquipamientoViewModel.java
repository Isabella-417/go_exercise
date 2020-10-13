package com.example.go_exercise.persistencia.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.go_exercise.persistencia.DAO.EquipamientoDAO;
import com.example.go_exercise.persistencia.GoExerciseDataBase;
import com.example.go_exercise.persistencia.entidades.Equipamiento;

import java.util.List;

public class EquipamientoViewModel extends AndroidViewModel {

    private EquipamientoDAO equipamientoDao;
    private List<Equipamiento> equipamientos;

    public EquipamientoViewModel(@NonNull Application application) {
        super(application);
        equipamientoDao = GoExerciseDataBase.getDatabase(application).equipamientoDao();
        equipamientos = equipamientoDao.getEquipamientos();
    }

    public void insert(Equipamiento... equipamientos) {
        equipamientoDao.insert(equipamientos);
    }

    public void update(Equipamiento equipamiento) {
        equipamientoDao.update(equipamiento);
    }

    public void deleteAll() {
        equipamientoDao.deleteAll();
    }

    public List<Equipamiento> getAll(){return equipamientoDao.getEquipamientos();}
}
