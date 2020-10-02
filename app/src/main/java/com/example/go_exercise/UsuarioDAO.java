package com.example.go_exercise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase db;

    public UsuarioDAO(Context context){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
    }
}
