package com.example.go_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.go_exercise.persistencia.views.UsuarioViewModel;
import com.example.go_exercise.persistencia.entidades.Usuario;

public class RegistroActivity extends AppCompatActivity {

    EditText nombre_usuario, correo_electronico, contrasena;
    public UsuarioViewModel usuarioViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioViewModel = new UsuarioViewModel(getApplication());
    }



    public void onModulo(View view){

        boolean registro_exitoso = false;

        correo_electronico = (EditText) findViewById(R.id.tv_correo_usuario);
        nombre_usuario = (EditText) findViewById(R.id.tv_nombre_usuario);
        contrasena = (EditText) findViewById(R.id.tv_contrasena_usuario);

        boolean vacio =
                correo_electronico.getText().toString().equals("") ||
                contrasena.getText().toString().equals("") ||
                        nombre_usuario.getText().toString().equals("") ? true:false;

        if (!vacio){
            Usuario usuario = new Usuario(nombre_usuario.getText().toString(),
                                        correo_electronico.getText().toString(),
                                        contrasena.getText().toString());
            registro_exitoso = true;
            usuarioViewModel.insert(usuario);
            Intent intent = new Intent(this, ModuloActivity.class);
            startActivity(intent);
        }

        if(!registro_exitoso){
            Toast toast =  Toast.makeText(getApplicationContext(),
                    "Ingrese los datos de registro completamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
            toast.show();

        }

    }
}