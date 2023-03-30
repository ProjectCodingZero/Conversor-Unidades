package main.components;
import main.App;
import main.conversores.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.awt.event.*;


public final class distanciaFrame extends JFrame implements GUI, ActionListener{
   private static final long serialVersionUID = 160320230208L;
   private final ImageIcon meterIcon  = new ImageIcon("src\\com\\main\\resourses\\meterIcon.png");
   private JPanel distanciaPanel;
   private FlowLayout layoutManager = new FlowLayout(FlowLayout.LEFT, 25 , 50);
   //private JPanel card1, card2;
   //Si estas son true el boton para convertir unidades aparecera
   
   private JLabel labelUnidad1, labelUnidad2;
   private JComboBox<String> cbUnidades1, cbUnidades2;
   private JButton JBConvertir;
   private JTextField ConvertirBase;
   private JTextField ConvertirNuevo;
   private Color textLabelColor = new Color(98, 16, 205);
   private Rectangle labelSize = new Rectangle(20, 30, 50, 90);
   private Dimension TextFieldDimension = new Dimension(100, 30);
   /*
    * Las clases distancia para junto al String[]
    * Todos el enum como string
    */
   private String[] dUnidades = distancia.unidadesArray;
   private distancia distanciaPrimera;
   private distancia distanciaSegunda;
   
   public distanciaFrame(Point posicion, Dimension tamano){
      
      super(App.Conversores[0]);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      distanciaPanel = new JPanel(layoutManager);
      
      labelUnidad1 = new JLabel("");
      labelUnidad1.setBounds(labelSize);
      labelUnidad1.setForeground(textLabelColor);
      //Configurando los componentes

      //Configurando la primera parte de los componentes
      cbUnidades1 = new JComboBox<String>(dUnidades);
      cbUnidades1.setSelectedItem(null);
      cbUnidades1.setName("CBPrimero");
      cbUnidades1.addActionListener(this);
      ConvertirBase = new JTextField();
      AbstractDocument document = (AbstractDocument) ConvertirBase.getDocument();
      document.setDocumentFilter(new onlyNumbersFilter());
      ConvertirBase.setPreferredSize(TextFieldDimension);

      //Configurando la 2da parte de los componentes
      labelUnidad2 = new JLabel("");
      labelUnidad2.setBounds(labelSize);
      labelUnidad2.setForeground(textLabelColor);
      cbUnidades2 = new JComboBox<String>(dUnidades);
      ConvertirNuevo = new JTextField("Unidad");
      cbUnidades2.setSelectedItem(null);
      cbUnidades2.setName("CBSegundo");
      cbUnidades2.addActionListener(this);
      //Coloriando el label
      ConvertirNuevo.setEditable(false);
      ConvertirNuevo.setOpaque(true);
      ConvertirNuevo.setBackground(new Color(96, 96, 96));
      ConvertirNuevo.setForeground(new Color(124, 252, 0));
      ConvertirNuevo.setPreferredSize(TextFieldDimension);
      
      JBConvertir = new JButton("Convertir");
      JBConvertir.setVisible(false);
      JBConvertir.setPreferredSize(new Dimension(100, 30));
      JBConvertir.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            JBConvertir.setBackground(Color.YELLOW);
         }
         public void mouseExited(MouseEvent e) {
            JBConvertir.setBackground(UIManager.getColor("control"));
         }
      });
      
      JBConvertir.addActionListener(new  ActionListener() {
         public void actionPerformed(ActionEvent tocarBoton){
            String Numero = ConvertirBase.getText();
            try{
               double num = Double.parseDouble(Numero);
               String numeroObtenido = distanciaPrimera.convertirUnidad(distanciaSegunda, num);
               
               ConvertirNuevo.setText(numeroObtenido);
               JOptionPane.showMessageDialog(null, "El numero " + Numero + " convertido desde " + cbUnidades1.getSelectedItem()
               + "(" + labelUnidad1.getText() +") a " + cbUnidades2.getSelectedItem() +"(" + labelUnidad2.getText() + ") es igual a " + numeroObtenido);
               }
            catch(NumberFormatException numberExcepcion){
               numberExcepcion.getStackTrace();
               }
            }
      });

      //distanciaPanel.add(labelUnidad1);
      distanciaPanel.setLocation(posicion);
      distanciaPanel.setPreferredSize(tamano);
      cbUnidades1.setAlignmentX(30);
      cbUnidades1.setAlignmentY(50);
      distanciaPanel.add(cbUnidades1);
      distanciaPanel.add(cbUnidades2);
      layoutManager.setAlignment(FlowLayout.CENTER);
      distanciaPanel.add(labelUnidad1);
      distanciaPanel.add(ConvertirBase);
      distanciaPanel.add(labelUnidad2);
      distanciaPanel.add(ConvertirNuevo);

      layoutManager.setAlignment(FlowLayout.TRAILING);
      distanciaPanel.add(JBConvertir);
      
   }
   
   public void actionPerformed(java.awt.event.ActionEvent e) {
      
      JComboBox<String> selectedBox = (JComboBox<String>) e.getSource();
      String nombre = ((JComponent) e.getSource()).getName();
      String unidadSelecionada = (String) selectedBox.getSelectedItem();
      String abreviatura = distancia.getAbreviatura(unidadSelecionada);
      
      if(nombre.equals("CBPrimero")){
         distanciaPrimera = new distancia((String) selectedBox.getSelectedItem());
         labelUnidad1.setText(abreviatura);
      }else{
         distanciaSegunda = new distancia((String) selectedBox.getSelectedItem());
         labelUnidad2.setText(abreviatura);
      }
      JBConvertir.setVisible(cbUnidades1.getSelectedItem() != null && cbUnidades2.getSelectedItem() != null);
   }
   public void showGUI(){
      add(distanciaPanel);
      setVisible(true);
      setIconImage(meterIcon.getImage());
      pack();
   }
   public void menuAdding(){
      
   }
}
