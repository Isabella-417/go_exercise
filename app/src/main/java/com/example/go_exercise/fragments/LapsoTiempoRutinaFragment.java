package com.example.go_exercise.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.go_exercise.R;
import com.example.go_exercise.persistencia.entidades.LapsoTiempo;
import com.example.go_exercise.persistencia.entidades.Necesidad;
import com.example.go_exercise.persistencia.views.NecesidadViewModel;
import com.example.go_exercise.utilidades.ContenedorInfoAdapter;
import com.example.go_exercise.utilidades.RecyclerViewClickInterface;
import com.example.go_exercise.utilidades.ScreenItem;

import java.util.ArrayList;
import java.util.List;


public class LapsoTiempoRutinaFragment extends Fragment implements RecyclerViewClickInterface {

    List<LapsoTiempo> lapsos;
    RecyclerView recycler;


    public LapsoTiempoRutinaFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lapsos = new ArrayList<LapsoTiempo>();

        LapsoTiempo lapso_1= new LapsoTiempo("15 minutos", 10);
        LapsoTiempo lapso_2= new LapsoTiempo("30 minutos", 30);
        LapsoTiempo lapso_3= new LapsoTiempo("45 minutos", 60);
        lapsos.add(lapso_1);
        lapsos.add(lapso_2);
        lapsos.add(lapso_3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lapso_tiempo_rutina, container, false);

        // Carga los datos que se mostrarán en el listado

        recycler = (RecyclerView) view.findViewById(R.id.contenedor_intervalo_tiempo_rutinas);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ScreenItem> lapsos_parsed = parseData(lapsos);

        ContenedorInfoAdapter contenedor_datos = new ContenedorInfoAdapter(lapsos_parsed, this);

        recycler.setAdapter(contenedor_datos);
        return view;

    }

    private List<ScreenItem> parseData(List<LapsoTiempo> lapsos){
        List<ScreenItem> items = new ArrayList<ScreenItem>();

        for (int i = 0; i < lapsos.size(); i++){
            String titulo = lapsos.get(i).getNombre();
            String descripcion = String.valueOf(lapsos.get(i).getMinutos());
            int imagen = 0;
            ScreenItem item = new ScreenItem(titulo,descripcion, imagen);
            items.add(item);
        }

        return items;
    }

    @Override
    public void onItemClick(View view) {
        final NavController navController = Navigation.findNavController(view);

        navController.navigate(R.id.iniciarRutinaFragment);

    }

    @Override
    public void onLongItemClick(int position) {

    }
}