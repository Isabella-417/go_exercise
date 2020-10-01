package com.example.go_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onRegister(View view){
        Intent intent = new Intent(this,RegistroActivity.class);
       startActivity(intent);
    }
    public void onModulo (View view){
        Intent intent = new Intent(this,ModuloActivity.class);
        startActivity(intent);
    }
}