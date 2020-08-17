/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AND;

/**
 *
 * @author jhonny
 */
public class NodoSPS implements Nodo, Cloneable {

    final int tipo;
    final int min;
    final int max;
    int num;
    String siguientes = "";

    public NodoSPS(int t, int min, int max, int num) {
        this.tipo = t;
        this.max = max;
        this.min = min;
        this.num = num;
    }

    @Override
    public String getValue() {
        return getSTR();
    }

    String getSTR() {
        String d = "";
        for (int i = min; i <= max; i++) {
            if (d.isEmpty()) {
                d += "" + i;
            } else {
                d += "," + i;
            }

        }
        return d;
    }

    @Override
    public void recorrer(Nodo href) {
    }

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public Nodo clone() {

        try {
            return (Nodo) super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return null;
    }

    @Override
    public void serNum(int x) {
        this.num = x;
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

}
