/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 *
 * @author sebas
 */
public class Post implements Serializable, Comparable<Post>{
    public String creador, titulo, fecha, foto, contenido, categoria, comment;
    

    public Post(String creador, String titulo, String fecha, String foto, String contenido, String categoria, String comment) {
        this.creador = creador;
        this.titulo = titulo;
        this.fecha = fecha;
        this.foto = foto;
        this.contenido = contenido;
        this.categoria = categoria;
        this.comment = comment;
    }
    
    

    public Post() {
    }

    public String getCategoria() {
        return categoria;
    }
    
    public String getCreador() {
        return creador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getFoto() {
        return foto;
    }

    public String getContenido() {
        return contenido;
    }
    
    public static File buscarPost(String name, File file){
        System.out.println("Estoy buscando " + name);
        File[] list = file.listFiles();
        System.out.println(file.getAbsolutePath());
        File r = null;
        if(list!=null){
            for (File fil : list)
            {
                if(r != null)
                    break;
                if (fil.isDirectory())
                {
                    r = buscarPost(name, fil);
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    System.out.println("Si lo encontre!!!");
                    return fil;
                }
            }
        }
        return r;
    }
    
    public static void generarLista(File file, ArrayList<Post> al){
        File[] list = file.listFiles();
        if(list!=null){
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    generarLista(fil, al);
                }
                else
                {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fil));
                        Post p = (Post)ois.readObject();
                        al.add(p);
                        ois.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void posts(JList j, ArrayList<Post> posts){
        DefaultListModel lm = new DefaultListModel();
        lm.clear();
        for (int i = 0; i < posts.size(); i++) {
            lm.add(i, posts.get(i).titulo);
            System.out.println(posts.get(i).titulo+"\n");
        }
        j.setModel(lm);
    }
    
      public void showImg(String rute, JLabel label) throws IOException{
        File f = new File(rute);
        Image image = ImageIO.read(f);
        ImageIcon icon = new ImageIcon(image);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        label.setText(null);
        label.setIcon(icono);
    }
    
    @Override
    public int compareTo(Post o) {
        if (Long.parseLong(fecha.substring(6, 10)) < Long.parseLong(o.fecha.substring(6, 10))) {
            System.out.println("< Objeto " + titulo + ": Año " + fecha.substring(6, 10) + " Objeto " + o.titulo + ": Año " + o.fecha.substring(6, 10));
            if (Integer.parseInt(fecha.substring(3, 5)) < Integer.parseInt(o.fecha.substring(3, 5))) {
                System.out.println("< Objeto " + titulo + ": Mes " + fecha.substring(3, 5) + " Objeto " + o.titulo + ": Mes " + o.fecha.substring(3, 5));
                if (Integer.parseInt(fecha.substring(0, 2)) < Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("< Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return 1;
                }
                if (Integer.parseInt(fecha.substring(0, 2)) > Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("> Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
            }
            if (Integer.parseInt(fecha.substring(3, 5)) > Integer.parseInt(o.fecha.substring(3, 5))) {
                System.out.println("> Objeto " + titulo + ": Mes " + fecha.substring(3, 5) + " Objeto " + o.titulo + ": Mes " + o.fecha.substring(3, 5));
                if (Integer.parseInt(fecha.substring(0, 2)) < Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("< Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return 1;
                }
                if (Integer.parseInt(fecha.substring(0, 2)) > Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("> Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
            }
        }
        else if (Long.parseLong(fecha.substring(6, 10)) > Long.parseLong(o.fecha.substring(6, 10))) {
            System.out.println("> Objeto " + titulo + ": Año " + fecha.substring(6, 10) + " Objeto " + o.titulo + ": Año " + o.fecha.substring(6, 10));
            if (Integer.parseInt(fecha.substring(3, 5)) < Integer.parseInt(o.fecha.substring(3, 5))) {
                System.out.println("< Objeto " + titulo + ": Mes " + fecha.substring(3, 5) + " Objeto " + o.titulo + ": Mes " + o.fecha.substring(3, 5));
                if (Integer.parseInt(fecha.substring(0, 2)) < Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("< Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
                if (Integer.parseInt(fecha.substring(0, 2)) > Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("> Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
            }
            if (Integer.parseInt(fecha.substring(3, 5)) > Integer.parseInt(o.fecha.substring(3, 5))) {
                System.out.println("> Objeto " + titulo + ": Mes " + fecha.substring(3, 5) + " Objeto " + o.titulo + ": Mes " + o.fecha.substring(3, 5));
                if (Integer.parseInt(fecha.substring(0, 2)) < Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("< Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
                if (Integer.parseInt(fecha.substring(0, 2)) > Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("> Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
            }
        }
        else{
            System.out.println("< Objeto " + titulo + ": Año " + fecha.substring(6, 10) + " Objeto " + o.titulo + ": Año " + o.fecha.substring(6, 10));
            if (Integer.parseInt(fecha.substring(3, 5)) < Integer.parseInt(o.fecha.substring(3, 5))) {
                System.out.println("< Objeto " + titulo + ": Mes " + fecha.substring(3, 5) + " Objeto " + o.titulo + ": Mes " + o.fecha.substring(3, 5));
                if (Integer.parseInt(fecha.substring(0, 2)) < Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("< Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return 1;
                }
                if (Integer.parseInt(fecha.substring(0, 2)) > Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("> Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
            }
            if (Integer.parseInt(fecha.substring(3, 5)) > Integer.parseInt(o.fecha.substring(3, 5))) {
                System.out.println("> Objeto " + titulo + ": Mes " + fecha.substring(3, 5) + " Objeto " + o.titulo + ": Mes " + o.fecha.substring(3, 5));
                if (Integer.parseInt(fecha.substring(0, 2)) < Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("< Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return -1;
                }
                if (Integer.parseInt(fecha.substring(0, 2)) > Integer.parseInt(o.fecha.substring(0, 2))) {
                    System.out.println("> Objeto " + titulo + ": Día " + fecha.substring(0, 2) + " Objeto " + o.titulo + ": Día " + o.fecha.substring(0, 2));
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public void coments(JScrollPane j, ArrayList<Post> comentarios){
        for (Post comentario : comentarios) {
            JTextArea cont = new JTextArea();
            JLabel aut = new JLabel();
            aut.setText(comentario.creador);
            cont.setText(comentario.contenido);
            j.setViewportView(aut);
            j.setViewportView(cont);
            
        }
    }
    
}
