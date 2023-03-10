package main.java.com.json;
import java.io.FileReader;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonRootName("moneda")
public class json {
   @JsonProperty("nombre")
   private String nombre;

   @JsonProperty("valor")
   private double valor;
   public json(String nombre, double valor){
      this.nombre = nombre;
      this.valor = valor;
   }
}