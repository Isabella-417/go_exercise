package com.example.go_exercise.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.go_exercise.R;
import com.example.go_exercise.persistencia.entidades.Necesidad;
import com.example.go_exercise.persistencia.entidades.RangoDificultad;
import com.example.go_exercise.persistencia.views.NecesidadViewModel;
import com.example.go_exercise.persistencia.views.RangoDificultadViewModel;
import com.example.go_exercise.utilidades.ContenedorInfoAdapter;
import com.example.go_exercise.utilidades.RecyclerViewClickInterface;
import com.example.go_exercise.utilidades.ScreenItem;
import com.example.go_exercise.utilidades.VariableGlobales;

import java.util.ArrayList;
import java.util.List;


public class RangoDificultadFragment extends Fragment implements RecyclerViewClickInterface {

    public RangoDificultadViewModel rangoViewModel;
    List<RangoDificultad> rangos;
    RecyclerView recycler;

    VariableGlobales variableGlobales;


    public RangoDificultadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_necesidad, container, false);

        rangoViewModel = new RangoDificultadViewModel(getActivity().getApplication());
        rangos = rangoViewModel.getAll();

        recycler = (RecyclerView) view.findViewById(R.id.contenedor_necesidades);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ScreenItem> rangos_parsed = parseData(rangos);

        ContenedorInfoAdapter contenedor_datos = new ContenedorInfoAdapter(rangos_parsed,this);

        recycler.setAdapter(contenedor_datos);
        return view;
    }

    private List<ScreenItem> parseData(List<RangoDificultad> rangos){
        List<ScreenItem> items = new ArrayList<ScreenItem>();
        for (int i = 0; i < rangos.size(); i++){
            String titulo = rangos.get(i).getNombre();
            String descripcion =  "No. Series : "+String.valueOf(rangos.get(i).getSeries())+"\nTiempo : "+String.valueOf(rangos.get(i).getTiempo()+" segundos");
            int imagen = 0;
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

            NecesidadFragmentArgs args = NecesidadFragmentArgs.fromBundle(getArguments());
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

        variableGlobales.setRangoDificultad(nombre_valor_escogido);


        RangoDificultadFragmentDirections.ActionRangoDificultadFragment2ToHomeFragment2 accion = RangoDificultadFragmentDirections.actionRangoDificultadFragment2ToHomeFragment2();
        accion.setVariablesGlobales(variableGlobales);

        final NavController navController = Navigation.findNavController(view);

        navController.navigate(accion);

    }

    @Override
    public void onLongItemClick(int position) {

    }
}