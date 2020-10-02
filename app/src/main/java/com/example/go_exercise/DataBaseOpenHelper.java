package com.example.go_exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public DataBaseOpenHelper(@Nullable Context context) {
        super(context, UtilitiesDataBase.DATABASE_NAME, null, UtilitiesDataBase.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UtilitiesDataBase.TablaUsuario.CREATE_TABLE_USUARIOS);

    }

    private void insert(SQLiteDatabase db, String email, String name, String password){
        ContentValues registro= new ContentValues();
        registro.put(UtilitiesDataBase.TablaUsuario.EMAIL, email);
        registro.put(UtilitiesDataBase.TablaUsuario.NAME, name);
        registro.put(UtilitiesDataBase.TablaUsuario.PASSWORD, password);

        db.insert(UtilitiesDataBase.TablaUsuario.TABLE_NAME, null, registro);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
