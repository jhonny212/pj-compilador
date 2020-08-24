package com.mycompany.programming.lan.Gramatica.ClaseDinamica;

import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class main {

    public static void main(String[] args) {
        // Definir la clase
        String[] sources = new String[]{""
            + ""
            + "import java.util.ArrayList;",
            "public class AppConfiguracion  {",
            "public void saludar(ArrayList<String> x){"
            + "System.out.println(x.size());"
            + "System.out.println(x.get(0));"
            + "System.out.println(\"hola mundo\");"
            + "}",
            "}"};

        String test = "import com.mycompany.programming.lan.Gramatica.AFD.Token;\n"
                + "import java.util.ArrayList;public class aritmetica{public int method0 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-0).getVal();\n"
                + "System.out.println(e);\n"
                + "\n"
                + "return RESULT;}\n"
                + "public int method1 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e1 = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-0).getVal();\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-2).getVal();\n"
                + "RESULT=e+e1;\n"
                + "\n"
                + "return RESULT;}\n"
                + "public int method2 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-0).getVal();\n"
                + "RESULT=e;\n"
                + "\n"
                + "return RESULT;}\n"
                + "public int method3 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e1 = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-0).getVal();\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-2).getVal();\n"
                + "RESULT=e*e1;\n"
                + "\n"
                + "return RESULT;}\n"
                + "public int method4 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-0).getVal();\n"
                + "RESULT=e;\n"
                + "\n"
                + "return RESULT;}\n"
                + "public int method5 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-0).getVal();\n"
                + "RESULT=e;\n"
                + "\n"
                + "return RESULT;}\n"
                + "public int method6 (ArrayList<Token> pDeTkns_$_$_){\n"
                + "int RESULT=-1;\n"
                + "int e = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-1).getVal();\n"
                + "RESULT=e;\n"
                + "\n"
                + "return RESULT;}"
                + "public void hola(){"
                + "System.out.println(\"x\");"
                + "}\n"
                + "{}"
                + "}";

        String source = test;

        String name = "aritmetica";
        // Compilar la clase

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(name, source));
        compiler.getTask(new StringWriter(), manager, null, null, null, files).call();
        // Cargar e instanciar la clase
        //Configuracion configuracion = (Configuracion) manager.getClassLoader(null).loadClass(name).newInstance();
        //Clas[] vect constructor
        //Object[] datas
        Constructor ct;
        try {
            Class cl = manager.getClassLoader(null).loadClass(name);
            ct = cl.getConstructor();
            Object obj = ct.newInstance();
            Method m1 = obj.getClass().getDeclaredMethod("hola");
            m1.invoke(obj);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        /*Class partypes[] = new Class[1];
        partypes[0] = ArrayList.class;
        Object vector[] = new Object[1];
        ArrayList<String> x = new ArrayList();
        x.add("hasa");
        vector[0] = x;
        m1.invoke(obj, vector);*/
        // Invocar un método de la clase

        /*Class partypes[] = new Class[1];
        partypes[0] = ArrayList.class;
        Method m1 = obj.getClass().getDeclaredMethod("saludar", partypes);
        Object vector[] = new Object[1];
        ArrayList<String> x = new ArrayList();
        x.add("hasa");
        vector[0] = x;
        m1.invoke(obj, vector);*/
        // Invocar un método de la clase
    }

    public static void test() {
        String[] sources = new String[]{""
            + "package testCompiler;",
            "import java.util.HashMap;",
            "import java.util.Map;",
            "public class AppConfiguracion  {",
            "public void saludar(){"
            + "System.out.println(\"hola modify mundo\");"
            + "}"
            + "public void hello(){"
            + "saludar();"
            + "}",
            "}"};
        String source = join(sources);
        String name = "testCompiler.AppConfiguracion";
        // Compilar la clase
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(name, source));
        compiler.getTask(new StringWriter(), manager, null, null, null, files).call();
        // Cargar e instanciar la clase
        //Configuracion configuracion = (Configuracion) manager.getClassLoader(null).loadClass(name).newInstance();
        Class cl = null;
        try {
            cl = manager.getClassLoader(null).loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Clas[] vect constructor
        //Object[] datas
        Constructor ct = null;
        try {
            ct = cl.getConstructor();
            Object obj = ct.newInstance();
            Method m1 = obj.getClass().getDeclaredMethod("hello");
            m1.invoke(obj);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static String join(String[] s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        return sb.toString();
    }
}
