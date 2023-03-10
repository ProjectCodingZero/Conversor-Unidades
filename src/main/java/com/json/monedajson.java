package main.java.com.json;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper; 


public class monedaJSON{
   
   public static void main(String[] args) throws Exception {
      
      json persona = new json("Juan", 30);
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(new File("sadas.json"), persona);
      String json = mapper.writeValueAsString(persona);
      System.out.println(json);
      
   }
}
