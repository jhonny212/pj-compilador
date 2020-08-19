/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AFD;

/**
 *
 * @author jhonny
 */
public class NodoSPS implements Nodo, Cloneable {

    //final int tipo;
    final int min;
    final int max;
    int num;
    String siguientes = "";
  
    public NodoSPS( int min, int max, int num) {
    
        this.max = max;
        this.min = min;
        this.num = num;
    }

    @Override
    public String getValue() {
        return "From "+min+" to "+max;
    }

    

    @Override
    public void recorrer(Nodo href) {
    }
/*
    @Override
    public int getNum() {
        return this.num;
    }*/

    @Override
    public Nodo clone() {

        try {
            return (Nodo) super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return null;
    }

    public int getMin()
    {
        return this.min;
    }
     public int getMax()
    {
        return this.max;
    }

    @Override
    public String getFirst() {
        return String.valueOf(this.num);
    }

    @Override
    public String getLast() {
        return String.valueOf(this.num);
    }

    @Override
    public boolean isLambda() {
        return false;
    }

    @Override
    public void addSiguientes(String x) {
        if(this.siguientes.isEmpty()){
            this.siguientes=x;
        }else{
            this.siguientes+=","+x;
        }
    }

    @Override
    public String getSiguientes() {
        return this.siguientes;
    }

    @Override
    public boolean check() {
        return this.check;
    }

    @Override
    public void setChech(boolean f) {
        check=f;
    }
    
    boolean check=false;

    @Override
    public boolean compare(Nodo x) {
       
        if(x instanceof NodoVal){
            if(x.getValue().length()==1){
                int code=x.getValue().hashCode();
                return (code>= min && code<=max);
            }else{
                return false;
            }
        }else{
            NodoSPS tmp=(NodoSPS)x;
            return ( tmp.min>= min &&tmp.max<=max);
        }
    }

  
}
