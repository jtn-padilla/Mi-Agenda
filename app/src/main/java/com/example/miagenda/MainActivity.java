package com.example.miagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button anadir, verAgenda;
    CheckBox cbFem,cbMasc;
    ArrayList<Alumno> alumnos = new ArrayList<>();
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // No se logro persistencia :(
        /*sp = getPreferences(Context.MODE_PRIVATE);
        editor = sp.edit();
        Gson gson = new Gson();
        String json = sp.getString("lista", "");
        Type founderListType = new TypeToken<ArrayList<Alumno>>(){}.getType();
        ArrayList<Alumno> alumnos = gson.fromJson(json, founderListType);*/

        controlaGenero();

        // Registro, se validan campos
        anadir = (Button) findViewById(R.id.btnAnadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCampos(v);
                borraCampos();
            }
        });

        verAgenda = (Button) findViewById(R.id.btnVerAgenda);
        verAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LlamarActivity2();
            }
        });
    }


    public void validaCampos(View view){
        String nombre = ((EditText)findViewById(R.id.etNombre)).getText().toString();
        String paterno = ((EditText)findViewById(R.id.etApellidoP)).getText().toString();
        String materno = ((EditText)findViewById(R.id.etApellidoM)).getText().toString();
        String numcuenta = ((EditText)findViewById(R.id.etNumCuenta)).getText().toString();
        int genero = validaGenero(); //1=hombre 0=mujer

        if(!nombre.isEmpty()){
            if(!paterno.isEmpty()){
                if(!materno.isEmpty()){
                    if(!numcuenta.isEmpty()){
                        if(numcuenta.length() == 10){
                            if(genero != -1){
                                Alumno nuevo = new Alumno(nombre,paterno,materno,numcuenta,genero);
                                //perstAlumno(nuevo);
                                alumnos.add(nuevo);
                                Toast.makeText(this, nombre + MainActivity.this.getString(R.string.Anadido) , Toast.LENGTH_SHORT).show();
                                // No se logro persistencia :(
                                /*Gson gson = new Gson();
                                String json = gson.toJson(alumnos);
                                editor.putString("lista", json);
                                editor.commit();*/
                            }
                            else{
                                Toast.makeText(this, MainActivity.this.getString(R.string.GeneroMal), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(this, MainActivity.this.getString(R.string.DigitosMal), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(this, MainActivity.this.getString(R.string.NumFalta), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this,  MainActivity.this.getString(R.string.ApellidoMFalta), Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,MainActivity.this.getString(R.string.ApellidoPFalta) , Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,MainActivity.this.getString(R.string.NombreFalta) , Toast.LENGTH_SHORT).show();
        }
    }

    public void LlamarActivity2() {
        // Intent a actividad Lista
        Intent int2 = new Intent(getApplicationContext(), Lista.class);
        int2.putExtra("alumno",alumnos);
        startActivity(int2);
    }


    public int validaGenero(){
        if(((CheckBox) findViewById(R.id.cbMasculino)).isChecked())
            return 1;
        else if(((CheckBox) findViewById(R.id.cbFemenino)).isChecked())
            return 0;
        else
            return -1;
    }

    public void controlaGenero(){
        // Marca solo el checkbox de fem si se presiona fem
        cbFem = (CheckBox) findViewById(R.id.cbFemenino);
        cbFem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbMasc.setChecked(false);
                }
            }
        });

        // Marca solo el checkbox de masc si se presiona masc
        cbMasc = (CheckBox) findViewById(R.id.cbMasculino);
        cbMasc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbFem.setChecked(false);
                }
            }
        });
    }

    public void borraCampos(){
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        EditText etPaterno = (EditText) findViewById(R.id.etApellidoP);
        EditText etMaterno = (EditText) findViewById(R.id.etApellidoM);
        EditText etNumCuenta = (EditText) findViewById(R.id.etNumCuenta);
        CheckBox cbMasc = (CheckBox) findViewById(R.id.cbMasculino);
        CheckBox cbFem = (CheckBox) findViewById(R.id.cbFemenino);

        etNombre.setText("");
        etPaterno.setText("");
        etMaterno.setText("");
        etNumCuenta.setText("");
        cbMasc.setChecked(false);
        cbFem.setChecked(false);
    }

}

