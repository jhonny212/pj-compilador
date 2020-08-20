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
        Start_program f=new Start_program();
        f.setVisible(true);
        //var x=new Compilador();
        //x.init();
       
        /*lenguaje l=new lenguaje();
        l.cargarLenguaje("/home/jhonny/Escritorio/6To.Semestre/Compi2/LabCompi/Proyecto1/Programming-lan/src"
                + "/main/java/com/mycompany/programming/lan/Interfaz/repositorios/juan");
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
                    + "nombre: juan ; \n"
                    + "version: 2 ;\n"
                    + "extension: com;\n"
                    + "%% public static void terminal(){} %%\n"
                    + "idsps= ([A-Z])+|([a-z])+;"
                 
                    + "%%\n"
                    + "terminal num,mul,sum,ap,cp;\n"
                    + "no terminal E,T,F;\n"
                    + "S:: T sum E;\n"
                    + "E:: T;\n"
                    + "T:: F mul T;\n"
                    + "T:: F;\n"
                    + "F:: num;\n"
                    + "F:: ap E cp;";
           
            
            var scan = new lexer(new BufferedReader(new StringReader(texto)));
            
            
            var parser = new parser(scan);
            try {
            parser.parse();
               
            
            } catch (Exception ex) {
            System.out.println("ex"+ex.getMessage());
            }
        
        

    }

    static void lexer() {
        Tokenizer tokenizer = new Tokenizer();

        tokenizer.add("[0-9]+((.)[0-9]+)?", 8, "ID ");

        //tokenizer.add("\\b(program|var|const|begin|end|Writeln|if|then|else|end|Mayor|Menor)\\b", 1, "Palabra Reservada");
        //tokenizer.add("[0-2]+", 6, "Numero");
        /*tokenizer.add("((?:[a-z][a-z0-9_]*))", 2, "Identificador");
        tokenizer.add("(;|,)", 3, "Puntos y Coma");
        tokenizer.add("(:)", 4, "Dos Puntos");
        tokenizer.add("(=|<|>)", 5, "Simbolos");
        tokenizer.add("(INTEGER|Integer|BOOLEAN|DOUBLE|REAL|CHAR)", 7, "Tipos Datos");
        tokenizer.add("(\\()", 8, "Simbolos '(' ");
        tokenizer.add("(\\))", 9, "Simbolos ')' ");
        tokenizer.add("(')", 10, "Comillas Simples");
        tokenizer.add("(\\.)", 11, "Punto final");*/
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
