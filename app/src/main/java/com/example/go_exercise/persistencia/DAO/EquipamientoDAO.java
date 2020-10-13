package com.example.go_exercise.persistencia.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_exercise.persistencia.entidades.Enfoque;
import com.example.go_exercise.persistencia.entidades.Equipamiento;

import java.util.List;

@Dao
public interface EquipamientoDAO {
    @Query("SELECT * FROM equipamiento")
    List<Equipamiento> getEquipamientos();

    @Query("SELECT * FROM equipamiento WHERE id = :id")
    Enfoque getEquipamiento(int id);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Equipamiento equipamiento);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Equipamiento... equipamientos);

    @Update
    void update(Equipamiento equipamiento);

    @Delete
    void delete(Equipamiento equipamiento);

    @Query("DELETE FROM equipamiento")
    void deleteAll();
}
