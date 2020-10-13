package com.example.go_exercise.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go_exercise.R;
import com.example.go_exercise.persistencia.entidades.Enfoque;
import com.example.go_exercise.persistencia.views.EnfoqueViewModel;
import com.example.go_exercise.utilidades.ContenedorInfoAdapter;
import com.example.go_exercise.utilidades.RecyclerViewClickInterface;
import com.example.go_exercise.utilidades.ScreenItem;
import com.example.go_exercise.utilidades.VariableGlobales;

import java.util.ArrayList;
import java.util.List;

public class ParteEjercitarFragment extends Fragment implements RecyclerViewClickInterface {

    public EnfoqueViewModel enfoqueViewModel;
    List<Enfoque> enfoques;
    RecyclerView recycler;

    VariableGlobales variableGlobales;


    public ParteEjercitarFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_parte_ejercitar, container, false);

        // Carga los datos que se mostrarán en el listado

        enfoqueViewModel = new EnfoqueViewModel(getActivity().getApplication());
        enfoques = enfoqueViewModel.getAll();

        recycler = (RecyclerView) view.findViewById(R.id.contenedor_enfoques);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ScreenItem> enfoques_parsed = parseData(enfoques);
        ContenedorInfoAdapter contenedor_datos = new ContenedorInfoAdapter(enfoques_parsed, this);
        recycler.setAdapter(contenedor_datos);
        return view;
    }

    private List<ScreenItem> parseData(List<Enfoque> enfoques){
        List<ScreenItem> items = new ArrayList<ScreenItem>();

        for (int i = 0; i < enfoques.size(); i++){
            String titulo = enfoques.get(i).getNombre();
            String descripcion = enfoques.get(i).getDescripcion();
            int imagen = 0 ;
            ScreenItem item = new ScreenItem(titulo,descripcion, imagen);
            items.add(item);
        }

        return items;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton btn_atras = view.findViewById(R.id.icono_atras);

        if (getArguments() != null){
            System.out.println(getArguments());
            ParteEjercitarFragmentArgs args = ParteEjercitarFragmentArgs.fromBundle(getArguments());
            if(args.getVariablesGlobales() != null){
                variableGlobales =  args.getVariablesGlobales();
            }
        }

        final NavController navController = Navigation.findNavController(view);

        //Navegación home
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.homeFragment2);
            }
        });
    }

    @Override
    public void onItemClick(View view) {
        TextView valor_escogido = (TextView) view.findViewById(R.id.tv_nombre_contenedor_elemento);
        String nombre_valor_escogido = valor_escogido.getText().toString();

        variableGlobales.setEnfoque(nombre_valor_escogido);

        ParteEjercitarFragmentDirections.ActionParteEjercitarFragmentToHomeFragment2 accion = ParteEjercitarFragmentDirections.actionParteEjercitarFragmentToHomeFragment2();
        accion.setVariablesGlobales(variableGlobales);

        final NavController navController = Navigation.findNavController(view);
        navController.navigate(accion);

    }

    @Override
    public void onLongItemClick(int position) {

    }
}