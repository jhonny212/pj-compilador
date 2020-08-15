/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhonny
 */
public class test {

    LinkedList<Integer> pilaDeProducciones$$$ = new LinkedList<>();
    LinkedList<String> pilaDeTokens____ = new LinkedList<>();
    int contador___________ = 0;
    String array[] = {"num", "coma", "num", "coma", "num", "$"};
    Produccion listadoDeProducciones[];

    public test() {
        leerBin();
        init();
    }

    public void leerBin() {
        try {
            String path = (new File("").getAbsolutePath()) + "/src/main/java/com/mycompany/programming/lan/Gramatica/TablaLALR/listado.bin";
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
            listadoDeProducciones = (Produccion[]) in.readObject();
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    public void init() {
        this.pilaDeProducciones$$$.push(0);
        methodPila(this.pilaDeProducciones$$$.getFirst(), array[0]);

    }

    void methodPila(int numOfPila, String dato) {
        switch (numOfPila) {
            case 0:
                caso0(dato);
                break;
            case 1:
                caso1(dato);
                break;
            case 2:
                caso2(dato);
                break;
            case 3:
                caso3(dato);
                break;
            case 4:
                caso4(dato);
                break;
        }
    }

    void caso0(String nameOfToken) {
        switch (nameOfToken) {
            case "E":
                int numOfTrans$$_E = 1;
                this.pilaDeProducciones$$$.push(numOfTrans$$_E);
                methodPila(numOfTrans$$_E, array[this.contador___________]);
                break;
            case "num":
                int numOfTrans$$_num = 2;
                this.contador___________++;
                this.pilaDeProducciones$$$.push(numOfTrans$$_num);
                this.pilaDeTokens____.push("num");
                methodPila(numOfTrans$$_num, array[this.contador___________]);
                break;
        }
    }

    void caso1(String nameOfToken) {
        switch (nameOfToken) {
            case "$":
                int numOfTrans$$_$ = -100;
                System.out.println("VENGO ACACCPT");
                break;
            case "coma":
                int numOfTrans$$_coma = 3;
                this.contador___________++;
                this.pilaDeProducciones$$$.push(numOfTrans$$_coma);
                this.pilaDeTokens____.push("coma");
                methodPila(numOfTrans$$_coma, array[this.contador___________]);
                break;
        }
    }

    void caso2(String nameOfToken) {
        switch (nameOfToken) {
            case "$":
                int numOfTrans$$_$ = 0;
                Produccion xtmp$ = this.listadoDeProducciones[numOfTrans$$_$];
                for (int i = 0; i < xtmp$.SimbolosProduccion.size(); i++) {
                    this.pilaDeTokens____.removeFirst();
                    this.pilaDeProducciones$$$.removeFirst();
                }
                this.pilaDeTokens____.push(xtmp$.padre);
                methodPila(this.pilaDeProducciones$$$.getFirst(), xtmp$.padre);

                /*this.pilaDeTokens____.removeFirst();
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeTokens____.push("E");
                methodPila(pilaDeProducciones$$$.getFirst(), "E");*/
                break;
            case "coma":
                int numOfTrans$$_coma = 0;
                Produccion xtmpcoma = this.listadoDeProducciones[numOfTrans$$_coma];
                for (int i = 0; i < xtmpcoma.SimbolosProduccion.size(); i++) {
                    this.pilaDeTokens____.removeFirst();
                    this.pilaDeProducciones$$$.removeFirst();
                }
                this.pilaDeTokens____.push(xtmpcoma.padre);
                methodPila(this.pilaDeProducciones$$$.getFirst(), xtmpcoma.padre);

                //this.pilaDeTokens____.removeFirst();
                //this.pilaDeProducciones$$$.removeFirst();
                //this.pilaDeTokens____.push("E");
                //methodPila(pilaDeProducciones$$$.getFirst(), "E");
                break;
        }
    }

    void caso3(String nameOfToken) {
        switch (nameOfToken) {
            case "num":
                int numOfTrans$$_num = 4;
                this.contador___________++;
                this.pilaDeTokens____.push("num");
                this.pilaDeProducciones$$$.push(numOfTrans$$_num);
                methodPila(numOfTrans$$_num, this.array[this.contador___________]);
                break;
        }
    }

    void caso4(String nameOfToken) {
        switch (nameOfToken) {
            case "$":
                int numOfTrans$$_$ = 1;

                Produccion xtmp$ = this.listadoDeProducciones[numOfTrans$$_$];
                for (int i = 0; i < xtmp$.SimbolosProduccion.size(); i++) {
                    this.pilaDeTokens____.removeFirst();
                    this.pilaDeProducciones$$$.removeFirst();
                }
                this.pilaDeTokens____.push(xtmp$.padre);
                methodPila(this.pilaDeProducciones$$$.getFirst(), xtmp$.padre);

                /*
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeTokens____.removeFirst();
                this.pilaDeTokens____.removeFirst();
                this.pilaDeTokens____.removeFirst();
                this.pilaDeTokens____.push("E");
                methodPila(pilaDeProducciones$$$.getFirst(), "E");*/
                break;
            case "coma":
                int numOfTrans$$_coma = 1;
                Produccion xtmpcoma = this.listadoDeProducciones[numOfTrans$$_coma];
                for (int i = 0; i < xtmpcoma.SimbolosProduccion.size(); i++) {
                    this.pilaDeTokens____.removeFirst();
                    this.pilaDeProducciones$$$.removeFirst();
                }
                this.pilaDeTokens____.push(xtmpcoma.padre);
                methodPila(this.pilaDeProducciones$$$.getFirst(), xtmpcoma.padre);

                /*
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeProducciones$$$.removeFirst();
                this.pilaDeTokens____.removeFirst();
                this.pilaDeTokens____.removeFirst();
                this.pilaDeTokens____.removeFirst();
                this.pilaDeTokens____.push("E");
                methodPila(pilaDeProducciones$$$.getFirst(), "E");*/
                break;
        }
    }
}
