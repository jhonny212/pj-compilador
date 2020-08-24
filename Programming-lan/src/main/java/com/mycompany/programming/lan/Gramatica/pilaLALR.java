/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica;

import com.mycompany.programming.lan.Gramatica.AFD.Token;
import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class pilaLALR {

    private ArrayList<movimiento> listado;

    public class movimiento {
        String pilaCheck;
        int reduce;
        String addTokens;
        int addTrans;
        
    }

}
