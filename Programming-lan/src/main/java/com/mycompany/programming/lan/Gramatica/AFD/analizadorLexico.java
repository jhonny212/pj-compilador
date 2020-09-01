/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AFD;

import com.mycompany.programming.lan.Gramatica.AFD.generarAFD.estado;
import com.mycompany.programming.lan.Gramatica.AFD.generarAFD.referencia;

/**
 *
 * @author jhonny
 */
public class analizadorLexico {

    generarAFD afd;

    public analizadorLexico(generarAFD afd) {
        this.afd = afd;

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
            return new Token(error, "Error");

        }
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
            cadena = tmp.replaceFirst(String.valueOf(this.tmp.charAt(0)), "");
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
        /*if (tkn.getToken().equals("Error")) {
            /*char error = this.tmp.charAt(0);
            String t;
            try {
                t = tmp.replaceFirst(String.valueOf(error), "");
            } catch (java.util.regex.PatternSyntaxException ex) {
                t = tmp.replaceFirst("\\" + String.valueOf(error), "");
            }

            this.tmp = t;
        }*/
        if (tkn.getToken().equals("&")) {
            return nextToken();
        }
        errorText = "";
        return tkn;
    }

    private String errorText = "";
    private int columna = 0, fila = 0;

}
