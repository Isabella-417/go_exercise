package com.example.go_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.go_exercise.persistencia.views.UsuarioViewModel;
import com.example.go_exercise.persistencia.entidades.Usuario;

public class LoginActivity extends AppCompatActivity {
    EditText correo_electronico, contrasena;

    public UsuarioViewModel usuarioViewModel;
    private Usuario usuario;


    Context context = null;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioViewModel = new UsuarioViewModel(getApplication());
        //usuarioDataBase = Room.databaseBuilder(getApplicationContext(),GoExerciseDataBase.class,"go_exercise").allowMainThreadQueries().build();

    }

    public void onRegister(View view){
        Intent intent = new Intent(this,RegistroActivity.class);
       startActivity(intent);
    }
    public void onModulo (View view){

        Usuario usuario  = null;

        correo_electronico = (EditText) findViewById(R.id.tv_correo_electronico);
        contrasena = (EditText) findViewById(R.id.tv_contrasena);

        boolean vacio = correo_electronico.getText().toString().equals("") ||
                contrasena.getText().toString().equals("") ? true:false;
        if(!vacio){
            usuario = usuarioViewModel.get(correo_electronico.getText().toString(),contrasena.getText().toString());
        }

        if (usuario != null){
            Toast toast =  Toast.makeText(getApplicationContext(),
                    "Bienvenido!", Toast.LENGTH_SHORT);

            toast.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
            toast.show();

            Intent intent = new Intent(this,ModuloActivity.class);
            startActivity(intent);

        }else{

            Toast toast =  Toast.makeText(getApplicationContext(),
                    "El usuario ingresado no se encuentra registrado", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
            toast.show();

        }
    }
}