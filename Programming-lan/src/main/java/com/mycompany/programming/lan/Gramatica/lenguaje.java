/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica;

import com.mycompany.programming.lan.Gramatica.AFD.generarAFD;
import com.mycompany.programming.lan.Gramatica.ClaseDinamica.CharSequenceJavaFileObject;
import com.mycompany.programming.lan.Gramatica.ClaseDinamica.ClassFileManager;
import com.mycompany.programming.lan.Gramatica.TablaLALR.Produccion;
import com.mycompany.programming.lan.Gramatica.TablaLALR.Transicion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

/**
 *
 * @author jhonny
 */
public class lenguaje implements Serializable {

    private static final long serialversionUID
            = 129348938L;

    public String nombre, autor, extension, codjava;
    public double version;
    public boolean exito = true;

    public lenguaje(String name, String autor, String ext, double vers, String cod) {
        this.nombre = name;
        this.autor = autor;
        this.extension = ext;
        this.version = vers;
        this.codjava = cod;
    }
    public HashMap<Integer, Transicion[]> tablaLALR = null;
    public generarAFD tablaAFD = null;
    public String[] fila = null;
    public Produccion[] producciones = null;

    public lenguaje() {

    }

    public void cargarLenguaje(String path) {

        leerBin(1, path + "/" + this.nombre + "/" + this.nombre + "_Producciones.bin");
        leerBin(2, path + "/" + this.nombre + "/" + this.nombre + "_Filas.bin");
        leerBin(3, path + "/" + this.nombre + "/" + this.nombre + "_tablaLALR.bin");
        leerBin(4, path + "/" + this.nombre + "/" + this.nombre + "_AFD.bin");

    }

    private void leerBin(int opc, String name) {
        try {
            FileInputStream file = new FileInputStream(new File(name));
            ObjectInputStream in = new ObjectInputStream(file);
            switch (opc) {
                case 1:
                    producciones = (Produccion[]) in.readObject();
                    break;
                case 2:
                    fila = (String[]) in.readObject();
                    break;
                case 3:
                    tablaLALR = (HashMap<Integer, Transicion[]>) in.readObject();
                    break;
                case 4:
                    tablaAFD = (generarAFD) in.readObject();
                    break;
            }
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught" + ex.getMessage());
            exito = false;
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            exito = false;
        }
    }
    String methods = "";

    public void testProducciones() {
        methods = "";
        for (Produccion x : producciones) {
            switch ((x.tipo())) {
                case 0:
                    methods += "public Object method" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "Object RESULT=null;"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
                case 1:
                    methods += "public String method" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "String RESULT=\"\";"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
                case 2:
                    methods += "public int method" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "int RESULT=-1;"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
                case 3:
                    methods += "public double method" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "double RESULT=0.0;"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
            }

        }

    }

    public void testFila() {
        System.out.println(Arrays.toString(fila));
    }

    public String createStringClass() {
        testProducciones();
        String package_ = "package " + this.getClass().getPackageName();
        String clase = package_ + ";\n";
        clase += "import com.mycompany.programming.lan.Gramatica.AFD.Token;\n"
                + "import java.util.ArrayList;";
        clase += "public class " + this.nombre + "{\n";
        clase += methods;
        clase += "\n}";
        return clase;
    }
    public Object claseCompilada=null;
    public void compilar() {
        String name = this.getClass().getPackageName() + "." + this.nombre;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
        List<JavaFileObject> files = new ArrayList<>();
        files.add(new CharSequenceJavaFileObject(name, createStringClass()));
        compiler.getTask(new StringWriter(), manager, null, null, null, files).call();
        try {
            Class cl = manager.getClassLoader(null).loadClass(name);
            Constructor ct = cl.getConstructor();
            claseCompilada = ct.newInstance();
            //Method m1 = obj.getClass().getDeclaredMethod("saludar");
            //m1.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            System.out.println(" ex -> " + e.getMessage());
            //e.printStackTrace();
        }
    }
}
