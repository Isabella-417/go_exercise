package com.example.go_exercise.utilidades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go_exercise.R;

import java.util.List;

public class ContenedorInfoAdapter extends RecyclerView.Adapter<ContenedorInfoAdapter.ViewHolderDatos> {

    List<ScreenItem> listaDatos;

    public ContenedorInfoAdapter(List<ScreenItem> listaDatos) {
        this.listaDatos = listaDatos;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,null,false);
        return new ViewHolderDatos(view);
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

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            icono_contenedor = (ImageView) itemView.findViewById(R.id.iv_icono_contenedor_elemento);
            nombre_contenedor = (TextView) itemView.findViewById(R.id.tv_nombre_contenedor_elemento);
            descripcion_contenedor = (TextView) itemView.findViewById(R.id.tv_descripcion_contenedor_elemento);
        }

        public void asignarDatos(ScreenItem screenItem) {
            icono_contenedor.setImageResource(screenItem.getScreenimg());
            nombre_contenedor.setText(screenItem.getTitle());
            descripcion_contenedor.setText(screenItem.getDescription());
        }
    }
}






