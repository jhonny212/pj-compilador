/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Gramatica.TablaLALR;

import com.mycompany.programming.lan.Gramatica.AFD.Token;
import com.mycompany.programming.lan.Gramatica.AFD.analizadorLexico;
import com.mycompany.programming.lan.Gramatica.lenguaje;
import com.mycompany.programming.lan.Gramatica.pilaLALR;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.lang.reflect.Method;

import java.util.HashMap;

/**
 *
 * @author jhonny
 */
public class Compilador {

    final Produccion LISTADOOFPRODUCTIONS[];
    final String FILA[];
    final HashMap<Integer, Transicion[]> TRANSICIONES;
    ArrayList<Integer> pilaDetransiciones = new ArrayList<>();
    ArrayList<Token> pilaDetokens = new ArrayList<>();
    analizadorLexico lexer;
    public final pilaLALR moves;
    //String moves = "";
    Token prev = null;
    Object claseCompilada = null;
    public boolean compilado = false;

    public Compilador(analizadorLexico lexer, lenguaje lan) {
        this.LISTADOOFPRODUCTIONS = lan.producciones;
        this.FILA = lan.fila;
        this.TRANSICIONES = lan.tablaLALR;
        this.lexer = lexer;
        this.claseCompilada = lan.claseCompilada;
        moves=new pilaLALR();
    }
    //String movess="";
    public void init() {
        
        Token tk = lexer.nextToken();
        this.pilaDetransiciones.add(0);
        moves.add(tk.getValue(),0,0);
        /*movess = "START{";
        movess += "\nPILA_TOKENS ADD: empty";
        movess += "\nPILA_TRANS  ADD: " + 0;
        movess += "\nPILA_ENTRADA CHECK: " + tk.getToken();
        movess += "\n}\n";*/
        try {
            pila(0, tk);
        } catch (Exception ex) {
            //System.out.println(moves);
        }
      
        //System.out.println(movess);
    }

    private void pila(int i, Token tk) {
        
        int busqueda[] = buscar(i, tk.getToken());
        if (busqueda != null) {
            switch (busqueda[1]) {
                case 0:
                    reduce_(busqueda[0], tk);
                    break;
                case 1:
                    switch_(busqueda[0], tk);
                    break;
                case 2:
                    break;
            }
        }

    }

    private void pila(int i, Token tk, Token prev) {
        int busqueda[] = buscar(i, tk.getToken());
        if (busqueda != null) {
            switch (busqueda[1]) {
                case 0:
                    reduce_(busqueda[0], tk);
                    break;
                case 1:
                    switch_(busqueda[0], tk);
                    break;
                case 2:
                    goTo_(busqueda[0], prev);
                    break;
            }
        }

    }

    int[] buscar(int i, String dato) {
        if (dato.equals("$") && i == 1) {
            this.compilado = true;
            return null;
        }
        int[] res = new int[2];
        Transicion[] trs = TRANSICIONES.get(i);
        for (int j = 1; j < FILA.length; j++) {
            Transicion tr = trs[j - 1];
            if (tr != null) {
                if (FILA[j].equals(dato)) {
                    res[0] = tr.transicion;
                    switch (tr.tipo) {
                        case "Reduce":
                            res[1] = 0;
                            break;
                        case "Switch":
                            res[1] = 1;
                            break;
                        case "Go-to":
                            res[1] = 2;

                    }
                    return res;
                }

            }

        }
        return null;
    }

    void switch_(int i, Token tk) {
        this.pilaDetokens.add(tk);
        this.pilaDetransiciones.add(i);
        Token href = lexer.nextToken();
        this.moves.add(href.getValue(),i,0,0,tk.getToken());
        /*movess += "SWITCH{";
        movess += "\n    ADD TKS: " + tk.getToken();
        movess += "\n    ADD TRANS: " + i;
        movess += "\n    PILA_ENTRADA CHECK: " + href.getToken();
        movess += "\n}\n";*/

        pila(i, href);

    }

    void reduce_(int i, Token w) {
        Produccion x = LISTADOOFPRODUCTIONS[i];
        //movess += "REDUCE{";
        Token tkn = new Token(x.padre);

        if (!x.isLambda) {
            try {
                switch (x.tipo) {
                    case 0:
                        tkn.addValue(getObjReduc(x.num));
                        break;
                    case 1:
                        tkn.addValue(getStrReduc(x.num));
                        break;
                    case 2:
                        tkn.addValue(getIntReduc(x.num));
                        break;
                    case 3:
                        tkn.addValue(getFloatReduc(x.num));
                        break;
                }
            } catch (Exception ex) {
            }
            int cnt=0;
            for (int j = 0; j < x.SimbolosProduccion.size(); j++) {
                int tm = this.pilaDetokens.size() - 1;
                int tm2 = this.pilaDetransiciones.size() - 1;
                
                //movess += "\n    REMOVE TKS:" + this.pilaDetokens.get(tm).getToken();
                //movess += "\n    REMOVE TRANS:" + this.pilaDetransiciones.get(tm2);
                this.pilaDetokens.remove(tm);
                this.pilaDetransiciones.remove(tm2);
                cnt++;
            }
            moves.add(cnt, x.padre);
        }
        //movess += "\n    ADD TKS:" + x.padre;

        this.pilaDetokens.add(tkn);
        int y = this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1);
        Token z = this.pilaDetokens.get(this.pilaDetokens.size() - 1);
        //movess += "\n}\n";
        pila(y, z, w);

    }

    void goTo_(int i, Token tk) {

        /*movess += "GO_TO{";
        movess += "\n    ADD TRANS: " + i;
        movess += "\n}\n";*/
        moves.add(i);
        this.pilaDetransiciones.add(i);
        pila(this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1), tk);
    }

    int getIntReduc(int x) {

        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method" + x, partypes);
            Object obj[] = new Object[1];
            obj[0] = this.pilaDetokens;
            return (int) m1.invoke(claseCompilada, obj);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

        }
        return -1;
    }

    Object getObjReduc(int x) {
        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method" + x, partypes);
            Object obj[] = new Object[1];
            obj[0] = this.pilaDetokens;
            return m1.invoke(claseCompilada, obj);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

        }
        return null;
    }

    double getFloatReduc(int x) {
        double resultado = 0.0;
        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method" + x, partypes);
            Object obj[] = new Object[1];
            obj[0] = this.pilaDetokens;
            resultado = (double) m1.invoke(claseCompilada, obj);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

        }
        return resultado;
    }

    String getStrReduc(int x) {
        String resultado = "";
        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method" + x, partypes);
            Object obj[] = new Object[1];
            obj[0] = this.pilaDetokens;
            resultado = (String) m1.invoke(claseCompilada, obj);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

}
