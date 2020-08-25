package com.mycompany.programming.lan;

import com.mycompany.programming.lan.Gramatica.lexer;
import java.io.BufferedReader;
import java.io.StringReader;

public class inicio {

    public static void main(String[] args) {
        //Start_program f = new Start_program();
        //f.setVisible(true);
       
    }

    private static void generarCompilador() {
        try {
            String ruta = "src/main/java/com/mycompany/programming/lan/Gramatica/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "lexer.Jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "parser", ruta + "parser.cup"};
            //java_cup.Main.main(opcCUP);
        } catch (Exception ex) {
        }

    }

    public static void probarCompilador() {
        try {
            String texto = ""
                    + "nombre: test ; \n"
                    + "version: 2 ;\n"
                    + "extension: com;\n"
                    + "%%  "
                    + "%%\n"
                    + "num= [0-9]+;"
                    + "sum= \"+\";"
                    + "mul= \"*\";"
                    + "&= \"(\";"
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
                    + "F:: ap E:e cp {RESULT=e;}; ";
            
            
            var scan = new lexer(new BufferedReader(new StringReader(" ")));
            scan.next_token();
            /*var parser = new parser(scan);
            try {
            parser.parse();
            
            } catch (Exception ex) {
            System.out.println("ex" + ex.getMessage());
            }*/
        } catch (Exception ex) {
        }

    }

 
}
