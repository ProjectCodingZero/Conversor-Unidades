import components.mainframe;


import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.MouseInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.Cursor.*;
import java.util.Collection;
import java.awt.Point;
import java.util.*;


public class App {
    public static String[] Frames = {"mainFrame", "distanciaFrame"};
    public static String Frame;
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        Dimension Tamano = new Dimension(260, 300);
        mainframe main = new mainframe(mousePosition, Tamano);
        main.menuAdding();
        main.showGUI();
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
    private static void menuAdding(JFrame frame){  

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
