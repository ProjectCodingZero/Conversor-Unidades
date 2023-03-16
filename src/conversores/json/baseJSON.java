package conversores.json;
import java.io.FileReader;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.net.*;

@JsonRootName("monedass")
public class baseJSON {
   @JsonProperty("nombre")
   private String nombre;

   @JsonProperty("unidad")
   private double unidad;
   public baseJSON(String nombre, double unidad){
      this.nombre = nombre;
      this.unidad = unidad;
   }

   public void setNombre(String nombre){
      this.nombre = nombre;
   }
   public String getNombre(){
      return this.nombre;
   }

   public void setUnidad(double unidad){
      this.unidad = unidad;
   }

   public double getUnidad(){
      return this.unidad;
   }

   
}