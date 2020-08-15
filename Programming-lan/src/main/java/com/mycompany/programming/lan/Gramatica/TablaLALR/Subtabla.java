package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.util.ArrayList;
import java.util.HashMap;

public class Subtabla {

    public boolean checkeado;
    public int num;
    ArrayList<FilaSub> listado;
    public String hrefTablas;
    public Subtabla(int num) {
        checkeado = false;
        this.num = num;
        listado = new ArrayList<>();
        hrefTablas="";
    }

    public void addContents(ArrayList<Produccion> prs, String pr,
            String sig, HashMap<String, TablaDeProduccion> table) {
        
        for (Produccion x : prs) {
            if(x.SimbolosProduccion!=null){
            String add = x.getKey(0);
            int ps = exists(add);
            if (ps == -1) {
                FilaSub tmp = new FilaSub(x, pr);
                tmp.siguientes = sig;
                this.listado.add(tmp);
            } else {
                this.listado.get(ps).addSiguiente(sig);
                //this.listado.get(ps).siguientes += "," + sig;
            }
            }
        }
    }

    public void addContents(Produccion x, String pr,
            String sig, HashMap<String, TablaDeProduccion> table) {
        String add = x.getKey(0);
        int ps = exists(add);
        if (ps == -1) {
            FilaSub tmp = new FilaSub(x, pr);
            tmp.siguientes = sig;
            this.listado.add(tmp);
        } else {
            this.listado.get(ps).addSiguiente(sig);
          
        }
    }

    private int exists(String key) {
        if (this.listado.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.listado.size(); i++) {
            FilaSub t = this.listado.get(i);
            if (t.getLLave(t.posPunto).equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public String getFirst() {
        return listado.get(0).padre;
    }

    public void printTable() {
        for (FilaSub w : listado) {
            String data = w.getPrint();
            System.out.println(w.padre + "-> " + data + "  SIG:" + w.siguientes+" MOVER A: "+w.transicion);
        }
    }

    
     
    public boolean haveToken(String tk,int i){
        for (int j=0; j <i; j++) {
            String dd=this.listado.get(j).producionData.SimbolosProduccion.get(0).token;
            if(dd.equals(tk)){
                tabla=j;
                return false;
            }
        }
    return true;
    }
    
    public int tabla=0;
    
    public int numOfTks(String tkn){
        int cnt=0;
        for (FilaSub x: listado) {
           
        }
    return cnt;
    }
 
    public String getLLave() {

        return this.listado.get(0).getLLave(this.listado.get(0).posPunto);
    }
    
   
}
