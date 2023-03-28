
package main.json;

import main.conversores.moneda;

public class monedaClase {
   private String nombre;
   private String abreviatura;
   private double unidad;
   //Constructor por defecto
   public monedaClase(){}
   //Contructor para que monedaJson.java no de error
   public monedaClase(String nombre, String abreviatura, double unidad){
      setNombre(nombre);
      setAbreviatura(abreviatura);
      setUnidad(unidad);
   }

   public String getNombre(){
      return this.nombre;
   }
   public void setNombre(String nombre){
      String capitalized = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
      String[] words = capitalized.split(" ");
      String finalStr = "";
      for (String word : words) {
         finalStr += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
      }
      finalStr = finalStr.trim();
      this.nombre = finalStr;
   }

   public double getUnidad(){
      return unidad;
   }
   public void setUnidad(double unidad){
      this.unidad = unidad;
   }

   public String getAbreviatura(){
      return this.abreviatura;
   }
   public void setAbreviatura(String abreviatura){
      this.abreviatura = abreviatura.substring(0, 3).toUpperCase();
   }
   @Override
	public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append(this.nombre + " {\n");
      sb.append("abreviatura: " + this.abreviatura + "\n");
      sb.append("unidad: " + this.unidad + "\n");
      sb.append("}\n");
      return sb.toString();
   }
   @Override 
   public boolean equals(Object obj) { 
      if(obj == this){
         return true;
      }
      if (!(obj instanceof monedaClase)) {
         return false;
      }
      monedaClase other = (monedaClase) obj;
      return this.abreviatura.equals(other.getAbreviatura()) && this.nombre.equals(other.getNombre());
   }
}