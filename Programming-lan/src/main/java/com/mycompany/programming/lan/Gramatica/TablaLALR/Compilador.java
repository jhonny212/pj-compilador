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
    public final ArrayList<Object[]> listado=new ArrayList<>();
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
        moves = new pilaLALR();
    }

  
    public void init() {

        Token tk = lexer.nextToken();
        this.pilaDetransiciones.add(0);
        String dato = "Inicio: token de entrada " + tk.getToken() + "\n";
        dato += "Añadir 0 en pila de transiciones";
        moves.add(tk.getValue(), 0, 0, dato);
        try {
            pila(0, tk);
        } catch (Exception ex) {
        }

    }

    private void pila(int i, Token tk) {

        int busqueda[] = buscar(i, tk.getToken());
        /*while(busqueda==null && !this.lexer.hashMore() && !tk.getToken().equals("$")){
            tk=this.lexer.nextToken();
            busqueda=buscar(i, tk.getValue());
        }*/
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
        int busqueda[] = null;
        busqueda=buscar(i, tk.getToken());
         /*while(busqueda==null && !this.lexer.hashMore() && !tk.getToken().equals("$")){
            tk=this.lexer.nextToken();
            busqueda=buscar(i, tk.getValue());
        }*/
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
        String dato = "Switch: \n"
                + "Agregar a pila de tokens " + tk.getToken() + "\n"
                + "Agregar a pila de transiciones " + i + "\n"
                + "Leer token " + href.getToken();

        this.moves.add(href.getValue(), i, 0, 0, tk.getToken(), dato);
        pila(i, href);

    }
   
    void reduce_(int i, Token w) {
        Produccion x = LISTADOOFPRODUCTIONS[i];
        Token tkn = new Token(x.padre);
        Object ac[]=new Object[2];
            try {
                switch (x.tipo) {
                    case 0:
                        Object obj=getObjReduc(x.num,x.isLambda);
                        tkn.addValue(obj);
                        ac[0]=x.padre+"::"+x.getPrs();
                        ac[1]= obj!=null ? obj: "{}";
                        
                        this.listado.add(ac);
                        break;
                    case 1:
                        String val=getStrReduc(x.num,x.isLambda);
                        tkn.addValue(val);
                        ac[0]=x.padre+"::"+x.getPrs();
                        ac[1]=val;
                        this.listado.add(ac);
                      
                        break;
                    case 2:
                        int data=getIntReduc(x.num,x.isLambda);
                        tkn.addValue(data);
                        ac[0]=x.padre+"::"+x.getPrs();
                        ac[1]=data;
                        
                        this.listado.add(ac);
                      
                        break;
                    case 3:
                        double db=getFloatReduc(x.num,x.isLambda);
                        tkn.addValue(db);
                        ac[0]=x.padre+"::"+x.getPrs();
                        ac[1]=db;
                       
                        this.listado.add(ac);
                      
                        break;
                }
            } catch (Exception ex) {
                if(claseCompilada!=null){
                }
            }
            int cnt = 0;
            if(!x.isLambda){
            for (int j = 0; j < x.SimbolosProduccion.size(); j++) {
                int tm = this.pilaDetokens.size() - 1;
                int tm2 = this.pilaDetransiciones.size() - 1;
                this.pilaDetokens.remove(tm);
                this.pilaDetransiciones.remove(tm2);
                cnt++;
            }
            moves.add(cnt, x.padre, "Reduce: \nremover de pila de tokens, " + cnt + " tokens \n"
                    + "remover de la pila de transiciones  " + cnt + "transiciones  \n"
                    + "agregar a pila de tokens " + x.padre);
            }else{
                moves.add(-15,x.padre,"Agregar a pila de tokens "+x.padre);
            }
        this.pilaDetokens.add(tkn);
        int y = this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1);
        Token z = this.pilaDetokens.get(this.pilaDetokens.size() - 1);
        pila(y, z, w);

    }

    void goTo_(int i, Token tk) {
        moves.add(i, "Go-to: \n Añadir a pila de transiciones " + i);
        this.pilaDetransiciones.add(i);
        pila(this.pilaDetransiciones.get(this.pilaDetransiciones.size() - 1), tk);
    }

    int getIntReduc(int x,boolean isLambda) {

        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method_$" + x, partypes);
            Object obj[] = new Object[1];
            if(isLambda){
            obj[0] = new ArrayList<Token>();
            }else{
            obj[0] = this.pilaDetokens;
            }
            return (int) m1.invoke(claseCompilada, obj);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        }
        return -1;
    }

    Object getObjReduc(int x,boolean isLambda) {
        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method_$" + x, partypes);
            Object obj[] = new Object[1];
            if(isLambda){
            obj[0] = new ArrayList<Token>();
            }else{
            obj[0] = this.pilaDetokens;
            }
            return m1.invoke(claseCompilada, obj);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        }
        return null;
    }

    double getFloatReduc(int x,boolean isLambda) {
        double resultado = 0.0;
        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method_$" + x, partypes);
            Object obj[] = new Object[1];
            if(isLambda){
            obj[0] = new ArrayList<Token>();
            }else{
            obj[0] = this.pilaDetokens;
            }
            resultado = (double) m1.invoke(claseCompilada, obj);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        }
        return resultado;
    }

    String getStrReduc(int x,boolean isLambda) {
        String resultado = "";
        try {
            Class partypes[] = new Class[1];
            partypes[0] = ArrayList.class;
            Method m1 = claseCompilada.getClass().getDeclaredMethod("method_$" + x, partypes);
            Object obj[] = new Object[1];
            if(isLambda){
            obj[0] = new ArrayList<Token>();
            }else{
            obj[0] = this.pilaDetokens;
            }
            resultado = (String) m1.invoke(claseCompilada, obj);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        }
        return resultado;
    }
    
    void recuperarDeError(){
        
    }
   

}
