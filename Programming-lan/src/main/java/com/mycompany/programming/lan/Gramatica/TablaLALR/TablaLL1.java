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
import jdk.nashorn.api.tree.ContinueTree;

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

    public void init(String path, String nombre) {
        tabla.init();
        terminales_noTerminales();
        llenarTabla();
        generarPrs();
        tabla.clean();
        var x = new TablaLALR(trans, tabla.tablasLL1, columnas, filas, exito);
        x.saveDatas(this.listado, path, nombre + "_Producciones.bin");
        saveDatas(obtenerFila(), path, nombre + "_Filas.bin");
        this.listado = null;
        this.tablaDeTerminales.clear();
        this.tablaDeTerminales = null;
        x.init(path, nombre);
        tabla.tablasLL1.clear();

    }

    boolean exito = true;

    public void saveDatas(Object obj, String path, String name) {
        try {
            FileOutputStream file = new FileOutputStream(path + "/" + name);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("no here");
        } catch (IOException ex) {
            System.out.println("no" + ex.getMessage());
        }

    }

    public void clean() {
        trans = null;
    }

    void terminales_noTerminales() {
        filas = tabla.tablasLL1.size();
        columnas = tablaDeTerminales.size();
        trans = new Transicion[filas][columnas];
    }

    void llenarTabla() {
        this.tabla.tablasLL1.forEach((tbl) -> {
            tbl.listado.forEach((sub) -> {
                if (sub.haveNext()) {
                    SymToken tk = sub.producionData.SimbolosProduccion.get(sub.posPunto);
                    int transicion = sub.transicion;
                    String datos[] = null;
                    boolean valid = true;
                    if (!tk.token.equals("LAMMBDAAAAA")) {
                        datos = tablaDeTerminales.get(tk.token).split(":");
                        valid = false;
                    }

                    if (!valid) {
                        if (tk.token.equals("$")) {
                            reduce(sub, tbl);
                        } else if (!tk.token.equals(tk.token.toLowerCase())) {
                            if (trans[tbl.num][Integer.valueOf(datos[0])] == null) {
                                trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(null, "Go-to", transicion);
                            } else {
                                if (trans[tbl.num][Integer.valueOf(datos[0])].tipo.equals("Go-to")) {
                                    if (trans[tbl.num][Integer.valueOf(datos[0])].transicion != transicion) {
                                        trans[tbl.num][Integer.valueOf(datos[0])].tipo += "|Go-to" + transicion;
                                        this.exito = false;
                                    }
                                } else {
                                    trans[tbl.num][Integer.valueOf(datos[0])].tipo += "|Go-to" + transicion;
                                    this.exito = false;

                                }

                            }

                        } else {
                            if (trans[tbl.num][Integer.valueOf(datos[0])] == null) {
                                trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(null, "Switch", transicion);
                            } else {
                                if (trans[tbl.num][Integer.valueOf(datos[0])].tipo.equals("Switch")) {
                                    if (trans[tbl.num][Integer.valueOf(datos[0])].transicion != transicion) {
                                        trans[tbl.num][Integer.valueOf(datos[0])].tipo += "|Switch" + transicion;
                                        this.exito = false;
                                    }
                                } else {
                                    trans[tbl.num][Integer.valueOf(datos[0])].tipo += "|Switch" + transicion;
                                    this.exito = false;
                                }

                            }
                        }
                    }else{
                        reduce(sub,tbl);
                    }

                } else {
                    reduce(sub, tbl);
                }
            });
        });
    }

    void reduce(FilaSub sub, Subtabla tbl) {
        TablaDeProduccion tb = tabla.href.get(sub.padre);
        if (tb != null) {
            int cnt=0;
            String produccion = sub.getPr();
            for (Produccion x : tb.listado) {
                
                if (x.getKey().equals(produccion)) {
                    String sig[] = sub.siguientes.split(",");
                    for (String d : sig) {
                        String datos[] = tablaDeTerminales.get(d).split(":");
                        if (trans[tbl.num][Integer.valueOf(datos[0])] == null) {
                            trans[tbl.num][Integer.valueOf(datos[0])] = new Transicion(null, "reduce", x.num);
                        } else {
                            if (trans[tbl.num][Integer.valueOf(datos[0])].tipo.equals("reduce")) {
                                if (trans[tbl.num][Integer.valueOf(datos[0])].transicion != x.num) {
                                    trans[tbl.num][Integer.valueOf(datos[0])].tipo += "|reduce" + x.num;
                                    this.exito = false;
                                }
                            } else {
                                trans[tbl.num][Integer.valueOf(datos[0])].tipo += "|reduce" + x.num;
                                this.exito = false;
                            }

                        }

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

    public String[] obtenerFila() {
        String FILA[];
        FILA = new String[this.tablaDeTerminales.size() + 1];
        FILA[0] = "No.";
        for (String d : this.tablaDeTerminales.values()) {
            String dato[] = d.split(":");
            FILA[Integer.valueOf(dato[0]) + 1] = dato[1];
        }
        System.out.println(Arrays.toString(FILA));
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

    void generarPrs() {
       
        tabla.href.values().forEach((x) -> {
            if (x != null) {
                x.listado.stream().map((y) -> {
                    this.listado[y.num] = y;
                    return y;
                }).forEachOrdered((y) -> {
                    this.listado[y.num].setDad(x.produccion);
                    this.listado[y.num].setTipo(x.tipo);
                });
            }
        });
        
    }
}
