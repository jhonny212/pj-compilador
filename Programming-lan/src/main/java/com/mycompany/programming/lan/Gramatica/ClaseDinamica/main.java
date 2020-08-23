package com.mycompany.programming.lan.Gramatica.ClaseDinamica;

import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class main {

    public static void main(String[] args) throws Exception {
        // Definir la clase
        String[] sources = new String[]{""
            + "package com.mycompany.programming.lan.Gramatica.ClaseDinamica;"
            + "import java.util.ArrayList;",
            "public class AppConfiguracion  {",
            "public void saludar(ArrayList<String> x){"
            + "System.out.println(x.size());"
            + "System.out.println(x.get(0));"
            + "System.out.println(\"hola mundo\");"
            + "}",
            "}"};

        

        String source = join(sources);

        /*if(source.contains("package")){
            int x=source.indexOf(";");
            String _package=source.substring(0,x);
            String _packName=_package.replace("package","").replace(" ","");
            System.out.println(_packName);
            x=source.indexOf("{");
            int y=source.indexOf("class");
            _package=source.substring(y+5,x).replace(" ","");
            String Name=_packName.concat("."+_package);
            System.out.println(Name);
        }*/
        String name = "com.mycompany.programming.lan.Gramatica.ClaseDinamica.AppConfiguracion";
        // Compilar la clase

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(name, source));
        compiler.getTask(new StringWriter(), manager, null, null, null, files).call();
        // Cargar e instanciar la clase
        //Configuracion configuracion = (Configuracion) manager.getClassLoader(null).loadClass(name).newInstance();
        Class cl = manager.getClassLoader(null).loadClass(name);
        //Clas[] vect constructor
        //Object[] datas
        Constructor ct = cl.getConstructor();
        Object obj = ct.newInstance();
        Class partypes[]=new Class[1];
        partypes[0]=ArrayList.class;
        Method m1 = obj.getClass().getDeclaredMethod("saludar",partypes);
        Object vector[]=new Object[1];
        ArrayList<String> x=new ArrayList();
        x.add("hasa");
        vector[0]=x;
        m1.invoke(obj,vector);
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
