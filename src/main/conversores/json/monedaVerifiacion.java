package conversores.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import conversores.distancia;
import conversores.moneda;

import java.io.File;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonRootName("moneda")
public class monedaVerifiacion{
   
   public static void verificarArchivoJSON(){
      final File monedaJSON = new File("./moneda.json");
      //Verificar si el archivo existe
      if(monedaJSON.exists()){
         //Obtiene la ultima modificacion
         long ultimaModificacion = monedaJSON.lastModified();
         
         Date fechaDelArchivo = new Date(ultimaModificacion);
         System.out.println(fechaDelArchivo);
      }
      else{
         try{
            //Crear el archivo
            boolean resultado = monedaJSON.createNewFile();
            if(resultado){
               System.out.println("Creacion exitosa");
            }
            else{
               System.out.println("No se pudo crear el archivo");
            }
            
         }
         //Si no se pudo crear el archivo
         catch(IOException archivo){
            System.out.println("No se pudo crear el archivo");
         }
      }
      
      public class monedaJSON{
         @JsonProperty("nombre")
         private String nombre;
         @JsonProperty("unidad")
         private double unidad;

         public monedaJSON(){
         }

         public monedaJSON(String nombre, double unidad){
            this.nombre = nombre;
            this.unidad = unidad;
         }
         public String getNombre(){
            return this.nombre;
         }
         public void setNombre(String nombre){
            this.nombre = nombre;
         }

         
      }
   
   } 
}

/*
 *    json persona = new json("Juan", 30);
 *    ObjectMapper mapper = new ObjectMapper();
 *    mapper.writeValue(new File("sadas.json"), persona);
 *    String json = mapper.writeValueAsString(persona);
 *    System.out.println(json);
 */