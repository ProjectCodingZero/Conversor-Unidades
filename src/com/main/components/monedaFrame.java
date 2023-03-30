package main.components;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import main.App;
import main.conversores.moneda;

public class monedaFrame extends JFrame implements GUI, ActionListener{
   private final ImageIcon moneyIcon = new ImageIcon("src\\com\\main\\resourses\\moneyIcon.png");
   private JPanel monedaPanel;
   private FlowLayout layoutManager = new FlowLayout(FlowLayout.LEFT, 25 , 50);

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
   
   private moneda monedaPrimera;
   private moneda monedaSegunda;

   private static String[] monedaUnidades;
   static{
      Set<String> keys = moneda.monedaRelacion.keySet();
      monedaUnidades = new String[keys.size()];
      int  i = 0;
      for(String key: keys){
         monedaUnidades[i] = key;
         i++;
      }
   }

   public monedaFrame(Point posicion, Dimension tamano){
      super(App.Conversores[1]);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      monedaPanel = new JPanel(layoutManager);

      labelUnidad1 = new JLabel("");
      labelUnidad1.setBounds(labelSize);
      labelUnidad1.setForeground(textLabelColor);
      //Configurando los componentes

      //Configurando la primera parte de los componentes
      cbUnidades1 = new JComboBox<String>(monedaUnidades);
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
      cbUnidades2 = new JComboBox<String>(monedaUnidades);
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
               String numeroObtenido = monedaPrimera.convertirUnidad(monedaSegunda, num);
               
               ConvertirNuevo.setText(numeroObtenido);
               JOptionPane.showMessageDialog(null, "El numero " + Numero + " convertido desde " + cbUnidades1.getSelectedItem()
               + "(" + labelUnidad1.getText() +") a " + cbUnidades2.getSelectedItem() +"(" + labelUnidad2.getText() + ") es igual a " + numeroObtenido);
               }
            catch(NumberFormatException numberExcepcion){
               numberExcepcion.getStackTrace();
               }
            }
      });
      monedaPanel.setPreferredSize(tamano);
      monedaPanel.setLocation(posicion);
      cbUnidades1.setAlignmentX(30);
      cbUnidades1.setAlignmentY(50);
      monedaPanel.add(cbUnidades1);
      monedaPanel.add(cbUnidades2);
      layoutManager.setAlignment(FlowLayout.CENTER);
      monedaPanel.add(labelUnidad1);
      monedaPanel.add(ConvertirBase);
      monedaPanel.add(labelUnidad2);
      monedaPanel.add(ConvertirNuevo);

      layoutManager.setAlignment(FlowLayout.TRAILING);
      monedaPanel.add(JBConvertir);

   }
   
   public void actionPerformed(java.awt.event.ActionEvent e) {
      
      JComboBox<String> selectedBox = (JComboBox<String>) e.getSource();
      String nombre = ((JComponent) e.getSource()).getName();
      String unidadSelecionada = (String) selectedBox.getSelectedItem();
      String abreviatura = moneda.getMapaAbreviatura(unidadSelecionada);
      
      if(nombre.equals("CBPrimero")){
         monedaPrimera = new moneda((String) selectedBox.getSelectedItem()); 
         labelUnidad1.setText(abreviatura);
      }else{
         monedaSegunda = new moneda((String) selectedBox.getSelectedItem()); 
         labelUnidad2.setText(abreviatura);
      }
      JBConvertir.setVisible(cbUnidades1.getSelectedItem() != null && cbUnidades2.getSelectedItem() != null);
   }

   public void showGUI(){
      add(monedaPanel);
      setVisible(true);
      setIconImage(moneyIcon.getImage());
      pack();
   }
   public void menuAdding(){

   }
}
