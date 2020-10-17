package com.example.go_exercise.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
            if(args.getEjerciciosSeleccionados() != null){
                ejerciciosSeleccionados =  args.getEjerciciosSeleccionados();

                intervalo_tiempo = ejerciciosSeleccionados.getTiempo() *  ejerciciosSeleccionados.getSeries();
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
                    }
                }catch (Exception BoundException){
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
            }
        });

        btn_retroceder_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });

    }

}