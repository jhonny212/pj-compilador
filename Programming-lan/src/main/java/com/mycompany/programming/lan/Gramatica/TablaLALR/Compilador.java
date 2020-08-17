/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

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

    Produccion LISTADOOFPRODUCTIONS[];
    String FILA[];
    HashMap<Integer, Transicion[]> TRANSICIONES;
    ArrayList<Integer> pilaDetransiciones = new ArrayList<>();
    ArrayList<String> pilaDetokens = new ArrayList<>();
    String entrada[] = {"ap", "num", "cp", "$"};
    int contador = 0, counTrans = 0;
    String moves = "";

    public void init() {
        leerBin(1, "listadoDeProducciones.bin");
        leerBin(2, "listadoDeFilas.bin");
        leerBin(3, "tablaLALR.bin");
        
        this.pilaDetransiciones.add(0);
        
        moves += "START{";
        moves += "\nPILA_TOKENS ADD: empty" ;
        moves += "\nPILA_TRANS  ADD: " + 0;
        moves += "\nPILA_ENTRADA CHECK: "+entrada[contador];
        moves += "\n}\n";
        pila(0, entrada[contador]);
        System.out.println(moves);
    }

    private void leerBin(int opc, String name) {
        try {
            FileInputStream file = new FileInputStream(new File(name));
            ObjectInputStream in = new ObjectInputStream(file);
            switch (opc) {
                case 1:
                    LISTADOOFPRODUCTIONS = (Produccion[]) in.readObject();
                    break;
                case 2:
                    FILA = (String[]) in.readObject();
                    break;
                case 3:
                    TRANSICIONES = (HashMap<Integer, Transicion[]>) in.readObject();
                    break;
            }
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    private void pila(int i, String string) {
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
        moves += "\nPILA_ENTRADA CHECK: " + entrada[contador+1];
        moves += "\n}\n";
        contador++;
        pila(i, entrada[contador]);

    }

    void reduce_(int i) {
        Produccion x = LISTADOOFPRODUCTIONS[i];
        moves+="REDUCE{";
        for (int j = 0; j < x.SimbolosProduccion.size(); j++) {
            int tm=this.pilaDetokens.size() - 1;
            int tm2=this.pilaDetransiciones.size()-1;
            moves+="\nREMOVE TKS:"+this.pilaDetokens.get(tm);
            moves+="\nREMOVE TRANS:"+this.pilaDetransiciones.get(tm2);
            this.pilaDetokens.remove(tm);
            this.pilaDetransiciones.remove(tm2);
        }
        moves+="\nADD TKS:"+x.padre;
        this.pilaDetokens.add(x.padre);
        int y = this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1);
        String z = this.pilaDetokens.get(this.pilaDetokens.size() - 1);
        moves+="\n}\n";
        pila(y, z);

    }

    void goTo_(int i) {
        moves+="GO_TO{";
        moves+="\nADD TRANS: "+i;
        moves+="\n}\n";
        this.pilaDetransiciones.add(i);
        pila(this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1), entrada[contador]);
    }

}
