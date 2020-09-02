/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AFD;

import com.mycompany.programming.lan.Gramatica.AFD.generarAFD.estado;
import com.mycompany.programming.lan.Gramatica.AFD.generarAFD.referencia;
import com.mycompany.programming.lan.Gramatica.Errores.ErrorClass;

/**
 *
 * @author jhonny
 */
public class analizadorLexico {

    generarAFD afd;

    public analizadorLexico(generarAFD afd) {
        this.afd = afd;
        errorList=new ErrorClass();
    }

    String tmp;

    public void init(String cadena) {
        this.tmp = cadena;
        
        
    }

    public Token nextToken() {
        Token tk = nextTkn();
        if (tk.getToken().equals("Error") && !tk.getValue().equals("\n")
                && !tk.getValue().equals(" ")
                && !tk.getValue().equals("\t")) {
            String error = "";
            while (tk.getToken().equals("Error")) {
                error += tk.getValue();
                tk = nextTkn();
                if (tk.getValue().equals("\n") || tk.getValue().equals("\t")
                        || tk.getValue().equals(" ")) {
                    break;
                }
            }

            if (!tk.getToken().equals("$")) {
                this.tmp = tk.getValue() + tmp;
            }
            tk=new Token(error, "Error");
            tk.add(fila, columna-tk.getValue().length());
            this.errorList.AddError(0, tk.getF()-1, tk.getC(), tk.getValue());
            return tk;

        }
        if (tk.getValue().contains("\n")) {
            this.fila++;
            this.columna = 1;
        }else{
            this.columna+=tk.getValue().length();
        }
        if (tk.getToken().equals("&")) {
            return nextToken();
        }
         tk.add(fila, columna-tk.getValue().length());
        return tk;
    }

    Token analizar() {
        Token retorno = null;
        int prev = 0;
        referencia refer = null;
        estado es = afd.AFD.get(afd.estadoInicial);
        int j = 0;
        for (int i = 0; i < es.referencias.size(); i++) {
            referencia ref = es.referencias.get(i);
            if (ref.compare(tmp)) {
                if (ref.getVal().length() > prev) {
                    prev = ref.getVal().length();
                    refer = ref;
                    j = i;
                }
            }
        }
        if (refer != null) {
            String tmps = "";
            try {
                tmps = tmp.replaceFirst(refer.getVal(), "");
                if(tmp.equals(tmps)){
                tmps=tmp.replaceFirst("\\"+refer.getVal(), "");
                }
            } catch (java.util.regex.PatternSyntaxException ex) {
                tmps = tmp.replaceFirst("\\" + refer.getVal(), "");
            }
            this.tmp = tmps;
            try {
                analizar(refer.y, refer.getVal(), tmp, es.tokens[j]);
            } catch (Exception ex) {
                analizar(refer.y, refer.getVal(), tmp, "");
            }
            return new Token(this.value, this.token);
        }

        retorno = new Token(String.valueOf(this.tmp.charAt(0)), "Error");
        String cadena = "";
        try {
            cadena = this.tmp.replaceFirst(String.valueOf(this.tmp.charAt(0)), "");
            if (cadena.equals(this.tmp)) {
                cadena = this.tmp.replaceFirst(String.valueOf("\\" + this.tmp.charAt(0)), "");
            }
        } catch (java.util.regex.PatternSyntaxException ex) {
            cadena = tmp.replaceFirst("\\" + String.valueOf(this.tmp.charAt(0)), "");
        }

        this.tmp = cadena;
        return retorno;
    }

    void analizar(estado es, String href, String texto, String token) {

        if (es.aceptacion) {
            this.token = token;
            this.value = href;
        }
        if (texto.isEmpty()) {
            return;
        }
        referencia refer = null;
        int prev = 0;
        int j = 0;
        for (int i = 0; i < es.referencias.size(); i++) {
            referencia ref = es.referencias.get(i);
            if (ref.compare(texto)) {
                if (ref.getVal().length() > prev) {
                    refer = ref;
                    j = i;
                    prev = ref.getVal().length();
                }
            }

        }

        if (refer != null) {
            String tmps = "";
            try {
                tmps = texto.replaceFirst(refer.getVal(), "");
            } catch (java.util.regex.PatternSyntaxException ex) {
                tmps = texto.replaceFirst("\\" + refer.getVal(), "");
            }
            this.tmp = tmps;
            href += refer.getVal();
            try {
                analizar(refer.y, href, tmp, es.tokens[j]);
            } catch (Exception ex) {
                analizar(refer.y, href, tmp, "");
            }
        }
    }

    private String value, token;

    Token nextTkn() {
        if (this.tmp.isEmpty()) {
            return new Token("$", "$");
        }
        Token tkn = analizar();
        return tkn;
    }

    private int columna = 1, fila = 1;
    public boolean hashMore(){
    return this.tmp.isEmpty();
    }
    public final ErrorClass errorList;
}
