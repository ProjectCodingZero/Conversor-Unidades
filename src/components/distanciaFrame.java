package components;
import conversores.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.awt.event.*;


public class distanciaFrame extends JFrame implements GUI{
   private static final long serialVersionUID = 160320230208L;
   private ImageIcon moneyIcon  = new ImageIcon("src\\resourses\\metericon.png");
   private JPanel distanciaPanel;
   private JPanel card1, card2;
   

   
   private JLabel labelUnidad1, labelUnidad2;
   private JComboBox<String> cbUnidades1, cbUnidades2;
   private JButton JBConvertir;
   private JTextField ConvertirBase;
   private JTextField ConvertirNuevo;
   private ActionListener ListenerJCB;

   /*
    * Las clases distancia para junto al String[]
    * Todos el enum como string
    */
   private String[] dUnidades = distancia.unidadesArray;
   private distancia distanciaPrimera;
   private distancia distanciaSegunda;
   
   public distanciaFrame(Point posicion, Dimension tamano){
      
      super("Conversor de distancia");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setIconImage(moneyIcon.getImage());
      clases();
      
      
      //Agregando el action Listener
      ListenerJCB = new ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent e) {
            JComboBox<String> selectedBox = (JComboBox<String>) e.getSource();
            String nombre = ((JComponent) e.getSource()).getName();
            if(nombre.equals("CBPrimero")){
               distanciaPrimera.cambiarUnidad((String) selectedBox.getSelectedItem());
               System.out.println("Se ha seleccionado la opción: " + distanciaPrimera.getNombre());
            }else{
               distanciaSegunda.cambiarUnidad((String) selectedBox.getSelectedItem());
               System.out.println("Se ha seleccionado la opción: " + distanciaSegunda.getNombre());
            }
         }
   };

      distanciaPanel = new JPanel(new FlowLayout(5, 3 , 1));
      card1 = new JPanel();
      
      labelUnidad1 = new JLabel("Unidad Base:");
      //Configurando los componentes

      //Configurando la primera parte de los componentes
      cbUnidades1 = new JComboBox<String>(dUnidades);
      cbUnidades1.setSelectedItem(null);
      cbUnidades1.setName("CBPrimero");
      cbUnidades1.addActionListener(ListenerJCB);
      ConvertirBase = new JTextField();
      AbstractDocument document = (AbstractDocument) ConvertirBase.getDocument();
      document.setDocumentFilter(new filter());
      ConvertirBase.setPreferredSize(new Dimension(50, 20));

      //Configurando la 2da parte de los componentes
      card2 = new JPanel();
      labelUnidad2 = new JLabel("Unidad Nueva:");
      cbUnidades2 = new JComboBox<String>(dUnidades);
      ConvertirNuevo = new JTextField("Unidad");
      cbUnidades2.setSelectedItem(null);
      cbUnidades2.setName("CBSegundo");
      cbUnidades2.addActionListener(ListenerJCB);
      ConvertirNuevo.setEditable(false);
      ConvertirNuevo.setPreferredSize(new Dimension(50, 20));
      
      JBConvertir = new JButton("Convertir");
      JBConvertir.setPreferredSize(new Dimension(100, 20));
      
      JBConvertir.addActionListener(new  ActionListener() {
         public void actionPerformed(ActionEvent tocarBoton){
            String Numero = ConvertirBase.getText();
            try{
               double num = Double.parseDouble(Numero);
               String numeroObtenido = "" + distanciaPrimera.convertirUnidad(distanciaSegunda, num);
               ConvertirNuevo.setText(numeroObtenido);
            }
            catch(NumberFormatException numberExcepcion){
               numberExcepcion.getStackTrace();
            }
            finally{
               ConvertirBase.setText("");
            }
         }
      });
      

      //distanciaPanel.add(labelUnidad1);
      distanciaPanel.add(labelUnidad1, LEFT_ALIGNMENT);
      distanciaPanel.add(cbUnidades1, LEFT_ALIGNMENT);
      distanciaPanel.add(ConvertirBase, BOTTOM_ALIGNMENT);
      distanciaPanel.add(labelUnidad2, RIGHT_ALIGNMENT);
      distanciaPanel.add(cbUnidades2, RIGHT_ALIGNMENT);
      distanciaPanel.add(ConvertirNuevo, RIGHT_ALIGNMENT);
      distanciaPanel.add(JBConvertir, CENTER_ALIGNMENT);

      distanciaPanel.setPreferredSize(tamano);
      
      setLocation(posicion);
   }
   

   public void showGUI(){
      add(distanciaPanel);
      setVisible(true);
      pack();
   }
   private void clases(){
      distanciaPrimera = new distancia();
      distanciaSegunda = new distancia();
   }

   public void setUnidad(distancia Distancia, String unidad){
      Distancia.cambiarUnidad(unidad);
   }
}
