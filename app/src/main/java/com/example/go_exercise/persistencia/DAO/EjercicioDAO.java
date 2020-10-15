package com.example.go_exercise.persistencia.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_exercise.persistencia.entidades.Ejercicio;

import java.util.List;

@Dao
public interface EjercicioDAO  {

    @Query("SELECT * FROM ejercicio")
    List<Ejercicio> getEjercicios();

    @Query("SELECT * FROM ejercicio WHERE necesidad = :necesidad and enfoque = :enfoque and equipamento = :equipamento ")
    List<Ejercicio> getEjercicioSelect(String  necesidad, String enfoque, String equipamento);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Ejercicio ejercicio);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Ejercicio... ejercicio);

    @Update
    void update(Ejercicio ejercicio);

    @Delete
    void delete(Ejercicio ejercicio);

    @Query("DELETE FROM ejercicio")
    void deleteAll();
}
