package main.components;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.App;
public final class mainframe extends JFrame implements GUI{
   public static final long serialVersionUID = 160320230150L;
   private JPanel mainpanel;
   private JButton buttonAceptar;
   private JComboBox<String> JCBConversores;
   private ImageIcon menuIcon = new ImageIcon("src\\com\\main\\resourses\\menuIcon.png");
   
   public mainframe(Point posicion, Dimension tamano) {
      super("Menu");
      // Configurar la ventana principal
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setIconImage(menuIcon.getImage());
      // Crear y configurar los componentes Swing
      mainpanel = new JPanel(new GridLayout(3, 3, 1, 1));
      mainpanel.setBounds(posicion.x, posicion.y, tamano.width, tamano.height);
      JCBConversores = new JComboBox<String>(App.Conversores);
      JCBConversores.setSelectedItem(null);
      JCBConversores.setEditable(false);
      //JCBConversores.setBounds(200, 100, 60, 70);
      buttonAceptar = new JButton("Entrar");
      buttonAceptar.setBounds(100, 100, 60, 70);
      // Agregar los componentes al mainpanel
      // Crear una lista de opciones
      
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
            Point Newposicion = mainframe.super.getLocation();
            Dimension NewTamano = new Dimension(getSize());;
            
            switch(JCBConversores.getSelectedIndex()){
               case 0:
                  dispose();
                  JOptionPane.showMessageDialog(null, "Ha ingresado al " + App.Conversores[0], "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                  distanciaFrame DFrame = new distanciaFrame(Newposicion, NewTamano);
                  DFrame.menuAdding();
                  DFrame.showGUI();
                  break;
               case 1:
                  dispose();
                  JOptionPane.showMessageDialog(null, "Ha ingresado al " + App.Conversores[1], "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                  monedaFrame MFrame = new monedaFrame(Newposicion, NewTamano);
                  MFrame.menuAdding();
                  MFrame.showGUI();
                  break;
               default:
                  JOptionPane.showMessageDialog(null, "No se ha encontrado la opcion", "Error 404", JOptionPane.ERROR_MESSAGE);
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
