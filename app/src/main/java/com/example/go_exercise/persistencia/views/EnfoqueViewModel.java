package com.example.go_exercise.persistencia.views;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.go_exercise.persistencia.DAO.EnfoqueDAO;
import com.example.go_exercise.persistencia.GoExerciseDataBase;
import com.example.go_exercise.persistencia.entidades.Enfoque;

import java.util.List;

public class EnfoqueViewModel extends AndroidViewModel {

    private EnfoqueDAO enfoqueDao;
    private List<Enfoque> enfoques;

    public EnfoqueViewModel(@NonNull Application application) {
        super(application);
        enfoqueDao = GoExerciseDataBase.getDatabase(application).enfoqueDao();
        enfoques = enfoqueDao.getEnfoques();
    }

    public void insert(Enfoque... enfoques) {
        enfoqueDao.insert(enfoques);
    }

    public void update(Enfoque enfoque) {
        enfoqueDao.update(enfoque);
    }

    public void deleteAll() {
        enfoqueDao.deleteAll();
    }

    public List<Enfoque> getAll(){return enfoqueDao.getEnfoques();}
}
