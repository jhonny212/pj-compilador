/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.Interfaz.Configs;


import com.mycompany.programming.lan.Gramatica.Errores.ErrorClass;
import com.mycompany.programming.lan.Interfaz.Contenido;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author jhonny
 */
public class crearEditor {

    JTabbedPane Content;

    public crearEditor(JTabbedPane Content) {
        this.Content = Content;
    }

    
    public void create(String title) {
        Contenido t = new Contenido();
        t.name = title;
        Content.add(title, t);

    }

    public void create(String title, File file) {
        Contenido f = new Contenido();
        f.f = file;
        f.name = title;
        f.setTexto(read(file));
        Content.add(title, f);
    }

    public void save(int opc) {
        try {
            Contenido cnt = getCl();
            if (opc == 0) {
                if (cnt.f == null) {
                    if (!cnt.name.isBlank()) {
                        obtenerRuta(cnt.name, cnt.getTexto());
                    } else {
                        JOptionPane.showMessageDialog(Content.getParent(), "No se pudo guardar, intente de nuevo");
                    }
                } else {
                    write(cnt.f, cnt.getTexto());
                }
            } else {

            }
            Content.remove(Content.getSelectedIndex());

        } catch (IndexOutOfBoundsException ex) {
        } catch (NullPointerException ex) {

        }
    }

    //Escritura del texto
    public void write(File path, String texto) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            pw.write(texto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    //Obtener ruta
    public File GetFile() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(Content.getParent());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            if (fichero.exists()) {
                return fichero;
            }
        }
        return null;
    }

   
    //Get selected component
    public Contenido getCl() {
        try {
            return (Contenido) Content.getSelectedComponent();

        } catch (NullPointerException ex) {
        }
        return null;
    }

    public void addTable(String titulo,String titles[], String array[][]) {
        Contenido tabla = new Contenido();
        //Contenido producciones = new Contenido();
        tabla.initTable(array, titles);
        Content.add(titulo, tabla);
        /*Content.add(titulo, producciones);*/

    }

    void obtenerRuta(String name, String texto) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("select folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int seleccion = chooser.showSaveDialog(Content.getParent());
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getCurrentDirectory();
            String path = f.getAbsolutePath() + "/" + name;
            write(new File(path), texto);
        }
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
                retorno += linea;
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

    public void saveAs(String name) {
        try {
            Contenido f = getCl();
            obtenerRuta(name, f.getTexto());
        } catch (Exception ex) {
        }

    }
    
    public void addError(ArrayList<ErrorClass.errorProduced> listado,String name){
     Contenido t=new Contenido();
     t.addError(listado);
     Content.add(name, t);
    }
}
