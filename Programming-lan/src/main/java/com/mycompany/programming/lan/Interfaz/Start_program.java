/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Interfaz;

import com.mycompany.programming.lan.Gramatica.AFD.analizadorLexico;
import com.mycompany.programming.lan.Gramatica.TablaLALR.Compilador;
import com.mycompany.programming.lan.Gramatica.lenguaje;
import com.mycompany.programming.lan.Gramatica.lexer;
import com.mycompany.programming.lan.Gramatica.parser;
import com.mycompany.programming.lan.Gramatica.pilaLALR;
import com.mycompany.programming.lan.Interfaz.Configs.crearEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;

import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author jhonny
 */
public class Start_program extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private final crearEditor edit;

    public Start_program() {
        initComponents();
        this.setLocationRelativeTo(this);
        edit = new crearEditor(this.Content);
        init();

    }
    String path = "";

    void init() {
        File file = new File("");
        path = file.getAbsolutePath() + "/src/main/java/com/mycompany/programming/lan/Interfaz/repositorios";
        File files[] = new File(path).listFiles();
        for (File archivo : files) {
            addMenu(archivo.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Lanselected = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Content = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        lans = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Lanselected.setText("Lenguaje seleccionado: None");

        jScrollPane1.setViewportView(Content);

        jButton1.setText("Cerrar ventana");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Guardar como");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        lans.setText("Lenguajes");
        jMenuBar1.add(lans);

        jMenu3.setText("Ejecutar");

        jMenuItem6.setText("Compilar lenguaje");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Cargar lenguaje");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Borrar lenguaje");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ver");

        jMenuItem9.setText("Tabla LALR");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("Pila");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Informacion del lenguaje");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Lanselected, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lanselected, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String name = "";
        while (name.isBlank() || name.isEmpty()) {
            name = JOptionPane.showInputDialog(this, "Ingrese el nombre de su documento");
            if (name == null) {
                return;
            }
        }
        edit.create(name);


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Contenido cn = edit.getCl();
            if (cn.bool) {
                this.Content.remove(cn);
                return;
            }
            final int opc = JOptionPane.showConfirmDialog(this, "¿Desea guardar el documento?");
            if (opc != 2) {
                edit.save(opc);
            }
        } catch (Exception ex) {
            try {
                this.Content.remove(this.Content.getSelectedComponent());
            } catch (Exception exx) {
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem3ActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Contenido href = edit.getCl();
            if (href.f != null) {
                if (href.f.exists()) {
                    edit.write(href.f, href.getTexto());
                } else {
                    JOptionPane.showMessageDialog(this, "no existe la ruta del archivo " + href.f.getAbsolutePath());
                }
            } else {
                edit.saveAs(href.name);
            }

        } catch (Exception ex) {

        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            Contenido f = edit.getCl();
            if (f == null) {
                return;
            }
            String name = "";
            while (name.isBlank() || name.isEmpty()) {
                name = JOptionPane.showInputDialog(this, "Ingrese el nombre de su documento");
                if (name == null) {
                    return;
                }
            }
            edit.saveAs(name);
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        File file = edit.GetFile();
        if (file != null) {
            edit.create(file.getName(), file);
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if (lenguaje != null) {
            edit.addTable("Tabla LALR", this.path + "/" + lenguaje.nombre + "/" + lenguaje.nombre + "_LALR.csv", lenguaje.fila);
            edit.addTable(lenguaje.getProducciones());
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            Contenido cnt = edit.getCl();
            if (cnt == null) {
                return;
            }
            String name = FilenameUtils.getExtension(cnt.name);
            if (!name.equals("len")) {
                JOptionPane.showMessageDialog(this, "Solo se puede leer archivos con extension .len");
                return;
            }
            parser lan = CargarLenguaje(cnt.getTexto());
            if (lan != null) {
                if (lan.listadoDeErrores.haveErrors()) {
                    existe(lan.nameProgram);
                    this.Lanselected.setText(lan.nameProgram);
                    compilar();

                } else {
                    if (!lan.listadoDeErrores.lexico.isEmpty()) {
                        edit.addError(lan.listadoDeErrores.lexico, "Errores lexico");
                    }
                    if (!lan.listadoDeErrores.semantico.isEmpty()) {
                        edit.addError(lan.listadoDeErrores.semantico, "Errores semantico");
                    }
                    if (!lan.listadoDeErrores.sintactico.isEmpty()) {
                        edit.addError(lan.listadoDeErrores.sintactico, "Errores sintactico");
                    }

                }
            }
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    boolean existe(String name) {
        int x = this.lans.getComponentCount();
        for (int i = 0; i <= x + 1; i++) {
            try {
                JMenuItem m = this.lans.getItem(i);
                if (m.getText().equals(name)) {
                    return true;
                }
            } catch (Exception ex) {
            }

        }

        addMenu(name);
        return false;
    }
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        if (lenguaje == null) {
            return;
        }
        if (!lenguaje.exito) {
            JOptionPane.showMessageDialog(this, "No es posible compilar con este lenguaje, verifique la tabla LALR");
        }
        try {
            if (this.Content.getComponentCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay un campo de texto disponible");
            } else {
                Contenido cn = edit.getCl();
                if (lenguaje.extension != null) {
                    if (!lenguaje.extension.isEmpty()) {
                        String ext = FilenameUtils.getExtension(cn.name);
                        if (!lenguaje.extension.equals(ext)) {
                            JOptionPane.showMessageDialog(this, "El archivo debe ser extension " + lenguaje.extension);
                            return;
                        }
                    }
                }

                if (!cn.bool) {
                    String texto = cn.getTexto();
                    analizadorLexico lexer = new analizadorLexico(lenguaje.tablaAFD);
                    lexer.init(texto);
                    Compilador cmp = new Compilador(lexer, lenguaje);
                    cmp.init();
                     if (cmp.compilado && lexer.errorList.haveErrors()) {
                        this.pila = cmp.moves;
                        JOptionPane.showMessageDialog(this, "La cadena fue aceptada");
                        edit.addTable(cmp.listado);
                    } else {
                        
                        if (!lexer.errorList.lexico.isEmpty()) {
                            edit.addError(lexer.errorList.lexico, "Errores lexico");
                        }
                        if (!lexer.errorList.sintactico.isEmpty()) {
                            edit.addError(lexer.errorList.sintactico, "Errores Sintactico");
                        }
                        JOptionPane.showMessageDialog(this, "La cadena fue rechazada");
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {

            JComboBox cmp = new JComboBox();
            for (int i = 0; i <= this.lans.getComponentCount(); i++) {
                JMenuItem m = this.lans.getItem(i);
                cmp.addItem(m.getText());
            }
            Object[] options = new Object[]{};
            JButton acept = new JButton("Aceptar");
            JOptionPane jop = new JOptionPane("Seleccione un lenguaje",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);
            jop.add(cmp);
            jop.add(acept);
            JDialog diag = new JDialog(this);
            diag.getContentPane().add(jop);
            diag.pack();
            diag.setVisible(true);
            acept.addActionListener((e) -> {
                diag.setVisible(false);
                int y = cmp.getSelectedIndex();
                JMenuItem m = this.lans.getItem(y);
                this.lans.remove(y);
                File carpeta = new File(path + "/" + m.getText());
                try {
                    File[] files = carpeta.listFiles();
                    for (File x : files) {
                        x.delete();
                    }
                } catch (Exception ex) {
                }

                carpeta.delete();
                if (m.getText().equals(this.Lanselected.getText())) {
                    this.lenguaje = null;
                    this.pila = null;
                    this.pila=null;
                    this.Lanselected.setText("Lenguaje seleccionano none");
                }
                m = null;

            });
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_jMenuItem8ActionPerformed
    pilaLALR pila;
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (pila == null) {
            return;
        }
        edit.addPila(pila.tm, pila.getMoves());
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JOptionPane.showMessageDialog(this, lenguaje.getInfo());
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private parser CargarLenguaje(String file) {

        lexer scan = new lexer(new BufferedReader(new StringReader(file)));
        parser parser = new parser(scan);
        try {
            try {
                parser.parse();
            } catch (Exception e) {
            }
            if (!parser.listadoDeErrores.haveErrors()) {
                parser.listadoDeErrores.lexico = scan.error.lexico;
            }

            return parser;
        } catch (Exception ex) {
            System.out.println("ex" + ex.getLocalizedMessage());
        }
        return null;
    }

    private lenguaje lenguaje = null;

    private void compilar() {
        lenguaje = null;
        File File = new File(path + "/" + this.Lanselected.getText() + "/" + this.Lanselected.getText() + ".bin");
        leerBin(File);
        if (lenguaje != null) {
            lenguaje.cargarLenguaje(path);
            if (!lenguaje.exito) {
                JOptionPane.showMessageDialog(this, "Posible confusion en tabla LALR, verificar");
            }

            lenguaje.compilar();
            if (lenguaje.claseCompilada == null) {
                JOptionPane.showMessageDialog(this, "Advertencia, verificar sus reglas semanticas, "
                        + "que este correctamente escrita en codigo java");
            }
            //enguaje.testFila();

        }
    }

    private void leerBin(File arch) {
        try {
            FileInputStream file = new FileInputStream(arch);
            ObjectInputStream in = new ObjectInputStream(file);
            lenguaje = (lenguaje) in.readObject();
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (final ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Start_program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (final InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start_program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (final IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start_program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (final javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start_program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start_program().setVisible(true);
            }
        });
    }

    void addMenu(String name) {
        JMenuItem m = new JMenuItem(name);
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lanselected.setText(m.getText());
                compilar();
            }
        });
        this.lans.add(m);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Content;
    private javax.swing.JLabel Lanselected;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenu lans;
    // End of variables declaration//GEN-END:variables
}
