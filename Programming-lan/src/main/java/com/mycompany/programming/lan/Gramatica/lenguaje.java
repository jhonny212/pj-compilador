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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

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

    public String nombre, autor, extension, codjava,lanzamiento;
    public double version;
    public boolean exito = true;

    public lenguaje(String name, String autor, String ext, double vers, String cod,String ls) {
        this.nombre = name;
        this.autor = autor;
        this.extension = ext;
        this.version = vers;
        this.codjava = cod;
        this.lanzamiento=ls;
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
        for (Produccion x : producciones) {
            switch ((x.tipo())) {
                case 0:
                    methods += "public Object method_$" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "Object RESULT=null;"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
                case 1:
                    methods += "public String method_$" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "String RESULT=\"\";"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
                case 2:
                    methods += "public int method_$" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "int RESULT=-1;"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
                case 3:
                    methods += "public double method_$" + x.num + " (ArrayList<Token> pDeTkns_$_$_)"
                            + "{\n"
                            + "double RESULT=0.0;"
                            + "" + x.reglasSemanticas + "\n"
                            + "return RESULT;}\n";
                    break;
            }

        }

    }

    public String createStringClass() {
        testProducciones();
        String clase = "";

        if (this.codjava == null) {
            String package_ = "package " + this.getClass().getPackageName();
            clase = package_ + ";\n";
            clase += "import com.mycompany.programming.lan.Gramatica.AFD.Token;\n"
                    + "import java.util.ArrayList;";
            clase += "public class " + this.nombre + "{\n";
            clase += methods;
            clase += "\n}";
            this.Name = this.getClass().getPackageName() + "." + nombre;
        } else {
            String package_ = "";
            if (this.codjava.contains("package")) {
                int x = this.codjava.indexOf("package");
                int y = this.codjava.indexOf(";");
                package_ = this.codjava.substring(x + 7, y);
                String tmp = package_.strip();
                package_ = tmp;
            }
            String imports = "import com.mycompany.programming.lan.Gramatica.AFD.Token;\n"
                    + "import java.util.ArrayList;";
            int w = 0, ww;
            int x = this.codjava.indexOf("class");
            ww = x;
            int y = this.codjava.indexOf("{");
            w = y;
            String tmp = this.codjava.substring(x + 5, y).strip();
            if (tmp.contains("implements")) {
                y = tmp.indexOf("implements");
                String tmp2 = tmp.substring(0, y).strip();
                tmp = tmp2;

            } else if (tmp.contains("extends")) {
                y = tmp.indexOf("extends");
                String tmp2 = tmp.substring(0, y).strip();
                tmp = tmp2;
            }
            if (package_.isEmpty()) {
                this.Name = tmp;
            } else {
                this.Name = package_ + "." + tmp;
            }
            String data = "";
            boolean valid = true;
            if (this.codjava.contains("final")) {
                x = this.codjava.indexOf("final");
                data = this.codjava.substring(0, x) + imports;
                if (x < ww) {
                    valid = false;
                }
            }
            if (this.codjava.contains("public") && valid) {
                x = this.codjava.indexOf("public");
                data = this.codjava.substring(0, x) + imports;
                if (x < ww) {
                    valid = false;
                }
            }

            if (this.codjava.contains("class") && valid) {
                x = ww;
                data = this.codjava.substring(0, x) + imports;
            }
            tmp = this.codjava.substring(x, w + 1);
            clase = data + tmp + methods;
            tmp = this.codjava.substring(w + 1, this.codjava.length() - 1);
            clase += tmp;

            return clase;

        }

        return clase;
    }
    public Object claseCompilada = null;
    String Name = "";

    public void compilar() {
        String clase = createStringClass();
        
        try {

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
            List<JavaFileObject> files = new ArrayList<>();
            files.add(new CharSequenceJavaFileObject(this.Name, clase));
            compiler.getTask(new StringWriter(), manager, null, null, null, files).call();

            Class cl = manager.getClassLoader(null).loadClass(this.Name);
            Constructor ct = cl.getConstructor();
            claseCompilada = ct.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "La clase de codigo java no pudo ser compilada, "
                    + "verifique la sintaxis y que sea de clase publica");

        } catch (java.lang.IllegalArgumentException ex) {
        }
    }

    public String getInfo() {
        String param = (this.extension.isEmpty() ? "None" : this.extension);
        String data = "Extension: " + param + "\n"
                + "Version: " + this.version + "\n";
        param = (this.autor.isEmpty() ? "None" : this.autor);
        data += "Autor: " + param + "\n";
        data += "Nombre:" + this.Name+"\n";
        param = (this.lanzamiento.isEmpty() ? "None" : this.lanzamiento);
        data += "Lanzamiento:" + param;
        
       

        return data;
    }

    public String[][] getProducciones() {
        String vector[][] = new String[this.producciones.length + 1][3];
        for (Produccion x : this.producciones) {
            int i = x.num + 1;
            vector[i][0] = String.valueOf((i-1));
            vector[i][1] = x.getDad()+"->"+x.getPrs();
            vector[i][2] = x.ruls;
        }
        return vector;
    }
}
