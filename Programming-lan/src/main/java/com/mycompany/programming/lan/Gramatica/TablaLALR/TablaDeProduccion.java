package com.mycompany.programming.lan.Gramatica.TablaLALR;


import java.io.Serializable;
import java.util.ArrayList;

public class TablaDeProduccion {
    public ArrayList<Produccion> listado;
    public String produccion;
    public String primeros;
    public boolean isLambda;
    public TablaDeProduccion(ArrayList<Produccion> listado, String produccion) {
        this.listado = listado;
        this.produccion = produccion;
        this.primeros="";
        this.isLambda=false;
     
    }

      
    public void addSiguiente(String vector){
    String array[]=vector.split(",");
        for(String d : array) {
           if(!this.primeros.contains(d)){
               if(!this.primeros.isEmpty()){
                this.primeros+=","+d;
               }else{
               this.primeros+=d;
               }
           }
        }
    }


}
