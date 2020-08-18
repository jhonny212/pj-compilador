/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AND;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jhonny
 */
public class generarAFD {

    ArrayList<Nodo> listado;
    Nodo inicio;
    int numState = 0;
    HashMap<String, estado> AFD = new HashMap<String, estado>();
    ArrayList<String> pila = new ArrayList<>();

    public generarAFD(ArrayList<Nodo> listado, Nodo inicio) {
        this.listado = listado;
        this.inicio = inicio;
        AFD.put(inicio.getFirst(), new estado(inicio.getFirst(), "inicio", numState, inicio.getFirst().contains(String.valueOf((this.listado.size() - 1)))));
        this.numState++;
        pila.add(inicio.getFirst());
        System.out.println(inicio.getFirst());
        int cnt = 0;
        for (Nodo x : listado) {
            // System.out.println("NODO "+cnt+" SIG "+x.getSiguientes()+" "+x.getValue());
            cnt++;
        }

    }

    public void init() {
        for (int i = 0; i < this.pila.size(); i++) {
            String llave = pila.get(i);
            String refs[] = llave.split(",");
            //System.out.println("\nanalizando [" + llave + "] S" + (i));
            for (int k = 0; k < refs.length; k++) {
                String y = refs[k];
                Nodo x = this.listado.get(Integer.valueOf(y));
                if (!x.check()) {
                    if (!x.getValue().equals("$")) {
                        String estado = x.getSiguientes();
                        String str = y;
                        for (int j = k + 1; j < refs.length; j++) {
                            String ref = refs[j];
                            Nodo tmp = this.listado.get(Integer.valueOf(ref));
                            if (tmp.compare(x)) {
                                str += "," + ref;
                                String sig[] = tmp.getSiguientes().split(",");
                                for (String xx : sig) {
                                    if (!estado.contains(xx)) {
                                        estado += "," + xx;

                                    }
                                }
                                tmp.setChech(true);
                            }
                        }

                        //System.out.println("    siguis-> " + str + " sigus: " + estado);
                        if (!AFD.containsKey(estado)) {
                            estado es = new estado(estado, x.getValue(), this.numState, (estado.contains(String.valueOf((this.listado.size() - 1)))));
                            AFD.put(estado, es);
                            this.AFD.get(llave).addHref(x.getValue(), es);
                            this.pila.add(estado);
                            //  System.out.println("        creando nuevo estado S" + numState + ":" + estado);
                            // System.out.println("        (S" + i + "," + x.getValue() + ") " + numState);
                            this.numState++;

                        } else {
                            estado es = AFD.get(llave);
                            es.addHref(x.getValue(), AFD.get(estado));
                            // System.out.println("        (S" + i + "," + x.getValue() + ") " + ((AFD.get(estado)).num));
                        }
                    }

                }
                x.setChech(false);

            }
        }
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
                System.out.println("    CON " + y.val + " IR A   " + y.y.num);
            }
        }
    }

    public class estado {

        public String llave;
        public String valor;
        public int num, min, max;
        public boolean aceptacion;
        ArrayList<referencia> referencias = new ArrayList<>();

        public estado(String key, String val, int num, boolean acpt) {
            this.llave = key;
            this.valor = val;
            this.num = num;
            this.aceptacion = acpt;
        }

        public estado(String key, String val, int num, int min, int max) {
            this.llave = key;
            this.valor = val;
            this.num = num;
            this.max = max;
            this.min = min;
        }

        public estado() {

        }

        public void addHref(String y, estado x) {
            this.referencias.add(new referencia(y, x));
        }
    }

    public class referencia {

        String val;
        estado y;

        public referencia(String val, estado x) {
            this.val = val;
            this.y = x;
        }
    }

}
