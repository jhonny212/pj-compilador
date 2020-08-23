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

    private String value, token;

    public Token(String val, String tkn) {
        this.value = val;
        this.token = tkn;
    }

    public Token(String tkn) {

        this.token = tkn;
    }

    public String getValue() {
        return value;
    }

    public String getToken() {
        return token;
    }

    public Object getObj() {
        if (!this.value.isEmpty() && valObj == null) {
            return this.value;
        }
        return valObj;
    }

    public int getVal() {
        try {
            return Integer.valueOf(value);
        } catch (Exception ex) {
        }
        return this.valInt;
    }

    Object valObj = null;
    int valInt;
    double real;

    public double getReal() {
        try {
            return Double.valueOf(value);
        } catch (Exception ex) {
        }
        return this.real;
    }

    public void addValue(Object val) {
        this.valObj = val;
    }

    public void addValue(String val) {
        this.value = val;
    }

    public void addValue(int val) {
        this.valInt = val;
    }

    public void addValue(double val) {
        this.real = val;
    }
}
