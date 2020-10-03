package com.example.go_exercise.persistencia.DAO;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.inspector.StaticInspectionCompanionProvider;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.go_exercise.persistencia.Constantes;
import com.example.go_exercise.persistencia.entidades.Usuario;

@Database(entities = {Usuario.class}, version = 1, exportSchema = true)
public abstract class UsuarioDataBase extends RoomDatabase {

    public abstract UsuarioDAO getUsuarioDao();
    private static UsuarioDataBase usuarioDatabase;

    public static UsuarioDataBase getInstance(Context context) {
        if (null == usuarioDatabase) {
            usuarioDatabase = buildDatabaseInstance(context);
        }
        return usuarioDatabase;
    }

    private static UsuarioDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                UsuarioDataBase.class,
                Constantes.NOMBRE_BASE_DATOS)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        usuarioDatabase = null;
    }
}
