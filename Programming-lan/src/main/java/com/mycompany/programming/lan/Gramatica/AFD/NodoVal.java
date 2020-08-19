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
public class NodoVal implements Nodo, Cloneable {

    public int num;
    public final String value;

    public NodoVal(int num, String val) {
        this.num = num;
        this.value = val;
    }

    @Override
    public String getValue() {
        
        return this.value;
        
    }

    @Override
    public void recorrer(Nodo href) {
        System.out.println(this.value + " xd");
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
/*
    @Override
    public void serNum(int x) {
        this.num = x;
    }*/

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
        if (this.siguientes.isEmpty()) {
            this.siguientes = x;
        } else {
            this.siguientes += "," + x;
        }
    }
    String siguientes = "";

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
        check = f;
    }

    boolean check = false;

    @Override
    public boolean compare(Nodo x) {
       
        if (x instanceof NodoSPS) {
            return x.compare(this);
        } else {
            return x.getValue().equals(this.value);
        }
    }

    
}
