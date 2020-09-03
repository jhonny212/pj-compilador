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
            String texto = "nombre : testBool ; \n"
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
                    + "or= \"|\";\n"
                    + "id= [a-z]+;\n"
                    + "&= [\\n|\\t|\\b] ;\n"
                    + "%%\n"
                    + "terminal sum,mul,and,or,id,op;"
                    + "terminal cadena bool;"
                    + "terminal ap  ;"
                    + "terminal cp ;\n"
                    + "terminal entero num;\n"
                    + "no terminal cadena E,T,F,Z;"
                    + "no terminal entero S,X,Y;"
                    + "%%\n";

            texto += "Z:: E:e {System.out.println(\"valor \"+e);};\n"
                    + "E:: E:e and T:e1 \n"
                    + "{"
                    + "boolean x1=Boolean.valueOf(e);\n"
                    + "boolean x2=Boolean.valueOf(e1);\n"
                    + "RESULT=String.valueOf((x1&&x2));\n"
                    + "}"
                    + ";\n"
                    + "E:: T:e {RESULT=e;};\n"
                    + "T:: T:e or F:e1 \n"
                    + "{"
                    + "boolean x1=Boolean.valueOf(e);\n"
                    + "boolean x2=Boolean.valueOf(e1);\n"
                    + "RESULT=String.valueOf((x1||x2));\n"
                    + "}\n"
                    + "; "
                    + "T:: F:e {RESULT=e;};\n"
                    + "F:: bool:e {RESULT=e;} ;\n "
                    + "F:: ap E:e cp {RESULT=e;} ;\n"
                    + "F:: S:e op S:e1 {RESULT=String.valueOf((e>e1));} ;\n"
                    + "S:: S:e sum X:e1 {RESULT=e+e1;};\n"
                    + "S:: X:e {RESULT=e;};\n"
                    + "X:: X:e mul Y:e1 {RESULT=e*e1;};\n"
                    + "X:: Y:e {RESULT=e;};\n"
                    + "Y:: num:e {RESULT=e;};\n"
                    + "Y:: ap S:e cp {RESULT=e;};\n";

            lexer scan = new lexer(new BufferedReader(new StringReader(texto)));

            parser parser = new parser(scan);

            try {
                parser.parse();

            } catch (Exception ex) {
                System.out.println("ex" + ex.getMessage());
            }
        } catch (Exception ex) {
        }

    }

}
