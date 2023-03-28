package main.conversores;

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

   public void setUnidad(double unidad){
      this.unidad = unidad;
   }
   public double getUnidad(){
      return this.unidad;
   }

   //TODO borrar
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
