package com.example.go_exercise.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.go_exercise.R;
import com.example.go_exercise.persistencia.entidades.Ejercicio;
import com.example.go_exercise.utilidades.EjerciciosSeleccionados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class IniciarRutinaFragment extends Fragment{

    public EjerciciosSeleccionados ejerciciosSeleccionados;

    private List<Ejercicio> ejercicios;

    private boolean enPausa = true;
    private Chronometer tiempo;

    private long pausa_compensacion;
    private int intervalo_tiempo = 0;
    private int indice_ejercicio = 0;
    private int contSerie =0;
    private String url_gif = "";


    public IniciarRutinaFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_rutina, container, false);
    }

    public void reiniciarCronometro(View view){
      tiempo.setBase(SystemClock.elapsedRealtime());
      pausa_compensacion = 0;
    }

    public long obtenerMilisegundosRango(int tiempo){
        return TimeUnit.SECONDS.toMillis(tiempo);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Definición de variables
        tiempo = view.findViewById(R.id.cm_cronometro_ejercicio);
        //final GifImageView
        final ImageView gif_ejercicio = view.findViewById(R.id.gifImageView);

        final TextView nombre_ejercicio = (TextView)view.findViewById(R.id.tv_iniciar_rutina_descripcion);
        final Button descripcion_ejercicio = (Button)view.findViewById(R.id.btn_descripcion_ejercicio);


        tiempo.setCountDown(true);

        if (getArguments() != null){
            IniciarRutinaFragmentArgs args = IniciarRutinaFragmentArgs.fromBundle(getArguments());
            if(args.getEjerciciosSeleccionados() != null) {
                ejerciciosSeleccionados = args.getEjerciciosSeleccionados();

            //intervalo_tiempo = ejerciciosSeleccionados.getTiempo() *  ejerciciosSeleccionados.getSeries();
                intervalo_tiempo = ejerciciosSeleccionados.getTiempo();
                ejercicios = ejerciciosSeleccionados.getEjercicios();
                tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo) );

                nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                if (gif_ejercicio != null){
                    url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                    Glide.with(getContext()).load(url_gif).into(gif_ejercicio);
                }
            }
        }

        //detener icono
        Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);

        final FloatingActionButton btn_inicio_rutina = view.findViewById(R.id.fb_pausar_reproduccion);
        final FloatingActionButton btn_adelantar_rutina = view.findViewById(R.id.fb_adelantar_reproduccion);
        final FloatingActionButton btn_retroceder_rutina = view.findViewById(R.id.fb_retroceder_reproduccion);
        final Button btn_home = view.findViewById(R.id.btn_home);
        final NavController navController = Navigation.findNavController(view);


        tiempo.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                try {
                    if (chronometer.getText().toString().equalsIgnoreCase("00:00")) {

                        chronometer.setOnChronometerTickListener(null);
                        chronometer.stop();

                        chronometer.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo +1));
                        chronometer.setOnChronometerTickListener(this);
                        TimeUnit.MILLISECONDS.sleep(100);

                        chronometer.start();


                        if (gif_ejercicio != null){
                            url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                            Glide.with(getContext()).load(url_gif).into(gif_ejercicio);
                        }

                        nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                        descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());
                        indice_ejercicio+=1;

                        if(indice_ejercicio == ejercicios.size()){
                            indice_ejercicio = 0;
                            contSerie +=1;
                        }
                        if (contSerie == ejerciciosSeleccionados.getSeries()){
                            indice_ejercicio = ejercicios.size();
                        }
                    }
                }catch (Exception BoundException){
                    System.out.println("este es el exeption: "+ BoundException);
                    navController.navigate(R.id.homeFragment2);
                    Toast.makeText(getContext(), "Rutina completada", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_inicio_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enPausa){
                    Glide.with(getContext()).load(url_gif).into(gif_ejercicio);
                    enPausa = false;
                    btn_inicio_rutina.setImageResource(R.drawable.icono_pausar);

                    tiempo.setBase(SystemClock.elapsedRealtime()  - pausa_compensacion);
                    tiempo.start();
                }else{
                    Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                    enPausa = true;
                    btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);

                    tiempo.stop();
                    pausa_compensacion = SystemClock.elapsedRealtime() - tiempo.getBase();
                }
            }
        });

        btn_adelantar_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contSerie==(ejerciciosSeleccionados.getSeries() -1) && indice_ejercicio == (ejercicios.size() -1) ){

                    Toast.makeText(getContext(), "Llegaste al final de la Rutina", Toast.LENGTH_SHORT).show();


                }else{

                    if (indice_ejercicio==(ejercicios.size()-1) && contSerie<(ejerciciosSeleccionados.getSeries() -1)) {
                        indice_ejercicio = 0;
                        contSerie += 1;
                        System.out.println("Mostrar tamaño ejercicio: " + (ejercicios.size()-1));
                        System.out.println("Mostrar indice: " + indice_ejercicio);
                        System.out.println("Mostrar serie: " + contSerie);
                        tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo));

                        nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                        descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                        if (gif_ejercicio != null) {
                            url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                            Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                            enPausa = true;
                            tiempo.stop();
                            btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);


                        }
                    }else {

                        if(indice_ejercicio<(ejercicios.size() - 1) && contSerie==(ejerciciosSeleccionados.getSeries() -1)) {

                            indice_ejercicio += 1;
                            System.out.println("noelse Mostrar indice: " + indice_ejercicio);
                            tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo));

                            nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                            descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                            if (gif_ejercicio != null) {
                                url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                                Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                                enPausa = true;
                                tiempo.stop();
                                btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);

                            }
                        }else{

                            if(indice_ejercicio < (ejercicios.size() - 1) && contSerie<(ejerciciosSeleccionados.getSeries() -1)){

                                indice_ejercicio += 1;
                                System.out.println("Else Mostrar indice: " + indice_ejercicio);
                                tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo));

                                nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                                descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                                if (gif_ejercicio != null) {
                                    url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                                    Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                                    enPausa = true;
                                    tiempo.stop();
                                    btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);
                                }
                            }

                        }


                    }


                }

            }


        });

        btn_retroceder_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(contSerie==0 && indice_ejercicio == 0 ){

                    Toast.makeText(getContext(), "Llegaste al Inicio de la Rutina", Toast.LENGTH_SHORT).show();


                }else{

                    if (indice_ejercicio==0 && contSerie>0) {
                        indice_ejercicio = ejercicios.size() - 1;
                        contSerie -= 1;
                        System.out.println("Mostrar tamaño ejercicio: " + (ejercicios.size()-1));
                        System.out.println("Mostrar indice: " + indice_ejercicio);
                        System.out.println("Mostrar serie: " + contSerie);
                        tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo ));

                        nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                        descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                        if (gif_ejercicio != null) {
                            url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                            Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                            enPausa = true;
                            tiempo.stop();
                            btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);


                        }
                    }else {

                        if(indice_ejercicio>0 && contSerie== 0) {

                            indice_ejercicio -= 1;
                            System.out.println("noelse Mostrar indice: " + indice_ejercicio);
                            tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo));

                            nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                            descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                            if (gif_ejercicio != null) {
                                url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                                Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                                enPausa = true;
                                tiempo.stop();
                                btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);

                            }
                        }else{

                            if(indice_ejercicio > 0 && contSerie>0){

                                indice_ejercicio -= 1;
                                System.out.println("Else Mostrar indice: " + indice_ejercicio);
                                tiempo.setBase(SystemClock.elapsedRealtime() + obtenerMilisegundosRango(intervalo_tiempo));

                                nombre_ejercicio.setText(ejercicios.get(indice_ejercicio).getNombre());
                                descripcion_ejercicio.setText(ejercicios.get(indice_ejercicio).getDescripcion_corta());

                                if (gif_ejercicio != null) {
                                    url_gif = ejercicios.get(indice_ejercicio).getRuta_gif();
                                    Glide.with(getContext()).load(url_gif).dontAnimate().into(gif_ejercicio);
                                    enPausa = true;
                                    tiempo.stop();
                                    btn_inicio_rutina.setImageResource(R.drawable.icono_iniciar_rutina);
                                }
                            }

                        }


                    }


                }


            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IniciarRutinaFragmentDirections.ActionIniciarRutinaFragmentToHomeFragment2 accion = IniciarRutinaFragmentDirections.actionIniciarRutinaFragmentToHomeFragment2();
                navController.navigate(accion);
            }
        });

    }

}