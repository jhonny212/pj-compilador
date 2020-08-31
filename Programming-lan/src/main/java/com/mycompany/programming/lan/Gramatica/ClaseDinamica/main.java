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
            "public void saludar(){"
            + "System.out.println(\"hola mundo\");"
            + "}",
            "}"};

     
        String source = join(sources);

        String name = "AppConfiguracion";
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
            Method m1 = obj.getClass().getDeclaredMethod("saludar");
            m1.invoke(obj);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
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
