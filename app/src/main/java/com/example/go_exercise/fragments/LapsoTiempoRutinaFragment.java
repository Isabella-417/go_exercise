package com.example.go_exercise.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go_exercise.R;
import com.example.go_exercise.persistencia.entidades.Ejercicio;
import com.example.go_exercise.persistencia.entidades.LapsoTiempo;
import com.example.go_exercise.persistencia.entidades.RangoDificultad;
import com.example.go_exercise.persistencia.views.EjercicioViewModel;
import com.example.go_exercise.persistencia.views.RangoDificultadViewModel;
import com.example.go_exercise.utilidades.ContenedorInfoAdapter;
import com.example.go_exercise.utilidades.EjerciciosSeleccionados;
import com.example.go_exercise.utilidades.RecyclerViewClickInterface;
import com.example.go_exercise.utilidades.ScreenItem;
import com.example.go_exercise.utilidades.VariableGlobales;

import java.util.ArrayList;
import java.util.List;


public class LapsoTiempoRutinaFragment extends Fragment implements RecyclerViewClickInterface {

    public EjercicioViewModel ejercicioViewModel;
    List<Ejercicio> ejercicios;

    public RangoDificultadViewModel rangoDificultadViewModel;
    RangoDificultad rango;

    VariableGlobales variableGlobales;

    List<LapsoTiempo> lapsos;
    RecyclerView recycler;


    public LapsoTiempoRutinaFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int tiempo = obtenerTiempo()*4*40/60;
       String tiempo_1 = String.valueOf(tiempo);
       String tiempo_2 = String.valueOf(tiempo/2);
       // String tiempo_1 = "hola";
       // String tiempo_2 = "mundo";



        lapsos = new ArrayList<LapsoTiempo>();

        LapsoTiempo lapso_1= new LapsoTiempo(tiempo_1+" minutos", 15);
        LapsoTiempo lapso_2= new LapsoTiempo(tiempo_2+" minutos", 30);
        lapsos.add(lapso_1);
        lapsos.add(lapso_2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lapso_tiempo_rutina, container, false);

        // Carga los datos que se mostrarán en el listado

        recycler = (RecyclerView) view.findViewById(R.id.contenedor_intervalo_tiempo_rutinas);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        List<ScreenItem> lapsos_parsed = parseData(lapsos);

        int numero_layout = R.layout.lista_item_tiempo;

        ContenedorInfoAdapter contenedor_datos = new ContenedorInfoAdapter(lapsos_parsed, this,numero_layout);

        recycler.setAdapter(contenedor_datos);
        return view;

    }

    private List<ScreenItem> parseData(List<LapsoTiempo> lapsos){
        List<ScreenItem> items = new ArrayList<ScreenItem>();

        for (int i = 0; i < lapsos.size(); i++){
            String titulo = lapsos.get(i).getNombre();
            String descripcion = String.valueOf(lapsos.get(i).getMinutos());
            int imagen = 0;
            ScreenItem item = new ScreenItem(titulo,descripcion, "");
            items.add(item);
        }

        return items;
    }

    @Override
    public void onItemClick(View view) {

        //llenar con todos los ejercicios y un rango  - BORRAR -----------
        ejercicioViewModel = new EjercicioViewModel(getActivity().getApplication());
        ejercicios = ejercicioViewModel.getAll();

        rangoDificultadViewModel = new RangoDificultadViewModel(getActivity().getApplication());
        rango = rangoDificultadViewModel.get(1);

        EjerciciosSeleccionados información_rutina = new EjerciciosSeleccionados(ejercicios,rango.getSeries(),rango.getTiempo());
        //---------------------------------------------------------------

        LapsoTiempoRutinaFragmentDirections.ActionLapsoTiempoRutinaFragmentToIniciarRutinaFragment accion = LapsoTiempoRutinaFragmentDirections.actionLapsoTiempoRutinaFragmentToIniciarRutinaFragment(información_rutina);
        final NavController navController = Navigation.findNavController(view);
        navController.navigate(accion);

    }

    @Override
    public void onLongItemClick(int position) {

    }

    public int obtenerTiempo(){
        int tiempo = 0;

        ejercicioViewModel = new EjercicioViewModel(getActivity().getApplication());
        //ejercicios = ejercicioViewModel.getAll();

        ejercicios = ejerciciosSelect(ejercicioViewModel);


        tiempo = ejercicios.size();

        return tiempo;
    }

    public List<Ejercicio> ejerciciosSelect(EjercicioViewModel ejercicioViewModel){

        List<Ejercicio> lista = null;
        if (getArguments() != null) {

            HomeFragmentArgs args = HomeFragmentArgs.fromBundle(getArguments());

            if (args.getVariablesGlobales() != null) {
                variableGlobales = args.getVariablesGlobales();
                String necesidad = variableGlobales.getNecesidad();
                String enfoque = variableGlobales.getEnfoque();
                String equipamento = variableGlobales.getEquipamiento();

                lista = ejercicioViewModel.getEjercicios(necesidad, enfoque, equipamento);
            }
        }
        return lista;
    }






}