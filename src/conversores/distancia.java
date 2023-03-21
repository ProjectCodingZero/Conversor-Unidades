package conversores;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
public class distancia extends conversorBase{
   //La unidad actual representa cual es la unidad que se esta usando
   private unidadDistancia unidadActual;
   private final static unidadDistancia[] unidadEnum = unidadDistancia.values();
   

   //Hacer todo el enum unidadDistancia como un arreglo
   public final static String[] unidadesArray = enumArreglado();


   public distancia(){
      this("metros");
      this.unidadEstablecida();
   }
   public distancia(String unidad){
      this.unidadActual = unidadDistancia.EstablecerUnidad(unidad);
      setIndice(Arrays.asList(unidadEnum).indexOf(this.unidadActual));
      this.unidadEstablecida();
   }

   //Enum para establecer las unidades y sus valores
   private enum unidadDistancia{
      milimetros,
      centimetros,
      decimetros,
      metros,
      decametros,
      pulgadas,
      hectometros,
      kilometros;
      public static final double m = 1;

      
      private static final unidadDistancia EstablecerUnidad(String unidad){
         try{
            return unidadDistancia.valueOf(unidad);
         }
         
         catch(IllegalArgumentException noUnidad){
            return unidadDistancia.metros;
         }
      }
   }
   //Crea un string[] para convertir todo unidadEnum en un array de strings 
   private static String[] enumArreglado(){
      String[] unidadStrings = new String[unidadEnum.length];
      for(int i = 0; i < unidadStrings.length; i++){
         unidadStrings[i] = unidadEnum[i].name();
      }
      return unidadStrings;
   }

   @Override
   protected void unidadEstablecida(){
      super.setNombre(this.unidadActual.toString());
      switch(this.unidadActual){
         case milimetros:
            super.setUnidad(unidadDistancia.m*1000);
            break;
         case centimetros:
            super.setUnidad(unidadDistancia.m*100);
            break;
         case decimetros:
            super.setUnidad(unidadDistancia.m*10);
            break;
         case decametros:
            super.setUnidad(unidadDistancia.m/10);
            break;
         case pulgadas:
            super.setUnidad(unidadDistancia.m/ 0.0254);
            break;
         case hectometros:
            super.setUnidad(unidadDistancia.m/100);
            break;
         case kilometros:
            super.setUnidad(unidadDistancia.m/1000);
            break;
         default:
            super.setUnidad(unidadDistancia.m);
         }
      }
      @Override
      public double convertirUnidad(conversorBase unidadHasta, double unidad){
         System.out.println("unidad1 : "+ getUnidad() +
            "\nunidad2: " + unidadHasta.getUnidad());
         if(getIndice() > unidadHasta.getIndice()){
            return (unidad * (unidadHasta.getUnidad() / getUnidad()));
         }
         else{
            return (unidad * (unidadHasta.getUnidad() / getUnidad())); 
         }
         //TODO: darle un uso a este codigo
         //+ DecimalFormat redondeo = new DecimalFormat("#.###");
      }
   
   @Override
   public void cambiarUnidad(String unidad){
      this.unidadActual = unidadDistancia.EstablecerUnidad(unidad);
      this.unidadEstablecida();
   }

}
