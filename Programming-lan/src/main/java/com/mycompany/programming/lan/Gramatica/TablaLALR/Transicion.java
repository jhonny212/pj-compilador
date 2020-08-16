/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.Serializable;

/**
 *
 * @author jhonny
 */
public class Transicion implements Serializable{
    private static final long serialversionUID = 
                                 129348938L; 
    public final SymToken tk;
    public final String tipo;
    public boolean unir;
    int transicion;
    Transicion(SymToken tk, String aGoto, int transicion) {
        this.tipo=aGoto;
        this.tk=tk;
        this.transicion=transicion;
    }

   
    
}
