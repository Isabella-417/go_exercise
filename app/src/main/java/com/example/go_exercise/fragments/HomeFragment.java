package com.example.go_exercise.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.go_exercise.R;
import com.example.go_exercise.utilidades.VariableGlobales;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


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

                if (variableGlobales.getEnfoque() != null){
                    TextView enfoque = (TextView)view.findViewById(R.id.tv_descripcion_parte_cuerpo_seleccionada);
                    enfoque.setText(variableGlobales.getEnfoque());
                }

                if (variableGlobales.getEnfoque() != null){
                    TextView equipamiento = (TextView)view.findViewById(R.id.tv_descripcion_equipamiento_seleccionada);
                    equipamiento.setText(variableGlobales.getEquipamiento());
                }

                System.out.println("**************Variables actualizadas OBJ : "+args.getVariablesGlobales().toString());

            }

        }

        Button btn_necesidad = view.findViewById(R.id.btn_conf_necesidad);
        Button btn_dificultad = view.findViewById(R.id.btn_conf_dificultad);
        Button btn_parte_ejercitar = view.findViewById(R.id.btn_conf_parte_cuerpo);
        Button btn_equipamiento = view.findViewById(R.id.btn_conf_equipamiento);
        FloatingActionButton btn_iniciar_rutina = view.findViewById(R.id.btn_calcular_tiempo_rutina);

        final NavController navController = Navigation.findNavController(view);

        //Navegación necesidad
        btn_necesidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToNecesidadFragment2 accion = HomeFragmentDirections.actionHomeFragment2ToNecesidadFragment2(variableGlobales);
                navController.navigate(accion);
            }
        });

        //Navegación dificultad
        btn_dificultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToRangoDificultadFragment2 accion = HomeFragmentDirections.actionHomeFragment2ToRangoDificultadFragment2(variableGlobales);
                navController.navigate(accion);
            }
        });

        //Navegación enfoque
        btn_parte_ejercitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToParteEjercitarFragment accion = HomeFragmentDirections.actionHomeFragment2ToParteEjercitarFragment(variableGlobales);
                navController.navigate(accion);
            }
        });

        //Navegación equipamiento
        btn_equipamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragment2ToEquipamientoFragment accion = HomeFragmentDirections.actionHomeFragment2ToEquipamientoFragment(variableGlobales);
                navController.navigate(accion);
            }
        });

        //Inicio de rutina
        btn_iniciar_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String necesidad = variableGlobales.getNecesidad();
                String dificultad = variableGlobales.getRangoDificultad();
                String enfoque = variableGlobales.getEnfoque();
                String equipamiento = variableGlobales.getEquipamiento();

                boolean formulario_diligenciado = necesidad!=null && dificultad!=null && enfoque!=null && equipamiento!=null;

                if(formulario_diligenciado){
                    navController.navigate(R.id.lapsoTiempoRutinaFragment);
                }else{
                    Toast toast =  Toast.makeText(getContext(),
                            "Por favor seleccione todas las opciones", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                    toast.show();
                }
                //HomeFragmentDirections.ActionHomeFragment2ToEquipamientoFragment accion = HomeFragmentDirections.actionHomeFragment2ToEquipamientoFragment(variableGlobales);
                //navController.navigate(accion);
            }
        });


    }
}