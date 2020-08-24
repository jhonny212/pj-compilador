package com.mycompany.programming.lan;

import com.mycompany.programming.lan.Gramatica.AFD.analizadorLexico;
import com.mycompany.programming.lan.Gramatica.TablaLALR.Compilador;
import com.mycompany.programming.lan.Gramatica.lenguaje;
import com.mycompany.programming.lan.Gramatica.lexer;
import com.mycompany.programming.lan.Gramatica.parser;
import com.mycompany.programming.lan.Interfaz.Start_program;
import com.mycompany.programming.lan.programming.language.Tokenizer;

import java.io.BufferedReader;
import java.io.StringReader;

public class inicio {

    public static void main(String[] args) {
        //String texto="12 mundoXd hola mundoXd";
        //var x=texto.startsWith("[0-9]+");
        //System.out.println(x);
        //generarCompilador();
        //probarCompilador();
        Start_program f = new Start_program();
        f.setVisible(true);
        /*
        String x = "package  sda.sda \b ;"
                + "public class java \t extends xd{ SYstem}";
        int z = x.indexOf("package");
        int w = x.indexOf(";");
        String tmp = x.substring(z + 7, w).strip();
        System.out.println(":" + tmp + ":");
        z = x.indexOf("class");
        int y=0;
        w = x.indexOf("{");
        y=w;
        tmp = x.substring(z + 5, w).strip();
        if (tmp.contains("implements")) {
            z = tmp.indexOf("implements");

        } else if (tmp.contains("extends")) {
            z = tmp.indexOf("extends");
        }
        String tmp2 = tmp.substring(0, z).strip();
        tmp=tmp2;
        System.out.println(":" + tmp + ":");
        tmp=x.substring(0,y);
        System.out.println("\n:" + tmp + ":");
        tmp=x.substring(y,x.length());
        System.out.println("\n:" + tmp + ":");
         */

    }

    private static void generarCompilador() {
        try {
            String ruta = "src/main/java/com/mycompany/programming/lan/Gramatica/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "lexer.Jflex", "-d", ruta};
            //jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "parser", ruta + "parser.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception ex) {
        }

    }

    public static void probarCompilador() {
        String texto = ""
                + "nombre: test ; \n"
                + "version: 2 ;\n"
                + "extension: com;\n"
                + "%%  "
                + "     public class tester(){"
                + "     public void saludar(){"
                + "     System.out.println(\"hola mundo\");"
                + "}"
                + "} %%\n"
                + "num= [0-9]+;"
                + "sum= \"+\";"
                + "mul= \"*\";"
                + "ap= \"(\";"
                + "cp= \")\";"
                + "%%\n"
                + "terminal sum,mul,ap,cp;"
                + "terminal entero num;\n"
                + "no terminal entero S,E,T,F;"
                + "%%\n"
                + "S:: E:e {System.out.println(e);};"
                + "E:: T:e sum E:e1 {RESULT=e+e1;};"
                + "E:: T:e {RESULT=e;};"
                + "T:: F:e mul T:e1 {RESULT=e*e1;};"
                + "T:: F:e {RESULT=e;};"
                + "F:: num:e {RESULT=e;};"
                + "F:: ap E:e cp {RESULT=e;};";

        texto = "nombre: test2 ;\n"
                + "version: 2 ;\n"
                + "extension: com;\n"
                + "%% public static void terminal(){} %%\n"
                + "id    = [a-z]+;\n"
                + "coma  = \",\";\n"
                + "%%\n"
                + "terminal cadena id;\n"
                + "terminal coma;\n"
                + "no terminal S;\n"
                + "no terminal cadena E;\n"
                + "%%\n"
                + "S:: E:e {System.out.println(\"la cadena ingresada es \"+e);};\n"
                + "E:: E:e coma id:e1 {RESULT= e+\",\"+e1;};\n"
                + "E:: id:e {RESULT=e;};";
        var scan = new lexer(new BufferedReader(new StringReader(texto)));

        var parser = new parser(scan);
        try {
            parser.parse();

        } catch (Exception ex) {
            System.out.println("ex" + ex.getMessage());
        }

    }

    static void lexer() {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("[0-9]+((.)[0-9]+)?", 8, "ID ");
        try {
            tokenizer.tokenize("2.2", true);
            for (Tokenizer.Token tok : tokenizer.getTokens()) {
                System.out.println(tok.getSequence() + "   [" + tok.getTipo() + "]");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
