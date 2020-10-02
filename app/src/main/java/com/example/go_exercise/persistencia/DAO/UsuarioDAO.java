package com.example.go_exercise.persistencia.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.go_exercise.persistencia.DataBaseOpenHelper;

public class UsuarioDAO {

    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase db;

    public UsuarioDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
    }
}
