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

    private ArrayList<movimiento> moves;
    public int tm = 0;

    public pilaLALR() {
        moves = new ArrayList<>();
    }

    public void add(String tk) {
        moves.add(new movimiento(new movimiento_check(tk), null, null));
        tm++;

    }

    public void add(String tk, int trans, int r) {
        moves.add(new movimiento(new movimiento_check(tk), null, new movimiento_trans(trans, r)));
        tm++;

    }

    public void add(String tk, int trans, int r, int x, String ww) {
        moves.add(new movimiento(new movimiento_check(tk), new movimiento_tokens(x, ww), new movimiento_trans(r, trans)));
        tm++;

    }

    public void add(int trans, String add) {
        moves.add(new movimiento(null, new movimiento_tokens(trans, add), new movimiento_trans(trans, -1)));
    }

    public void add(int trans) {
        moves.add(new movimiento(null, null, new movimiento_trans(0, trans)));
    }

    public ArrayList<movimiento> getMoves() {
        return moves;
    }

    public class movimiento {

        public final movimiento_check p1;
        public final movimiento_tokens p3;
        public final movimiento_trans p2;

        public movimiento(movimiento_check p1, movimiento_tokens p3, movimiento_trans p2) {
            this.p1 = p1;
            this.p3 = p3;
            this.p2 = p2;
        }

    }

    public class movimiento_check {

        String pilaCheck;

        public movimiento_check(String pilaCheck) {
            this.pilaCheck = pilaCheck;

        }

        public String getPilaCheck() {
            return pilaCheck;
        }

    }

    public class movimiento_tokens {
       
        final public int reduce;
        final public String tokenAdd;
        public String removes;
        public movimiento_tokens(int reduce, String tokenAdd) {
            this.reduce = reduce;
            this.tokenAdd = tokenAdd;
            this.removes="";
        }
        public void add(String x){
            if(this.removes.isEmpty()){
            this.removes=(x);
            }else{
            this.removes+=","+x;
            }
        }
    }

    public class movimiento_trans {

        final public int reduce;
        final public int addTrans;
        public String removes;
        public movimiento_trans(int reduce, int addTrans) {
            this.reduce = reduce;
            this.addTrans = addTrans;
            this.removes="";
        }
        
        public void add(String x){
            if(this.removes.isEmpty()){
            this.removes=(x);
            }else{
            this.removes+=","+(x);
            }
        }

    }

}
