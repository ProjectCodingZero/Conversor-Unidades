package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class mainframe extends JFrame {
   private static final long serialVersionUID = 160320230150L;
   private JPanel mainpanel;
   private JLabel label;
   private JButton buttonDistancia;
   private ImageIcon menuIcon = new ImageIcon("src\\components\\images\\menuIcon.png");

   public mainframe(int x, int y) {
      super("Menu");

      // Configurar la ventana principal
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      super.setIconImage(menuIcon.getImage());
      // Crear y configurar los componentes Swing
      mainpanel = new JPanel(new FlowLayout());
      
      label = new JLabel("Conversor!");

      buttonDistancia = new JButton("Conversor de distancia");



      // Agregar los componentes al mainpanel
      // Crear una lista de opciones
      /*String[] opcionesLista = {"Conversor Moneda", "Conversor Distancia"};
      opciones = new JComboBox<>(opcionesLista);
      */
      mainpanel.add(label);
      mainpanel.add(buttonDistancia, BorderLayout.CENTER);
      mainpanel.setPreferredSize(new Dimension(300, 250));

      
      //mainpanel.add(opciones);
      // Agregar el mainpanel a la ventana principal
      add(mainpanel);

      // Configurar el tamaño y la visibilidad de la ventana principal
      setLocation(x, y);
      //setLocationRelativeTo(null);

      buttonDistancia.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Point posicion = mainframe.super.getLocation();
            dispose();
            JOptionPane.showMessageDialog(null, "Ha ingresado al conversor de distancia");
            distanciaFrame DFrame = new distanciaFrame(posicion.x, posicion.y);
            DFrame.setVisible(true);
            
         }
      });
      pack();
      /*
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            label.setText("AAA!");
         }
      });*/
   }
}
