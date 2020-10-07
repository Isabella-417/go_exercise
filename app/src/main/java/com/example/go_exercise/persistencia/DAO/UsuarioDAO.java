package com.example.go_exercise.persistencia.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.go_exercise.persistencia.entidades.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM usuarios")
    List<Usuario> getUsuarios();

    @Query("SELECT * FROM usuarios WHERE email = :email and contrasena = :contrasena ")
   Usuario getUsuario(String email, String contrasena);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Usuario usuario);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Usuario... usuarios);

    @Update
    void update(Usuario user);

    @Delete
    void delete(Usuario user);

    @Query("DELETE FROM usuarios")
    void deleteAll();

}
