package com.mycompany.programming.lan;

import com.mycompany.programming.lan.Gramatica.lexer;
import com.mycompany.programming.lan.Gramatica.parser;
import com.mycompany.programming.lan.Interfaz.Start_program;

import java.io.BufferedReader;
import java.io.StringReader;

public class inicio {

    public static void main(String[] args) {
        Start_program f = new Start_program();
        f.setVisible(true);
        //generarCompilador();
        //probarCompilador();
    }

    private static void generarCompilador() {
        try {
            String ruta = "src/main/java/com/mycompany/programming/lan/Gramatica/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "lexer.Jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "parser", ruta + "parser.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception ex) {
        }

    }

    public static void probarCompilador() {
        try {
           var texto ="nombre : aritmetica ; \n"
                    + "version  : 2.0  ;\n"
                    + "extension : com ;\n"
                    + ""
                   + "%% \n"
                    + "   public class aritmetica{\n"
                    + "    public int sumar(int x,int y){\n"
                    + "        return x+y;\n"
                    + "    }\n"
                    + "\n"
                    + "     public aritmetica(){}	\n"
                    + "\n"
                    + "    public int mul(int x,int y){\n"
                    + "        return x*y;\n"
                    + "    }\n"
                    + "  \n"
                    + "}\n"
                    + "%%\n"
                    + "num = [0-9]+ ;\n"
                    + "sum= \"+\";\n"
                    + "mul= \"*\";\n"
                    + "ap= \"(\";\n"
                    + "cp= \")\";\n"
                    + "&= [\\n|\\t|\\b] ;\n"
                    + "%%\n"
                    + "terminal  sum,mul ;"
                    + "terminal  ap  ;"
                    + "terminal cp ;\n"
                    + "terminal entero num;\n"
                    + "no terminal entero  S;"
                   + "no terminal entero  E,T,F ;\n"
                    + "%%\n"
                    + "S:: E:e   {} ;\n";
           
           texto+=  ""
                    + "E:: T:e sum E:e1     {RESULT=sumar(e,e1);};\n"
                    + "E:: T:e             {RESULT=e;};\n"
                    + "T:: F:e  mul T:e1    {RESULT=mul(e,e1);};\n"
                    + "T::  F:e             {RESULT=e;};\n"
                    + "F:: num:e           {RESULT=e;};\n"
                    + "F:: ap E:e cp       {RESULT=e;};";
            var scan = new lexer(new BufferedReader(new StringReader(texto)));
            var parser = new parser(scan);

            try {
                parser.parse();

            } catch (Exception ex) {
                System.out.println("ex" + ex.getMessage());
            }
        } catch (Exception ex) {
        }

    }

}
