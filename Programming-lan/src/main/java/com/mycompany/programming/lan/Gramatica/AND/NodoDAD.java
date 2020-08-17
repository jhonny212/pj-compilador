/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AND;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhonny
 */
public class NodoDAD implements Nodo {

    public final String tipo;
    Nodo izq = null;
    Nodo der = null;
    int lasInserted;
    String primeros = "", ultimos = "";
    final boolean isLambda;

    public NodoDAD(String t, Nodo izq, Nodo der, boolean is) {
        this.tipo = t;
        this.der = der;
        this.izq = izq;
        this.isLambda = is;
        addFirst();

    }

    @Override
    public String getValue() {
        return this.tipo + " PRIMEROS " + getFirst() + " ULTIMOS " + getLast();
    }

    @Override
    public void recorrer(Nodo href) {
        System.out.println(href.getFirst());
        return;
        //System.out.println("Nodo padre VAL: " + href.getValue());
        /*if (href instanceof NodoDAD) {
            if (((NodoDAD) href).izq != null) {
                // System.out.println("    izq:" + ((NodoDAD) href).izq.getValue());
                if (((NodoDAD) href).izq instanceof NodoDAD) {
                    recorrer(((NodoDAD) href).izq);
                }
            }
            if (((NodoDAD) href).der != null) {
                //System.out.println("    der:" + ((NodoDAD) href).der.getValue());
                if (((NodoDAD) href).der instanceof NodoDAD) {
                    recorrer(((NodoDAD) href).der);
                }
            }
        }*/

    }

    @Override
    public int getNum() {

        return this.lasInserted;
    }

    @Override
    public void serNum(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addFirst() {
        switch (this.tipo) {
            case "*":
                if (this.der != null) {
                    this.primeros = this.der.getFirst();
                    this.ultimos = this.der.getLast();
                }
                break;
            case ".":
                if (this.izq.isLambda()) {
                    this.primeros = this.izq.getFirst() + "," + this.der.getFirst();
                } else {
                    this.primeros = this.izq.getFirst();
                }
                if (this.der.isLambda()) {
                    this.ultimos = this.izq.getLast() + "," + this.der.getLast();
                } else {
                    this.ultimos = this.der.getLast();
                }

                break;
            case "|":
                if (this.der != null && this.izq != null) {
                    this.primeros = this.izq.getFirst() + "," + this.der.getFirst();
                    this.ultimos = this.izq.getLast() + "," + this.der.getLast();
                } else if (this.der == null && izq != null) {
                    this.primeros = this.izq.getFirst();
                    this.ultimos = this.izq.getLast();
                } else if (this.izq == null && this.der != null) {
                    this.primeros = this.der.getFirst();
                    this.ultimos = this.der.getLast();
                }
                break;
            case "+":
                this.primeros = this.der.getFirst();
                this.ultimos = this.der.getLast();

                break;
        }
    }

    @Override
    public String getFirst() {
        return (this.primeros);
    }

    @Override
    public String getLast() {
        return (this.ultimos);
    }

    @Override
    public boolean isLambda() {
        return this.isLambda;
    }

    public void addNext(ArrayList<Nodo> list) {
        if (this.tipo.equals("*")) {
           // System.out.println("AGREGAR A :" + this.ultimos + " SIGUIENTES-> " + this.primeros);
           add(list,this.ultimos,this.primeros);
        } else if (tipo.equals(".")) {
           // System.out.println("AGREGAR A :" + this.izq.getLast() + " SIGUIENTES-> " + this.der.getFirst());
           add(list,this.izq.getLast(),this.der.getFirst());
        } else if (tipo.equals("+")) {
            //System.out.println("AGREGAR A :" + this.der.getLast() + " SIGUIENTES-> " + this.der.getFirst());
            add(list,this.der.getLast(),this.der.getFirst());
        }
    }

    void add(ArrayList<Nodo> list, String nds, String next) {
        String nd[] = nds.split(",");
        for (String x : nd) {
              list.get(Integer.valueOf(x)).addSiguientes(next);
        }
    }

    @Override
    public void addSiguientes(String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSiguientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean check() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChech(boolean f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
