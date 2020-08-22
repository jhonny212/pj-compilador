/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jhonny
 */
public class TablaLALR {

    private Transicion[][] trans = null;
    private ArrayList<Subtabla> tablasLL1 = null;
    final int columnas;
    final int filas;
    boolean exit=true;
    TablaLALR(Transicion[][] trans, ArrayList<Subtabla> tablasLL1, int columnas, int filas,boolean exit) {
        this.trans = trans;
        this.tablasLL1 = tablasLL1;
        this.columnas = columnas;
        this.filas = filas;
        this.exit=exit;
    }

    public void init(String path,String nombre) {
        
        for (int i = tablasLL1.size() - 1; i >= 0; i--) {
            Subtabla sub = tablasLL1.get(i);
            if (sub.hrefTablas != -1) {
                if (!sub.haveCheck) {
                    sub.haveCheck = true;
                    boolean v = unirTablas(sub.hrefTablas, sub.num, 0);
                    if (v) {
                        this.tablasLL1.get(sub.num).delete = true;
                        unirCeldas(sub.num, sub.hrefTablas);
                    }
                }
            }
        }
        
        saveDatas(print(path,nombre),path, nombre+"_tablaLALR.bin");
        this.tablasLL1.clear();
        this.tablasLL1 = null;

    }
   
    boolean unirTablas(int i, int j, int k) {
        Transicion t1 = trans[i][k];
        Transicion t2 = trans[j][k];
        
        if (t1 == null && t2 == null) {
            if (unirCeldas(k + 1)) {
                return unirTablas(i, j, k + 1);

            } else {
                return true;
            }

        } else if (t1 == null && t2 != null) {
            if (unirCeldas(k + 1)) {
                boolean v = unirTablas(i, j, k + 1);
                if (v) {
                    trans[i][k] = new Transicion(t2.tk, t2.tipo, t2.transicion);
                }
                return v;

            } else {
                return true;
            }

        } else if (t2 == null && t1 != null) {
            if (unirCeldas(k + 1)) {
                return unirTablas(i, j, k + 1);
            } else {
                return true;
            }

        } else if (t1 != null && t2 != null) {
            if (t1.tipo.equals(t2.tipo)) {
                int x1 = t1.transicion;
                int x2 = t2.transicion;
                if (x1 == x2) {
                    if (unirCeldas(k + 1)) {
                        return unirTablas(i, j, k + 1);
                    } else {
                        return true;
                    }
                } else {
                    if (existehref(x2, x1)) {
                        if (!tablasLL1.get(x2).haveCheck) {
                            this.tablasLL1.get(x2).haveCheck = true;
                            this.tablasLL1.get(j).numOfColumn = k + 1;
                            boolean v = unirTablas(x1, x2, 0);
                            if (v) {
                                this.tablasLL1.get(x2).delete = true;
                                unirCeldas(x2, x1);
                                if (unirCeldas(k + 1)) {
                                    return unirTablas(i, j, k + 1);
                                } else {
                                    return true;
                                }
                            } else {
                                return v;
                            }
                        } else {
                            boolean v = unirTablas(x1, x2, this.tablasLL1.get(x2).numOfColumn);
                            if(v){
                                if (unirCeldas(k + 1)) {
                                    return unirTablas(i, j, k + 1);
                                } else {
                                    return true;
                                }
                            }else{
                            return v;
                            }
                        }
                    }
                }
            } else {
                return false;
            }
        }

        return false;
    }

    boolean unirCeldas(int i) {
        return i < columnas;
    }

    boolean existehref(int i, int j) {
        return this.tablasLL1.get(i).hrefTablas == j;
    }

    void unirCeldas(int oldvalue, int newvalue) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Transicion tr = trans[i][j];
                if (tr != null) {
                    if (tr.transicion == oldvalue) {
                        tr.transicion = newvalue;
                    }
                }
            }
        }

    }

    HashMap<Integer, Transicion[]> print(String path,String nombre) {
        HashMap<Integer, Transicion[]> transicions = new HashMap<>();
        String csv = "";
        for (int i = 0; i < filas; i++) {
            String datas = "No." + i;
            if (this.tablasLL1.get(i).delete) {
                continue;
            }
            Transicion x[] = new Transicion[columnas];
            for (int j = 0; j < columnas; j++) {
                Transicion tr = trans[i][j];
                if (tr != null) {
                    {
                        x[j] = tr;
                        datas += "," + tr.tipo + ":" + tr.transicion;

                    }
                } else {
                    datas += ", ";
                }
            }
            transicions.put(i, x);
            csv += datas + "\n";
        }
        write(new File(path+"/"+nombre+"_LALR.csv"), csv);
        return transicions;
    }

    public void saveDatas(Object obj,String path, String name) {
        if(exit){
        try {
            FileOutputStream file = new FileOutputStream(path+"/"+name);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("no");
        } catch (IOException ex) {
            System.out.println("no");
        }
        }
    }

    public void write(File path, String texto) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            pw.write(texto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println("no");
            }
        }
    }

}
