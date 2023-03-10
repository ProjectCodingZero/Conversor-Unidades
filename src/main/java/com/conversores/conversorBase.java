package main.java.com.conversores;
import java.text.DecimalFormat;

public abstract class conversorBase {
   
   private String nombre;
   private double unidad;
   
   protected final void setNombre(String nombre){
      this.nombre = nombre;
   }
   protected final String getNombre(){
      return this.nombre;
   }

   protected final void setUnidad(double unidad){
      this.unidad = unidad;
   }
   protected final double getUnidad(){
      return this.unidad;
   }
   
	public abstract void cambiarUnidad(String unidad);
   public final static double convertirUnidad(conversorBase unidadDesde,conversorBase unidadHasta, double unidad){
      DecimalFormat redondeo = new DecimalFormat("#.###");
      if(unidadDesde.getUnidad() > unidadHasta.getUnidad()){
         return Double.valueOf(redondeo.format(unidad * 
         unidadDesde.getUnidad() * unidadHasta.getUnidad()));  
      }
      else{
         return Double.valueOf(redondeo.format(unidad * 
         unidadDesde.getUnidad() / unidadHasta.getUnidad()));  
      }
	}

   protected abstract void unidadEstablecida();
}
