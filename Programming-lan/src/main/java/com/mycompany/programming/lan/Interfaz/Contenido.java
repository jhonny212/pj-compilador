/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Interfaz;

import com.mycompany.programming.lan.Gramatica.Errores.ErrorClass;
import com.mycompany.programming.lan.Gramatica.TablaLALR.TablaDeTransiciones;
import com.mycompany.programming.lan.Gramatica.TablaLALR.TablaLL1;
import com.mycompany.programming.lan.Gramatica.parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonny
 */
public class Contenido extends javax.swing.JPanel {

    /**
     * Creates new form Contenido
     */
    public File f = null;
    public String name;

    public Contenido() {
        initComponents();
        TextLineNumber tln1 = new TextLineNumber(this._txt);
        this.scroll.setRowHeaderView(tln1);
    }

    public String getTexto() {
        return _txt.getText().isBlank() ? "" : _txt.getText();
    }

    public void setTexto(String t) {
        _txt.setText(t);
    }

    JTable tabla;

    public void initTable(String path, String[] titulos) {
        bool = true;

        this.tabla = new JTable(getCeldas(path, titulos.length), titulos);
        this.scroll.setRowHeader(null);
        this.scroll.setViewportView(this.tabla);
        showMsj();
    }

    Object[][] getCeldas(String path, int x) {
        String texto = read(new File(path));
        String vect[] = texto.split("\n");
        Object[][] celdas = new String[vect.length][x];
        for (int i = 0; i < vect.length; i++) {
            String celda[] = vect[i].split(",");
            for (int j = 0; j < celda.length; j++) {
                celdas[i][j] = celda[j];
            }
        }
        
        return celdas;
    }

    String read(File file) {
        FileReader fr = null;
        String retorno = "";
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = "";
            while ((linea = br.readLine()) != null) {
                if (retorno.isEmpty()) {
                    retorno = linea;
                } else {
                    retorno += "\n" + linea;

                }
            }
        } catch (IOException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }
        return retorno;
    }

    public void initPrs(String[][] array) {
        bool = true;
        String title[] = {"No", "produccion", "Regla de produccion"};
        tabla = new JTable(array, title);
        this.scroll.setViewportView(this.tabla);
        showMsj();
    }

    public void initPrs(ArrayList<Object[]> list) {
        bool = true;
        tabla = new JTable();
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Produccion");
        tbl.addColumn("Valor");

        for (int i = list.size() - 1; i >= 0; i--) {
            Object object[] = list.get(i);
            tbl.addRow(object);
        }
        this.tabla.setModel(tbl);

        this.scroll.setViewportView(this.tabla);
        showMsj();
    }

    void showMsj() {
        this.tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                int col = tabla.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    try{
                        String msj=tabla.getModel().getValueAt(row, col).toString();
                        String tmp=msj.replaceAll(";", ";\n");
                        JOptionPane.showMessageDialog(null, tmp);
                    }catch(Exception ex){}
                }
            }
        });

    }
    boolean bool = false;

    public void addError(ArrayList<ErrorClass.errorProduced> listado) {

        bool = true;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fila");
        model.addColumn("Columna");
        model.addColumn("Token");
        model.addColumn("Extra");

        for (ErrorClass.errorProduced x : listado) {
            Object vector[] = {x.fila, x.columna, x.token, x.dato};
            model.addRow(vector);
        }

        tabla = new JTable();
        this.tabla.setModel(model);
        this.scroll.setRowHeader(null);
        this.scroll.setViewportView(this.tabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        _txt = new javax.swing.JTextArea();

        _txt.setColumns(20);
        _txt.setRows(5);
        scroll.setViewportView(_txt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea _txt;
    public javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
