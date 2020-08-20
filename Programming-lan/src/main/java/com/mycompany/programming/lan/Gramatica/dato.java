package com.mycompany.programming.lan.Gramatica;

public class dato {
    public int fila,columna;
    public Object value[];
    public dato(int fila,int columna, Object value[]){
        this.fila=fila;
        this.columna=columna;
        this.value=value;
    }
    public String getValue(){
        return String.valueOf(value[1]);
    }
    public int tipo(){
        return (int)value[0];
    }
}