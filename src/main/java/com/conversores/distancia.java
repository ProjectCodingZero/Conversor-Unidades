package main.java.com.conversores;

public class distancia extends conversorBase{
   private unidadDistancia unidadActual;

   public distancia(){
      this("metros");
   }
   public distancia(String unidad){
      this.unidadActual = unidadDistancia.EstablecerUnidad(unidad);
      this.unidadEstablecida();
   }

   private enum unidadDistancia{
      milimetros,
      centimetros,
      decimetros,
      metros,
      decametros,
      hectometros,
      kilometros;
      public static final double METROS = 1;
      private static final unidadDistancia EstablecerUnidad(String unidad){
         try{
            return unidadDistancia.valueOf(unidad);
         }
         
         catch(IllegalArgumentException noUnidad){
            return unidadDistancia.metros;
         }
      }
   }


   @Override
   protected void unidadEstablecida(){
      super.setNombre(this.unidadActual.toString());
      switch(this.unidadActual){
         case milimetros:
            super.setUnidad(unidadDistancia.METROS*1000);
            break;
         case centimetros:
            super.setUnidad(unidadDistancia.METROS*100);
            break;
         case decimetros:
            super.setUnidad(unidadDistancia.METROS*10);
            break;
         case decametros:
            super.setUnidad(unidadDistancia.METROS/10);
            break;
         case hectometros:
            super.setUnidad(unidadDistancia.METROS/100);
            break;
         case kilometros:
            super.setUnidad(unidadDistancia.METROS/1000);
            break;
         default:
            super.setUnidad(unidadDistancia.METROS);
         }
      }
   
   @Override
   public void cambiarUnidad(String unidad){
      this.unidadActual = unidadDistancia.EstablecerUnidad(unidad);
      this.unidadEstablecida();
   }

}
