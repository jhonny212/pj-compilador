package com.mycompany.programming.lan.Gramatica.Errores;

import java.util.ArrayList;

public class ErrorClass {

    public ArrayList<errorProduced> lexico, semantico, sintactico;

    public ErrorClass() {
        this.lexico = new ArrayList<>();
        this.semantico = new ArrayList<>();
        this.sintactico = new ArrayList<>();
    }

    public  class errorProduced {

        public final int fila, columna;
        public final String token, dato;

        private errorProduced(String tk, String dt, int f, int c) {
            this.columna = c;
            this.fila = f-1;
            this.dato = dt;
            this.token = tk;
        }

        private errorProduced(String tk, int f, int c) {
            this.columna = c;
            this.fila = f;
            this.dato = "";
            this.token = tk;
        }
    }

    public void AddError(int opc, int fila, int columna, String token) {
        switch (opc) {
            case 0:
                this.lexico.add(new errorProduced(token, fila+1, columna+1));
                break;
            case 1:
               this.sintactico.add(new errorProduced(token, fila, columna));
               break;
            case 2:
                this.semantico.add(new errorProduced(token, fila, columna));
                break;
        }

    }

    public void AddError(int opc, int fila, int columna, String token, String solucion) {
        switch (opc) {
            case 0:
                this.lexico.add(new errorProduced(token,solucion, fila+1, columna+1));
                break;
            case 1:
               this.sintactico.add(new errorProduced(token,solucion, fila, columna));
               break;
            case 2:
                this.semantico.add(new errorProduced(token,solucion, fila, columna));
                break;
        }
    }
    
    public boolean haveErrors(){
    return this.lexico.isEmpty() && this.semantico.isEmpty() && this.sintactico.isEmpty();
    }
    
    public void erroresSintacticos(){
        for(errorProduced x: this.sintactico){
            System.out.println("COLUMNA "+x.columna+" FILA  "+x.fila+" TOKEN    "+x.token+" SOLU   "+x.dato);
        }
    }
    public void erroresSemanticos(){
        for(errorProduced x: this.semantico){
            System.out.println("COLUMNA "+x.columna+" FILA  "+x.fila+" TOKEN    "+x.token+" SOLU   "+x.dato);
        }
    }
}
