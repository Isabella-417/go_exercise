package com.example.go_exercise.persistencia.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_exercise.persistencia.entidades.RangoDificultad;

import java.util.List;

@Dao
public interface RangoDificultadDAO {

    @Query("SELECT * FROM rango_dificultad")
    List<RangoDificultad> getRangos();

    @Query("SELECT * FROM rango_dificultad WHERE id = :id")
    RangoDificultad getRangoDificultad(int id);

    @Query("SELECT * FROM rango_dificultad WHERE nombre = :nombre")
    RangoDificultad getRangoDificultadByName(String nombre);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(RangoDificultad rangoDificultad);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RangoDificultad... rangoDificultad);

    @Update
    void update(RangoDificultad rangoDificultad);

    @Delete
    void delete(RangoDificultad rangoDificultad);

    @Query("DELETE FROM rango_dificultad")
    void deleteAll();
}
