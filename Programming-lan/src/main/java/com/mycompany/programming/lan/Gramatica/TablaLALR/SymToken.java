package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.Serializable;

public class SymToken implements Serializable{
    private static final long serialversionUID = 
                                 129348938L; 
    public String valor;
    public String token;
    public String IdToken;
    public SymToken(String tk){
        this.token=tk;
    }
    public SymToken(String tk,String Id){
        this.token=tk;
        this.IdToken=Id;
    }


}

