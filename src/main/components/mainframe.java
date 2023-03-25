package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class mainframe extends JFrame implements GUI{
   public static final long serialVersionUID = 160320230150L;
   private JPanel mainpanel;
   private JLabel label;
   private JButton buttonAceptar;
   private JComboBox<String> JCBConversores;
   private String[] Conversores = {"Conversor Distancia"};
   private ImageIcon menuIcon = new ImageIcon("src\\resourses\\menuIcon.png");
   
   public mainframe(Point posicion, Dimension tamano) {
      super("Menu");
      // Configurar la ventana principal
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setIconImage(menuIcon.getImage());
      // Crear y configurar los componentes Swing
      mainpanel = new JPanel(new GridLayout(3, 3, 1, 1));
      mainpanel.setBounds(posicion.x, posicion.y, tamano.width, tamano.height);
      JCBConversores = new JComboBox<String>(Conversores);
      JCBConversores.setSelectedItem(null);
      JCBConversores.setEditable(false);
      //JCBConversores.setBounds(200, 100, 60, 70);
      buttonAceptar = new JButton("Entrar");
      buttonAceptar.setBounds(100, 100, 60, 70);
      // Agregar los componentes al mainpanel
      // Crear una lista de opciones
      /*String[] opcionesLista = {"Conversor Moneda", "Conversor Distancia"};
      opciones = new JComboBox<>(opcionesLista);
      */
      // Configurar el tamaño y la visibilidad de la ventana principal
      
      mainpanel.add(buttonAceptar, 0, 0);
      mainpanel.add(JCBConversores, 1, 0);
      mainpanel.setPreferredSize(tamano);
      setLocation(posicion);

      
      //mainpanel.add(opciones);
      // Agregar el mainpanel a la ventana principal
      add(mainpanel);
      buttonAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            switch(JCBConversores.getSelectedIndex()){
               case 0:
                  Point Newposicion = mainframe.super.getLocation();
                  Dimension NewTamano = new Dimension(getSize());;
                  dispose();
                  JOptionPane.showMessageDialog(null, "Ha ingresado al conversor de distancia");
                  distanciaFrame DFrame = new distanciaFrame(Newposicion, NewTamano);
                  DFrame.menuAdding();
                  DFrame.showGUI();
                  break;
               default:
                  break;
            }
      
         }
      });
      /*
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            label.setText("AAA!");
         }
      });*/
   }

   public void showGUI(){
      setVisible(Visible);
      pack();
   }
   public void menuAdding(){
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
      setMenuBar(menuBar);
   }
   
}
