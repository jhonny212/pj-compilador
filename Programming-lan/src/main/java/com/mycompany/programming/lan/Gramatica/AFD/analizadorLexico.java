/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AFD;

import com.mycompany.programming.lan.Gramatica.AFD.generarAFD.estado;
import com.mycompany.programming.lan.Gramatica.AFD.generarAFD.referencia;
import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class analizadorLexico {

    ArrayList<generarAFD> listado;

    public analizadorLexico(ArrayList<generarAFD> listado) {
        this.listado = listado;
    }

    String tmp;

    public void init(String cadena) {
        this.tmp = cadena;

        analizar();
        analizar();
    }

    Token analizar() {
        for (int i = 0; i < this.listado.size(); i++) {
            generarAFD afd = this.listado.get(i);
            estado es = afd.AFD.get(afd.estadoInicial);
            for (referencia ref : es.referencias) {
                if (ref.compare(tmp)) {
                    String tmps = tmp.replaceFirst(ref.getVal(), "");
                    analizar(ref.y, ref.getVal(), tmps);
                }

            }

            if (!this.tokens.isEmpty()) {
                String prev = "";
                for (String x : this.tokens) {
                    if (x.length() > prev.length()) {
                        prev = x;
                    }
                }
                this.tokens.clear();
                String dato = tmp.replaceFirst(prev, "");
                this.tmp = dato;
                return new Token(prev,es.nombreToken);
                //System.out.println("se acepta " + es.nombreToken + " value: " + prev + " -> " + tmp);
                /*if (!dato.isEmpty()) {
                    i = -1;
                } else {
                    return;
                }*/
            }
        }

        /*if (!this.tmp.isEmpty()) {
            char error = this.tmp.charAt(0);
            System.out.println("error en " + error);
            String t = tmp.replaceFirst(String.valueOf(error), "");
            tmp = t;
            if (!this.tmp.isEmpty()) {
                analizar();
            }
        }*/
        return new Token(String.valueOf(this.tmp.charAt(0)),"Error");
    }

    ArrayList<String> tokens = new ArrayList<>();

    void analizar(estado es, String href, String texto) {

        if (es.aceptacion) {
            tokens.add(href);
        }
        if (texto.isEmpty()) {
            return;
        }
        for (referencia ref : es.referencias) {
            if (ref.compare(texto)) {
                String tmp = texto.replaceFirst(ref.getVal(), "");
                String tmp1 = href;
                href += ref.getVal();
                analizar(ref.y, href, tmp);
                href = tmp1;
            }

        }

    }

    public Token nextToken() {
        Token tkn = analizar();
        if(tkn.getToken().equals("Error")){
            char error=this.tmp.charAt(0);
            String t=tmp.replaceFirst(String.valueOf(error), "");
            this.tmp=t;
        }
        return tkn;
    }
    
    public boolean hashMoreTokens(){
        return this.tmp.isEmpty();
    }
}
