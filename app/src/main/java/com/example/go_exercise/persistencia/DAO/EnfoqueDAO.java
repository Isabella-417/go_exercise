package com.example.go_exercise.persistencia.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_exercise.persistencia.entidades.Enfoque;
import com.example.go_exercise.persistencia.entidades.Necesidad;

import java.util.List;

@Dao
public interface EnfoqueDAO {

    @Query("SELECT * FROM enfoque")
    List<Enfoque> getEnfoques();

    @Query("SELECT * FROM enfoque WHERE id = :id")
    Enfoque getEnfoque(int id);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Enfoque enfoque);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Enfoque... enfoques);

    @Update
    void update(Enfoque enfoque);

    @Delete
    void delete(Enfoque enfoque);

    @Query("DELETE FROM enfoque")
    void deleteAll();
}
