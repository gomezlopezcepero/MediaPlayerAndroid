package com.example.paco.practicamediaplayer;


import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MiVista> {

    CustomItemClickListener listener;
    private List<Elemento> listaCanciones;


    public class MiVista extends RecyclerView.ViewHolder {
        public TextView nombre;
        public Button start, pause, stop;

        public MiVista(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombre);
            start = (Button) view.findViewById(R.id.start);
            pause = (Button) view.findViewById(R.id.pause);
            stop = (Button) view.findViewById(R.id.stop);
        }
    }

    public Adaptador(List<Elemento> listaCanciones, CustomItemClickListener listener) {

        this.listaCanciones = listaCanciones;
        this.listener = listener;
    }

    @Override

    public MiVista onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        final MiVista pvh = new MiVista(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View itemView) {
                listener.onItemClick(itemView, pvh.getPosition());
            }

        });




        return pvh;
    }


    @Override
    public void onBindViewHolder(final MiVista holder, final int position) {
        final Elemento cancion = listaCanciones.get(position);


        holder.nombre.setText(cancion.getNombre());

        final MediaPlayer mediaPlayer = new MediaPlayer();

        try{
            mediaPlayer.setDataSource(cancion.getRuta());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Botón escuchar

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }

            }
        });


        //Botón parar

        holder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(cancion.getRuta());
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //Botón pausar

        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaCanciones.size();
    }
}




