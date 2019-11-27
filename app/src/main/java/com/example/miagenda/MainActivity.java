package com.example.miagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button anadir;
    CheckBox cbFem,cbMasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    }

    public void validaCampos(View view){
        String nombre = ((EditText)findViewById(R.id.etNombre)).getText().toString();
        String paterno = ((EditText)findViewById(R.id.etApellidoP)).getText().toString();
        String materno = ((EditText)findViewById(R.id.etApellidoM)).getText().toString();
        String numcuenta = ((EditText)findViewById(R.id.etNumCuenta)).getText().toString();
        int genero = validaGenero(); //true=hombre false=mujer

        if(!nombre.isEmpty()){
            if(!paterno.isEmpty()){
                if(!materno.isEmpty()){
                    if(!numcuenta.isEmpty()){
                        if(numcuenta.length() == 10){
                            if(genero != -1){
                                Toast.makeText(this, String.valueOf(genero), Toast.LENGTH_SHORT).show();
                                Alumno nuevo = new Alumno(nombre,paterno,materno,numcuenta,genero);
                                LlamarActivity2(view,nuevo);
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

    public void LlamarActivity2(View view, Alumno nuevo) {
        // Intent a actividad Lista
        Intent int2 = new Intent(getApplicationContext(), Lista.class);
        int2.putExtra("alumno",nuevo);
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

