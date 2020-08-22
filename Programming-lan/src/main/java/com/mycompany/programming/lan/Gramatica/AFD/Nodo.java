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
public interface Nodo {
    
    public String getValue();
    
    public void recorrer(Nodo href);
    
   
    public String getFirst();
    public String getLast();
    public boolean isLambda();
    
    public void addSiguientes(String x);
    public String getSiguientes();
    
    public boolean check();
    public void setChech(boolean f);
    public boolean compare(Nodo x);
   
    public void setNameToken(String name);
    
    public String getToken();
    public void setToken();
}
