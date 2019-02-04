package com.example.paco.practicamediaplayer;


import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView reyclerViewUser;
    private RecyclerView.Adapter mAdapter;
    private TextView tv;

    ArrayList<String> canciones = new ArrayList<String>();
    ArrayList<String> rutas = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //establece las relaciones

        reyclerViewUser = (RecyclerView)findViewById(R.id.reyclerViewUser);
        tv = (TextView)findViewById(R.id.textView);


        obtenerCanciones();

        reyclerViewUser.setHasFixedSize(true);
        reyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        initializeAdapter();

    }


    //Accede a la ruta de la carpeta de musica de la tarjeta SD y extrae las rutas de las canciones

    public void obtenerCanciones() {

        File ruta= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

        File[] files = ruta.listFiles();

        for (int i = 0; i < files.length; ++i) {
            rutas.add(files[i].getAbsolutePath());
            canciones.add(files[i].getName());
        }
    }

    //RECYCLERVIEW

    //mete los objetos en el recyclerview y crea el metodo customitemclicklistener

    private void initializeAdapter(){
       mAdapter = new Adaptador(getData(canciones, rutas), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {

            }
        });
        reyclerViewUser.setAdapter(mAdapter);
    }




    // crea objetos de la clase del modelo y los mete en un arraylist

    public List<Elemento> getData(ArrayList<String> canciones, ArrayList<String> rutas) {

        List<Elemento> userModels = new ArrayList<>();

        for(int i=0;i<canciones.size();i++){

            userModels.add(new Elemento(canciones.get(i), rutas.get(i)));
        }


        return userModels;
    }


}
