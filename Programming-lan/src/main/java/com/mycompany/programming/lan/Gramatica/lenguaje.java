/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica;

import com.mycompany.programming.lan.Gramatica.AFD.generarAFD;
import com.mycompany.programming.lan.Gramatica.TablaLALR.Produccion;
import com.mycompany.programming.lan.Gramatica.TablaLALR.Transicion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author jhonny
 */
public class lenguaje implements Serializable {

    private static final long serialversionUID
            = 129348938L;

    public String nombre, autor, extension;
    public double version;

    public lenguaje(String name, String autor, String ext, double vers) {
        this.nombre = name;
        this.autor = autor;
        this.extension = ext;
        this.version = vers;
    }
    public HashMap<Integer, Transicion[]> tablaLALR = null;
    public ArrayList<generarAFD> tablaAFD=null;
    public String[] fila=null;
    public Produccion[] producciones = null;

    public lenguaje() {

    }
    
    public void cargarLenguaje(String path){
        
        leerBin(1, path+"/"+this.nombre+"/"+this.nombre+"_Producciones.bin");
        leerBin(2, path+"/"+this.nombre+"/"+this.nombre+"_Filas.bin");
        leerBin(3, path+"/"+this.nombre+"/"+this.nombre+"_tablaLALR.bin");
        leerBin(4, path+"/"+this.nombre+"/"+this.nombre+"_AFD.bin");
        //testProducciones();
        //testFila();
        //
    }

    private void leerBin(int opc,String name) {
        try {
            FileInputStream file = new FileInputStream(new File(name));
            ObjectInputStream in = new ObjectInputStream(file);
            switch (opc) {
                case 1:
                    producciones= (Produccion[]) in.readObject();
                    break;
                case 2:
                    fila = (String[]) in.readObject();
                    break;
                case 3:
                    tablaLALR= (HashMap<Integer, Transicion[]>) in.readObject();
                    break;
                case 4:
                    tablaAFD= (ArrayList<generarAFD>) in.readObject();
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
    
    void testProducciones(){
        for(Produccion x: producciones){
            System.out.println((x.num+" "+x.getKey()+" "+" ->"));
        }
    }
    
    void testFila(){
        System.out.println(Arrays.toString(fila));
    }
    

}
