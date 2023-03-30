/**
 * 
 */
/**
 * @author ProjectCodingZero
 *
 */
package main;

import java.awt.MouseInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import main.components.mainframe;


public class App {
    public static final String[] Conversores = {"Conversor Distancia", "Conversor Moneda"};
    public static final String[] Frames = {"mainFrame", "distanciaFrame"};
    
    
    public static void main(String[] args){
      try {
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      }catch (Exception ex) {
          ex.printStackTrace();
      }
      Point mousePosition = MouseInfo.getPointerInfo().getLocation();
      Dimension Tamano = new Dimension(260, 300);
      mainframe main = new mainframe(mousePosition, Tamano);
      main.menuAdding();
      main.showGUI();
    }
    /*
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
   */
}
