package com.example.miagenda;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre, paterno,materno,numcuenta;
    private int genero;

    public Alumno(String nombre, String paterno,String materno, String numcuenta, int genero) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.numcuenta = numcuenta;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return getNombre()+" "+getPaterno()+" "+getMaterno();
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }



}
