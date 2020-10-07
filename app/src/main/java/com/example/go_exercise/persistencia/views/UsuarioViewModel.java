package com.example.go_exercise.persistencia.views;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.go_exercise.persistencia.DAO.UsuarioDAO;
import com.example.go_exercise.persistencia.GoExerciseDataBase;
import com.example.go_exercise.persistencia.entidades.Usuario;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioDAO usuarioDao;
    private List<Usuario> usuariosLiveData;


    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        usuarioDao = GoExerciseDataBase.getDatabase(application).UsuarioDao();
        usuariosLiveData = usuarioDao.getUsuarios();
    }

    public void insert(Usuario... usuarios) {
        usuarioDao.insert(usuarios);
    }

    public void update(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    public void deleteAll() {
        usuarioDao.deleteAll();
    }

    public Usuario get(String email, String contrasena){ return usuarioDao.getUsuario(email,contrasena);}
}


