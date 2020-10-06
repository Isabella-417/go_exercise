package com.example.go_exercise.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.go_exercise.persistencia.DAO.UsuarioDAO;
import com.example.go_exercise.persistencia.entidades.Necesidad;
import com.example.go_exercise.persistencia.entidades.Usuario;

@Database(
        entities = {
                Usuario.class,
        },
        version = 1,
        exportSchema = true
)
public abstract class GoExerciseDataBase extends RoomDatabase {

    public abstract UsuarioDAO getUsuarioDao();
    private static GoExerciseDataBase goExerciseDatabase;

    public static GoExerciseDataBase getInstance(Context context) {
        if (null == goExerciseDatabase) {
            goExerciseDatabase = buildDatabaseInstance(context);
        }
        return goExerciseDatabase;
    }

    private static GoExerciseDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                GoExerciseDataBase.class,
                Constantes.NOMBRE_BASE_DATOS)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        goExerciseDatabase = null;
    }
}
