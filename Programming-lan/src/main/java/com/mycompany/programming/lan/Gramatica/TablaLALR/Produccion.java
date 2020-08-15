package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.Serializable;
import java.util.ArrayList;

public class Produccion implements Serializable{
    private static final long serialversionUID = 
                                 129348938L; 
    public String reglasSemanticas;
    public ArrayList<SymToken> SimbolosProduccion;
    final public int num; 
    String padre;
    final public boolean isLambda;
    public Produccion(ArrayList<SymToken> list,String rules,int numero){
       if(list!=null){
        this.SimbolosProduccion=list;
        this.isLambda=false;
        }else{
        this.SimbolosProduccion=new ArrayList<>();
        this.SimbolosProduccion.add(new SymToken("LAMMBDAAAAA"));
        this.isLambda=true;
        }
        this.reglasSemanticas=rules;
        this.num=numero;
   }
    public Produccion(ArrayList<SymToken> list,String rules){
       if(list!=null){
        this.SimbolosProduccion=list;
        this.isLambda=false;
        }else{
        this.SimbolosProduccion=new ArrayList<>();
        this.SimbolosProduccion.add(new SymToken("LAMMBDAAAAA"));
        this.isLambda=true;
        }
        this.reglasSemanticas=rules;
        this.num=-1;
   }
   
   


    public String getKey(int posPunto){
        String data="";
        boolean v=true;
        for (int i = 0; i <this.SimbolosProduccion.size() ; i++) {
            if(posPunto==i){
                data+=". ";
                v=false;
            }
            if(SimbolosProduccion.get(i).token.equals("ap")){
                 data+="("+" ";
            
            }else {
            if(SimbolosProduccion.get(i).token.equals("cp")){
                 data+=")"+" ";
            
            }else {
             data+=SimbolosProduccion.get(i).token+" ";
            }
            }
           
        }
        if(v){
            data+=".";
        }

        return data;
    }

    public String getKey(){
        String data="";
        for (int i = 0; i <this.SimbolosProduccion.size() ; i++) {
            data+=SimbolosProduccion.get(i).token+" ";
        }
        return data;
    }

    public void setDad(String d){
    this.padre=d;
    }
}
