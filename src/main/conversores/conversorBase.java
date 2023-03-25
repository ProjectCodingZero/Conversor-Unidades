package conversores;
import java.text.DecimalFormat;

public abstract class conversorBase {
   
   private String nombre;
   private double unidad;
   private int indice;
   public final void setNombre(String nombre){
      this.nombre = nombre;
   }
   public final String getNombre(){
      return this.nombre;
   }

   protected final void setUnidad(double unidad){
      this.unidad = unidad;
   }
   public final double getUnidad(){
      return this.unidad;
   }
   public final void setIndice(int indice){
      this.indice = indice;
   }
   public final int getIndice(){
      return this.indice;
   }
	public abstract void cambiarUnidad(String unidad);
   public abstract String convertirUnidad(conversorBase unidadHasta, double unidad);

   protected abstract void unidadEstablecida();
}
