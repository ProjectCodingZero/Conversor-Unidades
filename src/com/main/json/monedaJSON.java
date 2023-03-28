package main.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.net.URL;

//TODO: Borrar
import java.nio.charset.StandardCharsets;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.io.IOUtils;


import java.util.Arrays;
import main.App;
import main.conversores.moneda;
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class monedaJSON{
   public final static ArrayList<monedaClase> ArrayMoneda;
   /*
   private final static String jsonName = "./moneda.json";
   private final static URI jsonURI = Paths.get(jsonName).toUri();
   public final static String filepath = jsonURI.getPath();
   private final static File jsonFile = new File(filepath);
   private final static Path jsonPath = jsonFile.toPath();
   */
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
      ArrayMoneda = getJsonAsArray();
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
         App.LOGGER.info("Se pudo crear el archivo");
         writer.writeValue(fileWriter, json);
      }catch(IOException e){
         App.LOGGER.severe("No se pudo hacer el archivo");
         e.printStackTrace();
      }
      // Imprimir el objeto JSON
      //System.out.println(objectMapper.writeValueAsString(json));

      //Escribir el Json file
   }
	public static void modifyJson(ArrayList<monedaClase> monedas){
      ArrayList<monedaClase> monedaJson = getJsonAsArray();
      if(monedaJson == null){
         makeJsonFile(monedas);
      }
      else{
         ObjectMapper objectMapper = new ObjectMapper();
         ObjectNode json = objectMapper.createObjectNode();
         // Agregar las monedas al objeto JSON existente
         for (monedaClase moneda : monedaJson) {
            ObjectNode monedaJSONfile = objectMapper.createObjectNode();
            monedaJSONfile.put("abreviatura", moneda.getAbreviatura());
            monedaJSONfile.put("unidad", moneda.getUnidad());
            json.set("" + moneda.getNombre(), monedaJSONfile);
         }
         for (monedaClase moneda : monedas) {
            ObjectNode monedaJSONfile = objectMapper.createObjectNode();
            monedaJSONfile.put("abreviatura", moneda.getAbreviatura());
            monedaJSONfile.put("unidad", moneda.getUnidad());
            json.set("" + moneda.getNombre(), monedaJSONfile);
         }
         makeJsonFileFromNode(json);
      }

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
   public static Map<String, Double> getAsJsonMap(String nombre){
      //Leer el archivo json
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Double> mapaJson = new HashMap<>();
      try(InputStream inputStream = new FileInputStream(jsonFile)){
         
         byte[] jsonData = IOUtils.toByteArray(inputStream);
         JsonNode rootNode = objectMapper.readTree(jsonData);
         Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
         while(fieldsIterator.hasNext()){
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String abreviatura = field.getValue().get("abreviatura").asText();
            Double unidad = field.getValue().get("unidad").asDouble();
            mapaJson.put(abreviatura, unidad);
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
   public static ArrayList<monedaClase> getJsonAsArray(){
      ArrayList<monedaClase> monedasArray = new ArrayList<>();
      App.LOGGER.info("getJsonAsArray entrando");
      try(InputStream inputStream = new FileInputStream(jsonFile)){
         App.LOGGER.info("en el try");
         App.LOGGER.info("InputStream " + inputStream);
         ObjectMapper objectMapper = new ObjectMapper();
         byte[] jsonData = IOUtils.toByteArray(inputStream);
         App.LOGGER.info("jsondata  " + jsonData);
         //Imprime el contenido
         //String content = new String(jsonData, StandardCharsets.UTF_8);
         JsonNode rootNode = objectMapper.readTree(jsonData);
         Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
         while(fieldsIterator.hasNext()){
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String nombre = field.getKey();
            String abreviatura = field.getValue().get("abreviatura").asText();
            Double unidad = field.getValue().get("unidad").asDouble();
            monedaClase monedas = new monedaClase(nombre, abreviatura, unidad);
            monedasArray.add(monedas);
         }
      }catch(IOException | NullPointerException e){
         App.LOGGER.severe("se atrapo el error " + Arrays.toString(e.getStackTrace()));
         return null;
      }
      return monedasArray;
   }
}
