/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

import com.mycompany.programming.lan.Gramatica.AFD.Token;
import com.mycompany.programming.lan.Gramatica.AFD.analizadorLexico;
import com.mycompany.programming.lan.Gramatica.lenguaje;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author jhonny
 */
public class Compilador {

    final Produccion LISTADOOFPRODUCTIONS[];
    final String FILA[];
    final HashMap<Integer, Transicion[]> TRANSICIONES;
    ArrayList<Integer> pilaDetransiciones = new ArrayList<>();
    ArrayList<Token> pilaDetokens = new ArrayList<>();
    analizadorLexico lexer;
    //ArrayList<String> pilaDetokens = new ArrayList<>();
    //String entrada[] = {"ap", "num", "cp", "$"};
    //int contador = 0, counTrans = 0;
    String moves = "";
    Token prev=null;
    public Compilador(analizadorLexico lexer,lenguaje lan){
        this.LISTADOOFPRODUCTIONS=lan.producciones;
        this.FILA=lan.fila;
        this.TRANSICIONES=lan.tablaLALR;
        this.lexer=lexer;
    }   
    public void init() {
        Token tk=lexer.nextToken();
        this.pilaDetransiciones.add(0);
        
        moves  = "START{";
        moves += "\nPILA_TOKENS ADD: empty";
        moves += "\nPILA_TRANS  ADD: " + 0;
        moves += "\nPILA_ENTRADA CHECK: " + tk.getToken();
        moves += "\n}\n";
        
        /*
        moves += "START{";
        moves += "\nPILA_TOKENS ADD: empty";
        moves += "\nPILA_TRANS  ADD: " + 0;
        moves += "\nPILA_ENTRADA CHECK: " + entrada[contador];
        moves += "\n}\n";
        */
        //pila(0, entrada[contador]);
        pila(0, tk);
        
        
    }

  private void pila(int i, Token tk) {
        int busqueda[] = buscar(i, tk.getToken());
        if (busqueda != null) {
            switch (busqueda[1]) {
                case 0:
                    reduce_(busqueda[0],tk);
                    break;
                case 1:
                    switch_(busqueda[0],tk);
                    break;
                case 2:
                   
                    //goTo_(busqueda[0]);
                    break;
            }
        }

    }
   private void pila(int i, Token tk,Token prev) {
        int busqueda[] = buscar(i, tk.getToken());
        if (busqueda != null) {
            switch (busqueda[1]) {
                case 0:
                    reduce_(busqueda[0],tk);
                    break;
                case 1:
                    switch_(busqueda[0],tk);
                    break;
                case 2:
                    goTo_(busqueda[0],prev);
                    break;
            }
        }

    }
   
    int[] buscar(int i, String dato) {
        if (dato.equals("$") && i==1) {
            System.out.println("***************aceptacion"+i);
            return null;
        }
        int[] res = new int[2];
        Transicion[] trs = TRANSICIONES.get(i);
        for (int j = 1; j < FILA.length; j++) {
            Transicion tr = trs[j - 1];
            if (tr != null) {
                if (FILA[j].equals(dato)) {
                    res[0] = tr.transicion;
                    switch (tr.tipo) {
                        case "Reduce":
                            res[1] = 0;
                            break;
                        case "Switch":
                            res[1] = 1;
                            break;
                        case "Go-to":
                            res[1] = 2;

                    }
                    return res;
                }

            }

        }
        return null;
    }

    void switch_(int i,Token tk) {
        this.pilaDetokens.add(tk);
        this.pilaDetransiciones.add(i);
        Token href=lexer.nextToken();
        moves += "SWITCH{";
        moves += "\n    ADD TKS: " + tk.getToken();
        moves += "\n    ADD TRANS: " + i;
        moves += "\n    PILA_ENTRADA CHECK: " + href.getToken();
        moves += "\n}\n";
        
        pila(i, href);
       

    }

    void reduce_(int i,Token w) {
        Produccion x = LISTADOOFPRODUCTIONS[i];
        moves += "REDUCE{";
        if(!x.isLambda){
        for (int j = 0; j < x.SimbolosProduccion.size(); j++) {
            int tm = this.pilaDetokens.size() - 1;
            int tm2 = this.pilaDetransiciones.size() - 1;
            moves += "\n    REMOVE TKS:" + this.pilaDetokens.get(tm).getToken();
            moves += "\n    REMOVE TRANS:" + this.pilaDetransiciones.get(tm2);
            this.pilaDetokens.remove(tm);
            this.pilaDetransiciones.remove(tm2);
        }
        }
        moves += "\n    ADD TKS:" + x.padre;
        
        this.pilaDetokens.add(new Token(x.padre,x.padre));
        int y = this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1);
        Token z = this.pilaDetokens.get(this.pilaDetokens.size() - 1);
        moves += "\n}\n";
        pila(y,z,w);

    }

    void goTo_(int i,Token tk) {
        moves += "GO_TO{";
        moves += "\n    ADD TRANS: " + i;
        moves += "\n}\n";
        this.pilaDetransiciones.add(i);
        pila(this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1), tk);
    }
   
    
    /*private void pila(int i, String string) {
        int busqueda[] = buscar(i, string);
        if (busqueda != null) {
            switch (busqueda[1]) {
                case 0:
                    reduce_(busqueda[0]);
                    break;
                case 1:
                    switch_(busqueda[0]);
                    break;
                case 2:
                    goTo_(busqueda[0]);
                    break;
            }
        }

    }
   
    int[] buscar(int i, String dato) {
        if (dato.equals("$")) {
            System.out.println("aceptacion");
            return null;
        }
        int[] res = new int[2];
        Transicion[] trs = TRANSICIONES.get(i);
        for (int j = 1; j < FILA.length; j++) {
            Transicion tr = trs[j - 1];
            if (tr != null) {
                if (FILA[j].equals(dato)) {
                    res[0] = tr.transicion;
                    switch (tr.tipo) {
                        case "Reduce":
                            res[1] = 0;
                            break;
                        case "Switch":
                            res[1] = 1;
                            break;
                        case "Go-to":
                            res[1] = 2;

                    }
                    return res;
                }

            }

        }
        return null;
    }

    void switch_(int i) {
        this.pilaDetokens.add(entrada[contador]);
        this.pilaDetransiciones.add(i);
        moves += "SWITCH{";
        moves += "\nADD TKS: " + entrada[contador];
        moves += "\nADD TRANS: " + i;
        moves += "\nPILA_ENTRADA CHECK: " + entrada[contador + 1];
        moves += "\n}\n";
        contador++;
        pila(i, entrada[contador]);

    }

    void reduce_(int i) {
        Produccion x = LISTADOOFPRODUCTIONS[i];
        moves += "REDUCE{";
        for (int j = 0; j < x.SimbolosProduccion.size(); j++) {
            int tm = this.pilaDetokens.size() - 1;
            int tm2 = this.pilaDetransiciones.size() - 1;
            moves += "\nREMOVE TKS:" + this.pilaDetokens.get(tm);
            moves += "\nREMOVE TRANS:" + this.pilaDetransiciones.get(tm2);
            this.pilaDetokens.remove(tm);
            this.pilaDetransiciones.remove(tm2);
        }
        moves += "\nADD TKS:" + x.padre;
        this.pilaDetokens.add(x.padre);
        int y = this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1);
        String z = this.pilaDetokens.get(this.pilaDetokens.size() - 1);
        moves += "\n}\n";
        pila(y, z);

    }

    void goTo_(int i) {
        moves += "GO_TO{";
        moves += "\nADD TRANS: " + i;
        moves += "\n}\n";
        this.pilaDetransiciones.add(i);
        pila(this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1), entrada[contador]);
    }
     */

}
