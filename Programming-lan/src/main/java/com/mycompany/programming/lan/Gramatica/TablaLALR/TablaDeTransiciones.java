package com.mycompany.programming.lan.Gramatica.TablaLALR;

import java.util.ArrayList;
import java.util.HashMap;

public final class TablaDeTransiciones {

    HashMap<String, TablaDeProduccion> href;
    TablaDeProduccion inicio;
    ArrayList<Subtabla> tablasLL1 = new ArrayList<>();

    public TablaDeTransiciones(HashMap<String, TablaDeProduccion> href, TablaDeProduccion inicio) {
        this.href = href;
        this.inicio = inicio;
    }

    public void init() {
        GenerarPrimeros();
        GenerarTabla();
    }

    public void clean() {
        this.href.clear();
        this.href = null;
        this.ingresados.clear();
        this.ingresados = null;
    }

    void GenerarPrimeros() {
        for (TablaDeProduccion x : href.values()) {
            if(x!=null){
            if (!Contains(x.produccion)) {
                isLambda = false;
                first(x, x);
                x.isLambda = isLambda;
                pila.clear();
            }
            }
        }

    }

    void GenerarTabla() {
        Subtabla tb = new Subtabla(0);
        ArrayList<SymToken> syms = new ArrayList<>();
        syms.add(new SymToken(inicio.produccion));
        syms.add(new SymToken("$"));
        tb.addContents(new Produccion(syms, ""), "begun", "?", href);
        generar(tb);
        this.tablasLL1.add(tb);

        int contador = 0;
        for (int i = 0; i < this.tablasLL1.size(); i++) {
            Subtabla w = this.tablasLL1.get(i);
            ingresados.clear();
            if (!w.checkeado) {
                w.checkeado = true;
                for (int j = 0; j < w.listado.size(); j++) {
                    //Obteniendo produccion
                    FilaSub x = w.listado.get(j);
                    //Verificar si tiene mas token de produccion
                    if (x.haveNext()) {
                        //Obtener llave o primer 
                        String llave = x.getLLave(x.posPunto + 1);
                        String nextoken = x.getNext();
                        if (nextoken.equals("$")) {
                            continue;
                        }
                        if (nextoken.equals("LAMMBDAAAAA")) {
                            continue;
                        }
                        if (!ingresados.containsKey(nextoken)) {
                            int pos = existSub(llave, x.siguientes);
                            if (pos < 0) {
                                if (pos == -1) {
                                    contador++;
                                    generarSubtabla(x, contador, nextoken);
                                } else {
                                    contador++;
                                    generarSubtabla(x, contador, nextoken);
                                     this.tablasLL1.get(contador).hrefTablas = this.posicionHref;

                                }
                            } else {
                                ingresados.put(nextoken, pos);
                                x.transicion = pos;
                            }
                        } else {
                            int f = ingresados.get(nextoken);
                            x.transicion = f;
                            boolean v = this.tablasLL1.get(f).checkeado;
                            if (!v) {
                                FilaSub tmp = new FilaSub(x.producionData, x.padre);
                                tmp.siguientes = x.siguientes;
                                tmp.posPunto = x.posPunto + 1;
                                this.tablasLL1.get(f).listado.add(tmp);
                                generar(this.tablasLL1.get(f), tmp);
                            }
                        }
                    }
                }
            }
            System.out.println("\n*************"+w.num);
            w.printTable();
        }

    }

    void generarSubtabla(FilaSub x, int contador, String nextoken) {
        x.transicion = contador;
        Subtabla z = new Subtabla(contador);
        FilaSub tmp = new FilaSub(x.producionData, x.padre);
        tmp.siguientes = x.siguientes;
        tmp.posPunto = x.posPunto + 1;
        z.listado.add(tmp);
        generar(z);
        ingresados.put(nextoken, contador);
        this.tablasLL1.add(z);

    }

    HashMap<String, Integer> ingresados = new HashMap<>();

    public int posicionHref;

    int existSub(String key, String sig) {
        int retorno = -1;
        posicionHref = 0;
        boolean v=true;
        for (int i = this.tablasLL1.size()-1; i >=0 ; i--) {
            if (this.tablasLL1.get(i).getLLave().equals(key)) {
                Subtabla w = this.tablasLL1.get(i);
                if (w.listado.get(0).siguientes.equals(sig)) {
                    return i;
                } else {
                    if(v){
                    posicionHref = i;
                    v=false;
                    }
                    retorno = -2;
                }
            }
        }

        return retorno;
    }

    //Metodo para crear primero
    private void first(TablaDeProduccion x, TablaDeProduccion pr) {
        pila.add(x.produccion);
        for (Produccion y : x.listado) {
            if (y.SimbolosProduccion != null) {
                SymToken z = y.SimbolosProduccion.get(0);
                if (!z.token.equals("LAMMBDAAAAA")) {
                    if (!z.token.equals(z.token.toUpperCase())) {
                        pr.addSiguiente(z.token);
                    } else {
                        TablaDeProduccion w = href.get(z.token);
                        if (!Contains(w.produccion)) {
                            first(w, pr);
                        }
                    }
                } else {
                    isLambda = true;
                }
            } else {
                isLambda = true;
            }
        }
    }
    ArrayList<String> pila = new ArrayList<>();
    boolean isLambda = false;

    //Si en la pila ya paso esto
    boolean Contains(String data) {
        for (String x : pila) {
            if (data.equals(x)) {
                return true;
            }
        }
        return false;
    }

    //Crear nuevas subtablas    
    void generar(Subtabla sub) {
        for (int i = 0; i < sub.listado.size(); i++) {
            FilaSub x = sub.listado.get(i);
            if (x.haveNext()) {
                String dd = x.getNext();
                if (!dd.equals("$")) {
                    //Si una produccion de la tabla produce un no terminal
                    if (!dd.equals(dd.toLowerCase())) {
                        TablaDeProduccion hr = href.get(dd);
                        if (hr != null) {
                            sub.addContents(hr.listado, dd, buscarSiguiente(x), href);
                        }
                    }
                }
            }
        }
    }

    //Crear el inicio de la tabla
    void generar(Subtabla sub, FilaSub x) {
        if (x.haveNext()) {
            String dd = x.getNext();
            if (!dd.equals("$")) {
                if (!dd.equals(dd.toLowerCase())) {
                    TablaDeProduccion hr = href.get(dd);
                    sub.addContents(hr.listado, dd, buscarSiguiente(x), href);
                }
            }
        }

    }

    //Buscar el siguiente de una produccion
    public String buscarSiguiente(FilaSub href) {
        String list = "";
        //Si el punto esta en su maximo, retornar sus siguientes
        if (href.posPunto == (href.producionData.SimbolosProduccion.size() - 1)) {
            return href.siguientes;
        } else {
            //Validar mientras una produccion sea terminar o no terminal y no tenga lambda
            for (int i = href.posPunto + 1; i < href.producionData.SimbolosProduccion.size(); i++) {
                String token = href.getNext(i);

                if (!token.equals(token.toUpperCase()) || token.equals("$")) {
                    return token;
                } else {
                    System.out.println("BUSCANDO " + token);
                    TablaDeProduccion tbl = this.href.get(token);
                    list += tbl.primeros;

                    if (tbl.isLambda) {
                        if (i == (href.producionData.SimbolosProduccion.size() - 1)) {
                            list += "," + href.siguientes;
                        } else {
                            list += ",";
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return list;
    }

    /*void sameSubtabla(int x, int cnt) {
        if (this.tablasLL1.get(x).hrefTablas.isEmpty()) {
            this.tablasLL1.get(x).hrefTablas = "" + cnt;
        } else {
            this.tablasLL1.get(x).hrefTablas = "," + cnt;

        }
    }*/
}
