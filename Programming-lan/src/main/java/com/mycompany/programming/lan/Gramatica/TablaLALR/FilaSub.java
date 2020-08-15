package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.util.ArrayList;

public class FilaSub {
    final public Produccion producionData;
    final public String padre;
  public String siguientes;
    int posPunto=0;
    public String llaveInicio;
    public FilaSub(Produccion pr,String padre){
        this.producionData=pr;
        this.padre=padre;
        this.siguientes="";
        llaveInicio=producionData.getKey(0);
    }
    public String getLLave(){
        return this.llaveInicio;
    }
    public String getPrint(){
        return this.producionData.getKey(this.posPunto);
    }
    
    public String getNext(int ps){
        return this.producionData.SimbolosProduccion.get(ps).token;
    }

    public boolean haveNext(){
        return this.posPunto<this.producionData.SimbolosProduccion.size();
    }

    public String getNext(){
        return this.getNext(this.posPunto);
    }
    
    public String getLLave(int p){
        return producionData.getKey(p);
    }
    
    public int transicion=-1;
    
    public void addSiguiente(String vector){
    String array[]=vector.split(",");
        for(String d : array) {
           if(!this.siguientes.contains(d)){
               this.siguientes+=","+d;
           }
        }
    }

    public String getPr(){
        return this.producionData.getKey();
    }        

}
