package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.io.Serializable;
import java.util.ArrayList;

public class Produccion implements Serializable {

    private static final long serialversionUID
            = 129348938L;
    public String reglasSemanticas;
    public ArrayList<SymToken> SimbolosProduccion;
    final public int num;
    String padre;
    int tipo = -1;
    final public boolean isLambda;

    public Produccion(ArrayList<SymToken> list, String rules, int numero) {
        if (list != null) {
            this.SimbolosProduccion = list;
            this.isLambda = false;
        } else {
            this.SimbolosProduccion = new ArrayList<>();
            this.SimbolosProduccion.add(new SymToken("LAMMBDAAAAA"));
            this.isLambda = true;
        }
        this.ruls = rules;
        if (rules.isEmpty()) {
            this.reglasSemanticas = rules;
        } else {
            this.reglasSemanticas = getVars() + rules;
        }
        this.num = numero;

    }

    public Produccion(ArrayList<SymToken> list, String rules) {
        if (list != null) {
            this.SimbolosProduccion = list;
            this.isLambda = false;
        } else {
            this.SimbolosProduccion = new ArrayList<>();
            this.SimbolosProduccion.add(new SymToken("LAMMBDAAAAA"));
            this.isLambda = true;
        }
        this.ruls = rules;
        if (rules.isEmpty()) {
            this.reglasSemanticas = rules;

        } else {
            this.reglasSemanticas = getVars() + rules;
        }
        this.num = -1;
    }

    public String ruls;

    public void setTipo(int x) {
        this.tipo = x;
    }

    public int tipo() {
        return this.tipo;
    }

    public String getKey(int posPunto) {
        String data = "";
        boolean v = true;
        for (int i = 0; i < this.SimbolosProduccion.size(); i++) {
            if (posPunto == i) {
                data += ". ";
                v = false;
            }
            if (SimbolosProduccion.get(i).token.equals("ap")) {
                data += "(" + " ";

            } else {
                if (SimbolosProduccion.get(i).token.equals("cp")) {
                    data += ")" + " ";

                } else {
                    data += SimbolosProduccion.get(i).token + " ";
                }
            }

        }
        if (v) {
            data += ".";
        }

        return data;
    }

    public String getKey() {
        String data = "";
        for (int i = 0; i < this.SimbolosProduccion.size(); i++) {
            data += SimbolosProduccion.get(i).token + " ";
        }
        return data;
    }

    public void setDad(String d) {
        this.padre = d;
    }

    String getVars() {
        String retorno = "\n";
        int cnt = 0;
        for (int i = this.SimbolosProduccion.size() - 1; i >= 0; i--) {
            SymToken tk = this.SimbolosProduccion.get(i);
            if (tk.IdToken != null) {

                switch (tk.tipo) {
                    case 0:
                        retorno += "Object " + tk.IdToken + " = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-" + cnt + ").getObj();\n";
                        break;
                    case 1:
                        retorno += "String " + tk.IdToken + " = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-" + cnt + ").getValue();\n";
                        break;
                    case 2:
                        retorno += "int " + tk.IdToken + " = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-" + cnt + ").getVal();\n";
                        break;
                    case 3:
                        retorno += "double " + tk.IdToken + " = pDeTkns_$_$_.get(pDeTkns_$_$_.size()-1-" + cnt + ").getReal();\n";
                        break;
                    default:
                        //System.out.println("************************+");
                        break;
                }
            }
            cnt++;
        }

        return retorno;
    }

    public String getPrs() {
        String data = "";
        for (int i = 0; i < this.SimbolosProduccion.size(); i++) {
            SymToken tk = this.SimbolosProduccion.get(i);
            
            if (tk.IdToken == null) {
                data += tk.token + " ";
            } else {
                data += tk.token + ":" + tk.IdToken + " ";
            }

        }
        return data;
    }

}
