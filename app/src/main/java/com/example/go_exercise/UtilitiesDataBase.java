package com.example.go_exercise;

import android.provider.ContactsContract;
import android.view.inspector.StaticInspectionCompanionProvider;

public class UtilitiesDataBase {
    static final String DATABASE_NAME="go_exercise";
    static final int VERSION = 1;

    public class TablaUsuario{
        static final String TABLE_NAME ="usuarios";
        static final String EMAIL = "email";
        static final String NAME = "nombre";
        static final String PASSWORD = "contrasena";

        static final String CREATE_TABLE_USUARIOS = "CREATE TABLE"+TABLE_NAME+"("+ EMAIL +" TEXT PRIMARY KEY, "+ NAME+" TEXT, "+
                                                    PASSWORD+" TEXT)";

        static final String CONSULTAR_ALL_TABLE ="SELECT * FROM "+TABLE_NAME;


    }
}
