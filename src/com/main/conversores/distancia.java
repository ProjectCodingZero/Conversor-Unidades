package main.conversores;

import java.lang.Math;
import java.util.Arrays;
import java.util.HashMap;
import java.math.MathContext;
import java.math.BigDecimal;

public final class distancia extends conversorBase{
   //La unidad actual representa cual es la unidad que se esta usando
   private unidadDistancia unidadActual;
   //Unidad nombre completo con su anotacion

   //Hacer todo el enum unidadDistancia como un arreglo
   private static unidadDistancia[] arrayStringNombre;
   private static unidadDistancia.unidadAsociada[] arrayStringAbrevitura;
   public final static String[] unidadesArray;
   public final static HashMap<String, String> unidadMap;
   static{
      arrayStringNombre = unidadDistancia.values();
      arrayStringAbrevitura = unidadDistancia.unidadAsociada.values();
      unidadesArray = enumArreglado(arrayStringNombre);
      unidadMap = obtenerUnidadCompleta(arrayStringAbrevitura);
   }
   public distancia(){
      this("metros");
      this.unidadEstablecida();
   }
   public distancia(String unidad){
      this.unidadActual = unidadDistancia.EstablecerUnidad(unidad);
      setIndice(Arrays.asList(arrayStringNombre).indexOf(this.unidadActual));
      this.unidadEstablecida();
   }
   public static String getAbreviatura(String unidadDistancia){
      return unidadMap.get(unidadDistancia);    
   }

   
   //Enum para establecer las unidades y sus valores
   
   private enum unidadDistancia{
      yoctometro,
      zeptometro,
      attometro,
      femtometro,
      picometro,
      angstrom,
      nanometro,
      micrometro,  
      milimetros,
      centimetros,
      pulgadas,
      decimetros,
      pie,
      yarda,
      metros,
      decametros,
      hectometros,
      furlong,
      kilometros,
      milla,
      legua,
      miriametro,
      megametro,
      gigametro,
      astronomica,
      terametro,
      petametro,
      luz,
      parsec,
      exametro,
      zettametro,
      yottametro;

      private enum unidadAsociada{
         ym,
         zm,
         am,
         fm,
         pm,
         A,
         nm,
         µm,
         mm,
         cm,
         in,
         dm,
         ft,
         yard,
         m, 
         dam,
         hm,
         furlong,
         km,
         milla,
         legua,
         Mam,
         Mm,
         Gm,
         UA,
         Tm,
         Pm,
         ly,
         pc,
         Em,
         Zm,
         Ym;
      }
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
   private static String[] enumArreglado(unidadDistancia[] nombreUnidad){
      String[] unidadStrings = new String[nombreUnidad.length];
      for(int i = 0; i < unidadStrings.length; i++){
         unidadStrings[i] = nombreUnidad[i].name();
      }
      return unidadStrings;
   }
   private static HashMap<String, String> obtenerUnidadCompleta(unidadDistancia.unidadAsociada[] AbreviaturaUnidad){
      if(arrayStringNombre.length != AbreviaturaUnidad.length){
         EnumInigualesExcepcion excepcion  = new EnumInigualesExcepcion("Los enums no son iguales");
         throw excepcion;
      }
      HashMap<String, String> Maping = new HashMap<>();
      for(int i = 0; i < arrayStringNombre.length; i++){
         
         Maping.put(arrayStringNombre[i].name(), AbreviaturaUnidad[i].name());
      }
      return Maping;
   }

   @Override
   protected void unidadEstablecida(){
      super.setNombre(this.unidadActual.toString());
      switch(this.unidadActual){
         case yoctometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -24.0));
            break;
         case zeptometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -21.0));
            break;
         case attometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -18.0));
            break;
         case femtometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -15.0));
            break;
         case picometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -12.0));
            break;
         case angstrom:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -10.0));
            break;
         case nanometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -9.0));
            break;
         case micrometro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -6.0));
            break;
         case milimetros:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -3.0));
            break;
         case centimetros:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, -2.0));
            break;
         case pulgadas:
            super.setUnidad(unidadDistancia.m / 0.0254);
            break;
         case decimetros:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 1.0));
            break;
         case pie:
            super.setUnidad(unidadDistancia.m / 0.3048);
            break;
         case yarda:
            super.setUnidad(unidadDistancia.m / 0.9144);
            break;
         case decametros:
            super.setUnidad(unidadDistancia.m /  Math.pow(10.0, 1.0));
            break;
         case hectometros:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 2.0));
            break;
         case furlong:
            super.setUnidad(unidadDistancia.m / 201.168);
            break;
         case kilometros:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 3.0));
            break;
         case milla:
            super.setUnidad(unidadDistancia.m / 1.60934);
            break;
         case legua:
            super.setUnidad(unidadDistancia.m / 4.82803);
            break;
         case miriametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 4.0));
            break;
         case megametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 6.0));
            break;
         case gigametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 9.0));
            break;
         case astronomica:
            super.setUnidad(unidadDistancia.m / (1.495 * Math.pow(10.0, 11.0)));
            break;
         case terametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 12.0));
            break;
         case petametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 15.0));
            break;
         case luz:
            super.setUnidad(unidadDistancia.m / (9.46 * Math.pow(10.0, 15.0)));
            break;
         case parsec:
            super.setUnidad(unidadDistancia.m / (3.08 * Math.pow(10.0, 16.0)));
            break;
         case exametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 18.0));
            break;
         case zettametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 21.0));
            break;
         case yottametro:
            super.setUnidad(unidadDistancia.m / Math.pow(10.0, 24.0));
            break;

         default:
            super.setUnidad(unidadDistancia.m);
            break;
      }
   }

   @Override
   public String convertirUnidad(conversorBase unidadHasta, double unidad){
      BigDecimal UnidadConvertida;
      if(getIndice() > unidadHasta.getIndice()){
         UnidadConvertida = new BigDecimal(unidad * unidadHasta.getUnidad() * getUnidad(), MathContext.DECIMAL32);
      }
      else{
         UnidadConvertida = new BigDecimal(unidad * unidadHasta.getUnidad() / getUnidad(), MathContext.DECIMAL32); 
      }
      UnidadConvertida = UnidadConvertida.abs();
      System.out.println(UnidadConvertida);
      return "" + UnidadConvertida;
      }
   
   @Override
   public void cambiarUnidad(String unidad){
      this.unidadActual = unidadDistancia.EstablecerUnidad(unidad);
      this.unidadEstablecida();
   }

}
