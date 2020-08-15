/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhonny
 */
public class TablaLL1 {

    private TablaDeTransiciones tabla;
    public HashMap<String, String> tablaDeTerminales;
    public Transicion[][] trans = null;
    public Produccion[] listado = null;
    int filas = 0;
    int columnas = 0;

    public TablaLL1(TablaDeTransiciones tablaLALR, HashMap<String, String> tablaDeTerminales, int tm) {
        this.tabla = tablaLALR;
        this.tablaDeTerminales = tablaDeTerminales;
        this.tablaDeTerminales.put("$", this.tablaDeTerminales.size() + ":" + "$");
        this.listado = new Produccion[tm];

    }

    public void init() {
        tabla.init();
        terminales_noTerminales();
        llenarTabla();

        //generarPrs();
        //saveProductions(this.listado, "listado.bin");
        //saveProductions(this.trans, "tabla.bin");
    }

    public void clean() {
        tabla.clean();
        tablaDeTerminales.clear();
        trans = null;
    }

    void terminales_noTerminales() {
        filas = tabla.tablasLL1.size();
        columnas = tablaDeTerminales.size();
        trans = new Transicion[filas][columnas];
    }

    void llenarTabla() {
        /*textoPila += "void methodPila(int numOfPila,String dato){\n"
                + "     switch (numOfPila){\n";*/

        this.tabla.tablasLL1.forEach((tbl) -> {
            /*textoPila += "    case " + tbl.num + ":\n"
                    + "     caso" + tbl.num + "(dato);\n"
                    + "     break;\n";
            textoLL1 += "void caso" + tbl.num + " (String nameOfToken)\n"
                    + "{\n"
                    + "     switch(nameOfToken){\n";*/
            tbl.listado.forEach((sub) -> {

                if (sub.haveNext()) {
                    SymToken tk = sub.producionData.SimbolosProduccion.get(sub.posPunto);
                    int transicion = sub.transicion;
                    String datos[] = tablaDeTerminales.get(tk.token).split(":");
                    if (tk.token.equals("$")) {
                        reduce(sub, tbl);
                    } else if (!tk.token.equals(tk.token.toLowerCase())) {
                        if (trans[tbl.num][Integer.valueOf(datos[0])] == null) {
                            /*textoLL1
                                    += "           case \"" + tk.token + "\":\n"
                                    + "            int numOfTrans$$_" + tk.token + "=" + transicion + ";\n "
                                    + "         break;\n";*/
                            trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(tk, "Go-to", transicion);

                        }

                    } else {
                        if (trans[tbl.num][Integer.valueOf(datos[0])] == null) {
                            /*textoLL1
                                    += "           case \"" + tk.token + "\":\n"
                                    + "            int numOfTrans$$_" + tk.token + "=" + transicion + ";\n "
                                    + "         break;\n";*/
                            trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(tk, "Switch", transicion);

                        }
                    }
                } else {
                    reduce(sub, tbl);
                }
            });
            //textoLL1 += "}\n}\n";
        });
    }

    void reduce(FilaSub sub, Subtabla tbl) {
        TablaDeProduccion tb = tabla.href.get(sub.padre);
        if (tb != null) {
            int cnt = 0;
            String produccion = sub.getPr();
            for (Produccion x : tb.listado) {
                if (x.getKey().equals(produccion)) {
                    String sig[] = sub.siguientes.split(",");
                    for (String d : sig) {
                        String datos[] = tablaDeTerminales.get(d).split(":");
                        trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(null, "reduce", x.num);

                    }
                    break;
                }
                cnt++;
            }
        } else {
            String datos[] = tablaDeTerminales.get("$").split(":");
            trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(null, "ACEPTACION", -1);

        }

    }

    public void saveProductions(Object obj, String name) {
        try {
            FileOutputStream file = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Logger.getLogger(TablaLL1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void generarPrs() {
        tabla.href.values().forEach((x) -> {
            x.listado.stream().map((y) -> {
                this.listado[y.num] = y;
                return y;
            }).forEachOrdered((y) -> {
                this.listado[y.num].setDad(x.produccion);
            });
        });
    }

    public String[] obtenerFila() {
        String FILA[];
        FILA = new String[this.tablaDeTerminales.size() + 1];
        FILA[0] = "No.";
        for (String d : this.tablaDeTerminales.values()) {
            String dato[] = d.split(":");
            FILA[Integer.valueOf(dato[0]) + 1] = dato[1];
        }
        return FILA;
    }

    public String[][] getCeldas() {
        String[][] celdas = new String[filas][columnas + 1];
        for (int i = 0; i < filas; i++) {
            celdas[i][0] = i + "";
            for (int j = 0; j < columnas; j++) {
                try {
                  celdas[i][j + 1] = trans[i][j].tipo + " " + trans[i][j].transicion;
                } catch (Exception ex) {
                    celdas[i][j + 1] = "";
                }
            }
        }
        return celdas;
    }

}
