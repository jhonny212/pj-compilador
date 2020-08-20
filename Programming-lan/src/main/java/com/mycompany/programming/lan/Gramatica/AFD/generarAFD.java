/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AFD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jhonny
 */
public class generarAFD implements Serializable{
     private static final long serialversionUID = 
                                 129348938L; 

    ArrayList<Nodo> listado;
    int numState = 0;
    HashMap<String, estado> AFD = new HashMap<String, estado>();
    ArrayList<String> pila = new ArrayList<>();
    String token, estadoInicial;

    public generarAFD(ArrayList<Nodo> listado, Nodo inicio, String name) {
        this.listado = listado;
        this.token = name;
        this.estadoInicial = inicio.getFirst();
        AFD.put(inicio.getFirst(), new estado(inicio.getFirst(), "inicio", numState, inicio.getFirst().contains(String.valueOf((this.listado.size() - 1))), name));
        this.numState++;
        pila.add(this.estadoInicial);

    }

    public void init() {
        for (int i = 0; i < this.pila.size(); i++) {
            String llave = pila.get(i);
            String refs[] = llave.split(",");
            for (int k = 0; k < refs.length; k++) {
                String y = refs[k];
                Nodo x = this.listado.get(Integer.valueOf(y));
                int min = -1, max = -1;
                if (x instanceof NodoSPS) {
                    min = ((NodoSPS) x).getMin();
                    max = ((NodoSPS) x).getMax();
                }
                if (!x.check()) {
                    if (!x.getValue().equals("$")) {
                        String estado = x.getSiguientes();
                        for (int j = k + 1; j < refs.length; j++) {
                            String ref = refs[j];
                            Nodo tmp = this.listado.get(Integer.valueOf(ref));
                            if (tmp.compare(x)) {
                                if (tmp instanceof NodoSPS) {
                                    int aux1 = ((NodoSPS) tmp).getMin();
                                    if (aux1 < min) {
                                        min = aux1;
                                    }
                                    aux1 = ((NodoSPS) tmp).getMax();
                                    if (aux1 > max) {
                                        max = aux1;
                                    }
                                }
                                String sig[] = tmp.getSiguientes().split(",");
                                for (String xx : sig) {
                                    if (!estado.contains(xx)) {
                                        estado += "," + xx;
                                    }
                                }
                                tmp.setChech(true);
                            }
                        }
                        if (!AFD.containsKey(estado)) {

                            estado es = new estado(estado, x.getValue(), this.numState, (estado.contains(String.valueOf((this.listado.size() - 1)))), this.token);
                            AFD.put(estado, es);
                            if (min < 0 || max < 0) {
                                this.AFD.get(llave).addHref(x.getValue(), es);
                            } else {
                                this.AFD.get(llave).addHref(es, min, max);
                            }
                            this.pila.add(estado);
                            this.numState++;

                        } else {
                            estado es = AFD.get(llave);
                            if (min < 0 || max < 0) {
                                es.addHref(x.getValue(), AFD.get(estado));
                            } else {
                                es.addHref(AFD.get(estado), min, max);
                            }
                        }
                    }

                }
                x.setChech(false);

            }
        }
        //print();
        this.pila.clear();
        this.pila = null;
        listado.clear();
    }

    public void clean() {
        this.listado.clear();
        this.listado = null;
    }

    public void print() {
        System.out.println("\n");
        for (estado x : this.AFD.values()) {
            System.out.println("Estado Numero " + x.num + " REFERENCIAS: " + x.aceptacion);
            for (referencia y : x.referencias) {
                System.out.println("    CON " + y.getVal() + " IR A   " + y.y.num);
            }
        }
    }

    public class estado implements Serializable {
 private static final long serialversionUID = 
                                 129348938L; 
        public String llave, nombreToken;
        public String valor;
        public int num;
        public boolean aceptacion;
        ArrayList<referencia> referencias = new ArrayList<>();

        public estado(String key, String val, int num, boolean acpt, String tkn) {
            this.llave = key;
            this.valor = val;
            this.num = num;
            this.aceptacion = acpt;
            this.nombreToken = tkn;
        }

        public estado() {

        }

        public void addHref(String y, estado x) {
            this.referencias.add(new referencia(y, x));
        }

        public void addHref(estado x, int min, int max) {
            this.referencias.add(new referencia(x, min, max));
        }

    }

    public class referencia implements Serializable {
 private static final long serialversionUID = 
                                 129348938L; 
        private String val;
        estado y;

        public referencia(String val, estado x) {
            this.val = val;
            this.y = x;
        }
        int min = -1, max = -1;

        public referencia(estado x, int min, int max) {
            this.y = x;
            
            this.max = max;
            this.min = min;
        }

        public String getVal() {
            return this.val;
        }

        public boolean compare(String dato) {
            if (min >= 0 || max >= 0) {
                int index=String.valueOf(dato.charAt(0)).hashCode();
                if(index>= min && index<=max){
                    this.val=String.valueOf((char)index);
                    return true;
                }else{
                    return false;
                }
            } else {
                return dato.startsWith(this.val);
            }
        }
    }

}
