package main.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Path;

//TODO: Borrar
import java.nio.charset.StandardCharsets;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.io.IOUtils;

import java.util.Arrays;
import main.App;
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class monedaJSON{
   public static HashMap<String, monedaClase> MapaMonedas;
   private final static String jsonName = "moneda.json";
   public static Path jsonPath;
   private static File jsonFile;

   static {
      Path jsonPath = Paths.get(System.getProperty("user.home"), "/json");
      if(!Files.exists(jsonPath)){
         try {
            Files.createDirectory(jsonPath);
         } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
         }
      }
      jsonPath = Paths.get(System.getProperty("user.home"), "/json" ,jsonName);
      jsonFile = jsonPath.toFile();
      if(!jsonFile.exists()){
         ArrayList<monedaClase> monedasBases = new ArrayList<>();
         monedasBases.add(new monedaClase("Argentine Peso", "ARG", 207.83));
         monedasBases.add(new monedaClase("United States Dollar", "USD", 1));
         monedaJSON.makeJsonFile(monedasBases);
      }
      MapaMonedas = getJsonAsMap();
   }
   
   public static void makeJsonFile(ArrayList<monedaClase> monedas){

      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

      ObjectNode json = objectMapper.createObjectNode();
      // Agregar el objeto JSON "ejemplo"
      ObjectNode monedasJSON = objectMapper.createObjectNode();
      for(monedaClase moneda : monedas){
         monedasJSON.put("abreviatura", moneda.getAbreviatura());
         monedasJSON.put("unidad", moneda.getUnidad());
         json.set(moneda.getNombre(), monedasJSON);
      }
      try(FileWriter fileWriter = new FileWriter(jsonFile)){
         writer.writeValue(fileWriter, json);
      }catch(IOException e){
         e.printStackTrace();
      }
      // Imprimir el objeto JSON
      //System.out.println(objectMapper.writeValueAsString(json));

      //Escribir el Json file
   }
   public static void makeJsonFile(HashMap<String, monedaClase> monedas){
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

      ObjectNode json = objectMapper.createObjectNode();
      // Agregar el objeto JSON "ejemplo"
      ObjectNode monedasJSON = objectMapper.createObjectNode();
      for(Map.Entry<String, monedaClase> entry : monedas.entrySet()){
         monedaClase moneda = entry.getValue();
         monedasJSON.put("abreviatura", moneda.getAbreviatura());
         monedasJSON.put("unidad", moneda.getUnidad());
         json.set(moneda.getNombre(), monedasJSON);
      }
      try(FileWriter fileWriter = new FileWriter(jsonFile)){
         writer.writeValue(fileWriter, json);
      }catch(IOException e){
         e.printStackTrace();
      }
   }
	public static void modifyJson(ArrayList<monedaClase> monedas){
      MapaMonedas = getJsonAsMap();
      ArrayList<monedaClase> monedaArray = null;
      try{
         monedaArray = new ArrayList<>(MapaMonedas.values());
      }catch(NullPointerException noMapa){
         makeJsonFile(monedas);
      }

      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode json = objectMapper.createObjectNode();
      // Agregar las monedas al objeto JSON existente
      for (monedaClase moneda : monedaArray) {
         ObjectNode monedaJSONfile = objectMapper.createObjectNode();
         monedaJSONfile.put("abreviatura", moneda.getAbreviatura());
         monedaJSONfile.put("unidad", moneda.getUnidad());
         json.set(moneda.getNombre(), monedaJSONfile);
      }
      for (monedaClase moneda : monedas) {
         ObjectNode monedaJSONfile = objectMapper.createObjectNode();
         monedaJSONfile.put("abreviatura", moneda.getAbreviatura());
         monedaJSONfile.put("unidad", moneda.getUnidad());
         json.set(moneda.getNombre(), monedaJSONfile);
      }
      makeJsonFileFromNode(json);
   }

   private static void makeJsonFileFromNode(ObjectNode json){
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
      try(FileWriter fileWriter = new FileWriter(jsonFile);){
         writer.writeValue(fileWriter, json);
      }catch(IOException e){
         e.printStackTrace();
      }
   }

   //Lee una key:value del json
   //Abreviatura(String) : valor(double)
   public static Map<String, monedaClase> getAsJsonMap(String nombre){
      //Leer el archivo json
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, monedaClase> mapaJson = new HashMap<>();
      try(InputStream inputStream = new FileInputStream(jsonFile)){
         
         byte[] jsonData = IOUtils.toByteArray(inputStream);
         JsonNode rootNode = objectMapper.readTree(jsonData);
         Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
         while(fieldsIterator.hasNext()){
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String nombreJSON = field.getKey();
            String abreviaturaJSON = field.getValue().get("abreviatura").asText();
            Double unidadJSON = field.getValue().get("unidad").asDouble();
            monedaClase moneda = new monedaClase(nombreJSON, abreviaturaJSON, unidadJSON);
            mapaJson.put(nombreJSON, moneda);
         }
      }catch(NullPointerException | IOException e){
         // manejar el caso en que no se encuentre el nodo con el nombre buscado
         throw new UnsupportedOperationException();
      }
      return mapaJson;
   }
   public static double readSpecifyJsonDouble(String nombre){
      ObjectMapper objectMapper = new ObjectMapper();
      try(InputStream inputStream = new FileInputStream(jsonFile)){
         byte[] jsonData = IOUtils.toByteArray(inputStream);
         JsonNode rootNode = objectMapper.readTree(jsonData);
         JsonNode nombreNode = rootNode.path(nombre);

         double unidad = nombreNode.get("unidad").asDouble();
         return unidad;
      }catch(NullPointerException | IOException e){
         // manejar el caso en que no se encuentre el nodo con el nombre buscado
         throw new UnsupportedOperationException();
      }
   }

   //TODO: hacer q sirva
   //TODO: poder hacer q esto no de null
   public static HashMap<String, monedaClase> getJsonAsMap(){
      HashMap<String, monedaClase> monedaMap = new HashMap<>();
      try(InputStream inputStream = new FileInputStream(jsonFile)){
         ObjectMapper objectMapper = new ObjectMapper();
         byte[] jsonData = IOUtils.toByteArray(inputStream);
         //Imprime el contenido
         //String content = new String(jsonData, StandardCharsets.UTF_8);
         JsonNode rootNode = objectMapper.readTree(jsonData);
         Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
         while(fieldsIterator.hasNext()){
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String nombre = field.getKey();
            String abreviatura = field.getValue().get("abreviatura").asText();
            Double unidad = field.getValue().get("unidad").asDouble();
            monedaClase moneda = new monedaClase(nombre, abreviatura, unidad);
            monedaMap.put(nombre, moneda);
         }
      }catch(IOException | NullPointerException e){
         return null;
      }
      return monedaMap;
   }
}
