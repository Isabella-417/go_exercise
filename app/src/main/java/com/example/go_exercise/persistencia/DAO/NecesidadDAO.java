package com.example.go_exercise.persistencia.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_exercise.persistencia.entidades.Necesidad;
import com.example.go_exercise.persistencia.entidades.Usuario;

import java.util.List;

@Dao
public interface NecesidadDAO {

    @Query("SELECT * FROM necesidad")
    List<Necesidad> getNecesidades();

    @Query("SELECT * FROM necesidad WHERE id = :id")
    Necesidad getNecesidad(int id);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Necesidad necesidad);

    @Update
    void update(Necesidad necesidad);

    @Delete
    void delete(Necesidad necesidad);
}
