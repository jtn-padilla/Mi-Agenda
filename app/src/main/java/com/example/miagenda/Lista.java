package com.example.miagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvLista = (ListView) findViewById(R.id.lvLista);
        // Recibe lista de alumnos de Main
        ArrayList<Alumno> alumnos = (ArrayList<Alumno>) getIntent().getSerializableExtra("alumno");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alumnos);
        lvLista.setAdapter(arrayAdapter);

        // Que pasa cuando se le da click al item del ListView
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = "ID: " + position; //+" texto:"+alumnos.get(position).getNombre();
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
