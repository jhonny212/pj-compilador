package com.mycompany.programming.lan;

import com.mycompany.programming.lan.Gramatica.lexer;
import com.mycompany.programming.lan.Gramatica.parser;
import com.mycompany.programming.lan.Interfaz.Start_program;

import java.io.BufferedReader;
import java.io.StringReader;

public class inicio {

    public static void main(String[] args) {
        //Start_program f = new Start_program();
        //f.setVisible(true);
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
            var texto = "nombre : testBool ; \n"
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
                    + "bool= \"true\"|\"false\";\n"
                    + "op= \">\";\n"
                    + "and= \"&\";\n"
                    + "or= \"||\";\n"
                    + "id= [a-z]+;\n"
                    + "&= [\\n|\\t|\\b] ;\n"
                    + "%%\n"
                    + "terminal sum,mul,and,or,bool,id,op;"
                    + "terminal ap  ;"
                    + "terminal cp ;\n"
                    + "terminal  num;\n"
                    + "no terminal  E,T,F,S,X,Y,Z;"
            
                    + "%%\n";
            
            texto +=  "E:: E and T;"
                    + "E:: T;"
                    + "T:: T or F;"
                    + "T:: F;"
                    + "F:: bool;"
                    + "F:: ap E cp;"
                    + "F:: S op S;"
                    + "S:: S sum X;"
                    + "S:: X;"
                    + "X:: X mul Y;"
                    + "X:: Y;"
                    + "Y:: num;"
                    + "Y:: id;"
                    + "Y:: ap S cp;";
            
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
