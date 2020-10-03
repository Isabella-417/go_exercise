package com.example.go_exercise.persistencia;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.example.go_exercise.persistencia.DAO.UsuarioDAO;
import com.example.go_exercise.persistencia.DAO.UsuarioDataBase;
import com.example.go_exercise.persistencia.entidades.Usuario;

import java.util.List;

public class VistaUsuario {

    @SuppressLint("StaticFieldLeak")
    private static VistaUsuario sVistaUsuario;

    private UsuarioDAO mUsuarioDao;

    public VistaUsuario(Context context) {
        Context appContext = context.getApplicationContext();
        UsuarioDataBase database = Room.databaseBuilder(appContext,UsuarioDataBase.class,"go_exercise").allowMainThreadQueries().build();
        mUsuarioDao = database.getUsuarioDao();
    }

    public static VistaUsuario get(Context context) {
        if (sVistaUsuario == null) {
            sVistaUsuario = new VistaUsuario(context);
        }
        return sVistaUsuario;
    }

    public List<Usuario> getUsuarios() {
        return mUsuarioDao.getUsuarios();
    }

    public Usuario getUsuario(String email, String contrasena) {
        return mUsuarioDao.getUsuario(email,contrasena);
    }

    public void insertUsuario(Usuario usuario) {
        mUsuarioDao.insert(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        mUsuarioDao.update(usuario);
    }

    public void deleteUsuario(Usuario usuario) {
        mUsuarioDao.delete(usuario);
    }

}
