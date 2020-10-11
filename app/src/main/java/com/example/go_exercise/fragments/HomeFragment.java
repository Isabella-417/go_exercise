package com.example.go_exercise.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.go_exercise.R;
import com.example.go_exercise.utilidades.VariableGlobales;

public class HomeFragment extends Fragment {

    public  VariableGlobales variableGlobales = new VariableGlobales();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Obtiene la información de configuración global de la rutina
        if (getArguments() != null){

            HomeFragmentArgs args = HomeFragmentArgs.fromBundle(getArguments());

            if(args.getVariablesGlobales() != null){
                variableGlobales = args.getVariablesGlobales();

                if (variableGlobales.getNecesidad() != null){
                    TextView necesidad = (TextView)view.findViewById(R.id.tv_descripcion_necesidad_seleccionada);
                    necesidad.setText(variableGlobales.getNecesidad());
                }

                if (variableGlobales.getRangoDificultad() != null){
                    TextView rango_dificultad = (TextView)view.findViewById(R.id.tv_descripcion_dificultad_seleccionada);
                    rango_dificultad.setText(variableGlobales.getRangoDificultad());
                }

                System.out.println("**************Variables actualizadas OBJ : "+args.getVariablesGlobales().toString());

            }

        }

        Button btn_necesidad = view.findViewById(R.id.btn_conf_necesidad);
        Button btn_dificultad = view.findViewById(R.id.btn_conf_dificultad);
        Button btn_parte_ejercitar = view.findViewById(R.id.btn_conf_parte_cuerpo);
        Button btn_equipamiento = view.findViewById(R.id.btn_conf_equipamiento);

        final NavController navController = Navigation.findNavController(view);

        //Navegación necesidad
        btn_necesidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToNecesidadFragment2 accion = HomeFragmentDirections.actionHomeFragment2ToNecesidadFragment2(variableGlobales);
                navController.navigate(accion);
                //navController.navigate(R.id.necesidadFragment2);
            }
        });

        //Navegación dificultad
        btn_dificultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToRangoDificultadFragment2 accion = HomeFragmentDirections.actionHomeFragment2ToRangoDificultadFragment2(variableGlobales);
                navController.navigate(accion);
                //navController.navigate(R.id.rangoDificultadFragment2);
            }
        });


        //Navegación parte del cuerpo a ejercitar
        btn_parte_ejercitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.parteEjercitarFragment);
            }
        });

        //Navegación equipamiento
        btn_equipamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.equipamientoFragment);
            }
        });


    }
}