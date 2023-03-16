
import components.distanciaFrame;
import components.mainframe;
import conversores.*;


import javax.swing.JFrame;

import java.awt.MouseInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.Cursor.*;
import java.awt.Point;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args){
        //GUI();

        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        mainframe main = new mainframe(mousePosition.x, mousePosition.y);
        main.setVisible(true);  
        main.pack();

        //distanciaFrame DFrame = new distanciaFrame();
        /*
        distancia metros2 = new distancia("milimetros");
        System.out.println(distancia.convertirUnidad(metros1, metros2, 153));
        System.out.println(distancia.convertirUnidad(metros2, metros1, 153));
        moneda moneda1 = new moneda("EUR");
        moneda moneda2= new moneda("USD");
        System.out.println(distancia.convertirUnidad(moneda1, moneda2, 153));   
        System.out.println(distancia.convertirUnidad(moneda2, moneda1, 153));
        */
        //distancia metros1 = new distancia("metros");
        
    }
    private static void GUI(){
        JFrame frame = new JFrame("Conversor");
        ImageIcon menuIcon = 
        new ImageIcon("src\\components\\images\\menuIcon.png");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(menuIcon.getImage());
        //Obtener la posicion del menu
        

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Conversores");
        
        MenuItem distanciaItem = new MenuItem("Distancia");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.add(distanciaItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        frame.setMenuBar(menuBar);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
