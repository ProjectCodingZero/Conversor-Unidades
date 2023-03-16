package components;
import conversores.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class distanciaFrame extends JFrame{
   private static final long serialVersionUID = 160320230208L;
   private JPanel distanciaPanel;
   private JLabel label;
   private ImageIcon moneyIcon  = new ImageIcon("src\\components\\images\\moneyIcon.png");
   private String[] dUnidades = 
   {"milimetros", "centimetros", "decimetros", "metros", "decametros", "hectometros", "kilometros"};
   private JComboBox cbUnidades;
   public distanciaFrame(int x, int y){
      
      super("Conversor de distancia");
      super.setIconImage(moneyIcon.getImage());
      
      distanciaPanel = new JPanel(new FlowLayout());

      label = new JLabel("Hehe");
      cbUnidades = new JComboBox<String>(dUnidades);
      cbUnidades.setSize(30, 50);

      cbUnidades.addFocusListener(new FocusListener() {
         public void focusGained(FocusEvent e) {
            System.out.println("El campo de texto ha ganado el enfoque");
        }

        public void focusLost(FocusEvent e) {
            System.out.println("El campo de texto ha perdido el enfoque");
        }
      });

      distanciaPanel.add(label);
      distanciaPanel.add(cbUnidades);
      distanciaPanel.setPreferredSize(new Dimension(300, 200));
      setLocation(x, y);
      
   }
}
