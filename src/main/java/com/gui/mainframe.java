package main.java.com.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class mainframe extends JFrame implements ActionListener {
   private JPanel mainpanel;
   private JLabel label;
   private JButton buttonMain;
   private JComboBox<String> opciones;

   public mainframe() {
      
      // Configurar la ventana principal
      super("Conversor");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Crear y configurar los componentes Swing
      mainpanel = new JPanel(new FlowLayout());
      label = new JLabel("Conversor!");

      buttonMain = new JButton("Continuar");


      buttonMain.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            JOptionPane.showMessageDialog(null, "");
         }
      });
      // Agregar los componentes al mainpanel
      // Crear una lista de opciones
      /*String[] opcionesLista = {"Conversor Moneda", "Conversor Distancia"};
      opciones = new JComboBox<>(opcionesLista);
      */
      mainpanel.add(label);
      mainpanel.add(buttonMain, BorderLayout.CENTER);
      mainpanel.setPreferredSize(new Dimension(300, 250));

      
      //mainpanel.add(opciones);
      // Agregar el mainpanel a la ventana principal
      add(mainpanel);

      // Configurar el tamaño y la visibilidad de la ventana principal
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
      /*
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            label.setText("AAA!");
         }
      });*/
   }
}
