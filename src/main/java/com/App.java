package main.java.com;
import main.java.com.gui.*;
import main.java.com.conversores.*;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args){
        //mainframe mainframe = new mainframe();
        distancia metros1 = new distancia("metros");
        distancia metros2 = new distancia("milimetros");
        System.out.println(distancia.convertirUnidad(metros1, metros2, 153));
        System.out.println(distancia.convertirUnidad(metros2, metros1, 153));
        moneda moneda1 = new moneda("EUR");
        moneda moneda2= new moneda("USD");
        System.out.println(distancia.convertirUnidad(moneda1, moneda2, 153));   
        System.out.println(distancia.convertirUnidad(moneda2, moneda1, 153));
    }
}
