package com.example.miagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView lvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvLista = (ListView) findViewById(R.id.lvLista);
        final ArrayList<Alumno> alumnos = new ArrayList<>();
        Alumno nuevo = (Alumno) getIntent().getSerializableExtra("alumno");
        alumnos.add(nuevo);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alumnos);
        lvLista.setAdapter(arrayAdapter);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = "ID: " + position; //+" texto:"+alumnos.get(position).getNombre();
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
