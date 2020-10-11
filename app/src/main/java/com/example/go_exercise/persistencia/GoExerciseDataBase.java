package com.example.go_exercise.persistencia;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.go_exercise.persistencia.DAO.NecesidadDAO;
import com.example.go_exercise.persistencia.DAO.RangoDificultadDAO;
import com.example.go_exercise.persistencia.DAO.UsuarioDAO;
import com.example.go_exercise.persistencia.entidades.Necesidad;
import com.example.go_exercise.persistencia.entidades.RangoDificultad;
import com.example.go_exercise.persistencia.entidades.Usuario;

@Database(
        entities = {
                Usuario.class,
                Necesidad.class,
                RangoDificultad.class
        },
        version = 1,
        exportSchema = true
)
public abstract class GoExerciseDataBase extends RoomDatabase {

    private static GoExerciseDataBase INSTANCE;
    private static final String NOMBRE_BASE_DATOS = "go_exercise.db";


    public static GoExerciseDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GoExerciseDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GoExerciseDataBase.class, NOMBRE_BASE_DATOS)
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("GoExerciseDataBase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public void clearDb() {
        if (INSTANCE != null) {
            new PopulateDbAsync(INSTANCE).execute();
        }
    }

    public abstract NecesidadDAO  necesidadDao();
    public abstract UsuarioDAO usuarioDao();
    public abstract RangoDificultadDAO rangoDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final NecesidadDAO necesidadDao;
        private final UsuarioDAO usuarioDao;
        private final RangoDificultadDAO rangoDao;

        public PopulateDbAsync(GoExerciseDataBase instance) {
            necesidadDao = instance.necesidadDao();
            usuarioDao = instance.usuarioDao();
            rangoDao = instance.rangoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            necesidadDao.deleteAll();
            usuarioDao.deleteAll();
            rangoDao.deleteAll();

            //usuarios
            Usuario usuario_1 = new Usuario("Isabella Serna","annie@gmail.com","12345");
            Usuario usuario_2 = new Usuario("George Romero","george@gmail.com","12345");

            usuarioDao.insert(usuario_1,usuario_2);

            //necesidades
            Necesidad necesidad_1 = new Necesidad("Reducción de grasa", "");
            Necesidad necesidad_2 = new Necesidad("Tonificación muscular", "");

            necesidadDao.insert(necesidad_1,necesidad_2);

            //rango de dificultad
            RangoDificultad rango_1 = new RangoDificultad("Principiante",20,2);
            RangoDificultad rango_2 = new RangoDificultad("Intermedio",40,4);
            RangoDificultad rango_3 = new RangoDificultad("Experto",60,8);

            rangoDao.insert(rango_1,rango_2,rango_3);


            return null;
        }
    }
}
