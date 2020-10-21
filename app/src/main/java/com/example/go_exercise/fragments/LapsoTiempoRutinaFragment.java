package com.example.go_exercise.fragments;

import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.go_exercise.utilidades.SelectExcercise;
import com.example.go_exercise.utilidades.VariableGlobales;

import java.util.ArrayList;
import java.util.List;


public class LapsoTiempoRutinaFragment extends Fragment implements RecyclerViewClickInterface {

    public EjercicioViewModel ejercicioViewModel;
    List<Ejercicio> ejercicios;



    public RangoDificultadViewModel rangoDificultadViewModel;
    RangoDificultad rango;

    VariableGlobales variableGlobales;

    List<Ejercicio> listaEjercios;

    List<SelectExcercise> listaLapsos;

    List<LapsoTiempo> lapsos;
    RecyclerView recycler;


    public LapsoTiempoRutinaFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ejercicioViewModel = new EjercicioViewModel(getActivity().getApplication());
        if (getArguments() != null) {

            HomeFragmentArgs args = HomeFragmentArgs.fromBundle(getArguments());

            if (args.getVariablesGlobales() != null) {
                variableGlobales = args.getVariablesGlobales();
            }
        }

        rangoSelect();
        ejerciciosSelect();

        int tiempo = listaEjercios.size();
        System.out.println("este es el tiempo escogido: "+String.valueOf(tiempo));
       // String tiempo_1 = "hola";
       // String tiempo_2 = "mundo";
        lapsos = new ArrayList<LapsoTiempo>();
        listaLapsos = new ArrayList<SelectExcercise>();


        agregarLapsos(lapsos,tiempo,listaLapsos);


    }

    public void agregarLapsos(List<LapsoTiempo> lapsos, int tiempo, List<SelectExcercise> listaLapsos){

        if (tiempo>=3){
            int tiempo1 = (tiempo/3)*rango.getSeries()*rango.getTiempo()/60;
            int tiempo2 = (2*tiempo/3)*rango.getSeries()*rango.getTiempo()/60;
            int tiempo3 = tiempo*rango.getSeries()*rango.getTiempo()/60;


            String tiempo_1 = String.valueOf(tiempo1);
            String tiempo_2 = String.valueOf(tiempo2);
            String tiempo_3 = String.valueOf(tiempo3);

            LapsoTiempo lapso_1= new LapsoTiempo(tiempo_1+" minutos", tiempo1);
            LapsoTiempo lapso_2= new LapsoTiempo(tiempo_2+" minutos", tiempo2);
            LapsoTiempo lapso_3= new LapsoTiempo(tiempo_3+" minutos", tiempo3);

            lapsos.add(lapso_1);
            lapsos.add(lapso_2);
            lapsos.add(lapso_3);

            int cant1 = listaEjercios.size()/3;
            int cant2 = 2*listaEjercios.size()/3;
            int cant3 = listaEjercios.size();
            System.out.println("Mostar lista ejercicios de lapso: "+listaEjercios.size());
            SelectExcercise select1 = new SelectExcercise(tiempo_1,cant1);
            SelectExcercise select2 = new SelectExcercise(tiempo_2,cant2);
            SelectExcercise select3 = new SelectExcercise(tiempo_3,cant3);

            listaLapsos.add(select1);
            listaLapsos.add(select2);
            listaLapsos.add(select3);
        }
        if (tiempo==2){
            int tiempo1 = (tiempo/2)*rango.getSeries()*rango.getTiempo()/60;
            int tiempo2 = tiempo*rango.getSeries()*rango.getTiempo()/60;

            String tiempo_1 = String.valueOf(tiempo1);
            String tiempo_2 = String.valueOf(tiempo2);

            LapsoTiempo lapso_1= new LapsoTiempo(tiempo_1+" minutos", tiempo1);
            LapsoTiempo lapso_2= new LapsoTiempo(tiempo_2+" minutos", tiempo2);

            lapsos.add(lapso_1);
            lapsos.add(lapso_2);

            int cant1 = listaEjercios.size()/2;
            int cant2 = listaEjercios.size();
            SelectExcercise select1 = new SelectExcercise(tiempo_1,cant1);
            SelectExcercise select2 = new SelectExcercise(tiempo_2,cant2);
            listaLapsos.add(select1);
            listaLapsos.add(select2);
        }

        if (tiempo>0){
            int tiempo1 = tiempo*rango.getSeries()*rango.getTiempo()/60;

            String tiempo_1 = String.valueOf(tiempo1);

            LapsoTiempo lapso_1= new LapsoTiempo(tiempo_1+" minutos", tiempo1);

            lapsos.add(lapso_1);

            int cant1 = listaEjercios.size();
            SelectExcercise select1 = new SelectExcercise(tiempo_1,cant1);
            listaLapsos.add(select1);

        }
        if(tiempo == 0){
            int tiempo1 = tiempo;

            LapsoTiempo lapso_1= new LapsoTiempo("Ups! No Hay Ejercicios", tiempo1);

            lapsos.add(lapso_1);
        }




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



        TextView valor_escogido = (TextView) view.findViewById(R.id.tv_nombre_contenedor_elemento);
        String nombre_valor_escogido = String.valueOf(valor_escogido.getText().charAt(0));
        System.out.println("Actual seleccion item: "+nombre_valor_escogido);


       // ejercicioViewModel = new EjercicioViewModel(getActivity().getApplication());
        //ejercicios = ejercicioViewModel.getAll();
        //llenar con todos los ejercicios y un rango  - BORRAR -----------



        if(nombre_valor_escogido.equals("U")){

            final NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.homeFragment2);
            Toast.makeText(getContext(), "Por favor seleccione otra configuración", Toast.LENGTH_SHORT).show();



        }else{
            ejercicios = asignarEjercicios(nombre_valor_escogido);
            System.out.println("Este es el ejercicio: "+ejercicios.size());

            EjerciciosSeleccionados información_rutina = new EjerciciosSeleccionados(ejercicios,rango.getSeries(),rango.getTiempo());


            //---------------------------------------------------------------

            LapsoTiempoRutinaFragmentDirections.ActionLapsoTiempoRutinaFragmentToIniciarRutinaFragment accion = LapsoTiempoRutinaFragmentDirections.actionLapsoTiempoRutinaFragmentToIniciarRutinaFragment(información_rutina);
            final NavController navController = Navigation.findNavController(view);
            navController.navigate(accion);

        }




    }

    public List<Ejercicio> asignarEjercicios(String nombre_valor_escogido){
        List<Ejercicio> ejerciciosSel;
        ejerciciosSel = new ArrayList<Ejercicio>();
        for (int i = 0; i<listaLapsos.size(); i++){
           // System.out.println("ojo minuto de la lista: "+listaLapsos.get(i).getMinutos());
            if(nombre_valor_escogido.equals(listaLapsos.get(i).getMinutos())){
                System.out.println("valor escogido: "+nombre_valor_escogido);
                System.out.println("valosr lista: "+listaLapsos.get(i).getMinutos());

                for(int j=0; j<=listaLapsos.get(i).getCantEjercicios(); j++){
                    System.out.println("Alguno nombre: "+listaEjercios.get(j).getNombre());
                    ejerciciosSel.add(listaEjercios.get(j));
                }

                return ejerciciosSel;



            }
        }
        return ejerciciosSel;


    }

    @Override
    public void onLongItemClick(int position) {

    }

    public void ejerciciosSelect(){


                String necesidad = variableGlobales.getNecesidad();
                String enfoque = variableGlobales.getEnfoque();
                String equipamento = variableGlobales.getEquipamiento();

                listaEjercios = ejercicioViewModel.getEjercicios(necesidad, enfoque, equipamento);


    }
    public  void rangoSelect(){

        String seleccion = variableGlobales.getRangoDificultad();
        rangoDificultadViewModel = new RangoDificultadViewModel(getActivity().getApplication());
        rango = rangoDificultadViewModel.getByName(seleccion);

    }






}