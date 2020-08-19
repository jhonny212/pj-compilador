/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.AFD;

/**
 *
 * @author jhonny
 */
public class Token {
    private final String value,token;
    public Token(String val,String tkn){
    this.value=val;
    this.token=tkn;
    }

    public String getValue() {
        return value;
    }

    public String getToken() {
        return token;
    }
    
    
}
