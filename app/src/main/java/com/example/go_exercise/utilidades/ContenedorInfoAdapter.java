package com.example.go_exercise.utilidades;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go_exercise.R;

import java.util.List;

public class ContenedorInfoAdapter extends RecyclerView.Adapter<ContenedorInfoAdapter.ViewHolderDatos> {

    List<ScreenItem> listaDatos;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    private int tipo_plantilla = 0;


    public ContenedorInfoAdapter(List<ScreenItem> listaDatos, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.listaDatos = listaDatos;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    public ContenedorInfoAdapter(List<ScreenItem> listaDatos, RecyclerViewClickInterface recyclerViewClickInterface, int tipo_plantilla) {
        this.listaDatos = listaDatos;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
        this.tipo_plantilla = tipo_plantilla;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(tipo_plantilla == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,null,false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(tipo_plantilla,null,false);
        }
        ViewHolderDatos vHolder = new ViewHolderDatos(view);

        vHolder.contenedor_elemento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                recyclerViewClickInterface.onItemClick(view);
            }
        });


        return vHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        System.out.println("numero de datos : "+ String.valueOf(listaDatos.size()));
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView icono_contenedor;
        TextView  nombre_contenedor;
        TextView  descripcion_contenedor;
        LinearLayout contenedor_elemento;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            contenedor_elemento = (LinearLayout) itemView.findViewById(R.id.ll_contenedor_informacion_elemento);
            icono_contenedor = (ImageView) itemView.findViewById(R.id.iv_icono_contenedor_elemento);
            nombre_contenedor = (TextView) itemView.findViewById(R.id.tv_nombre_contenedor_elemento);
            descripcion_contenedor = (TextView) itemView.findViewById(R.id.tv_descripcion_contenedor_elemento);

        }

        public void asignarDatos(ScreenItem screenItem) {
            //icono_contenedor.setImageResource(screenItem.getScreenimg());
            nombre_contenedor.setText(screenItem.getTitle());
            if(tipo_plantilla == 0) {
                descripcion_contenedor.setText(screenItem.getDescription());
            }
        }
    }
}






