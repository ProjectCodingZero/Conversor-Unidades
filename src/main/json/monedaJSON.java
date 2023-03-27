package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.net.URI;
import java.io.FileReader;
//TODO: Borrar
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public final class monedaJSON{
	public final static Map<String, Double> MapaMoneda = readAllJson();
   private final static URI jsonFile = URI.create("moneda.json");
   public final static String filepath = jsonFile.getPath();

   //TODO: privatizar
   public static void protoName(ArrayList<monedaClase> monedas){

      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

      ObjectNode json = objectMapper.createObjectNode();
      // Agregar el objeto JSON "ejemplo"
      ObjectNode monedaJSONfile = objectMapper.createObjectNode();
      for(monedaClase moneda : monedas){
         monedaJSONfile.put("abreviatura", moneda.getAbreviatura());
         monedaJSONfile.put("unidad", moneda.getUnidad());
         json.set(""+ moneda.getNombre(), monedaJSONfile);
      }
      File file = new File(filepath);
      try(FileWriter fileWriter = new FileWriter(file);){
         writer.writeValue(fileWriter, json);
      }catch(IOException e){
         e.printStackTrace();
      }
      // Imprimir el objeto JSON
      //System.out.println(objectMapper.writeValueAsString(json));

      //Escribir el Json file
      
   }
   //TODO: hacer q sirva
   //TODO: ponerle un nombre mas leible
   public static void prototypename001() throws IOException{
      //leer el archivo json y hacerlo string
      byte[] jsondata = Files.readAllBytes(Paths.get(filepath));
      ObjectMapper objectMapper = new ObjectMapper();
      //convertir el string json a objeto
      ArrayList<monedaClase> monedas = objectMapper.readValue(jsondata, new TypeReference<ArrayList<monedaClase>>(){});
      for(monedaClase moneda: monedas){
         System.out.println(moneda.getNombre());
         System.out.println(moneda.getAbreviatura());
         System.out.println(moneda.getUnidad());
      }
      
      //Convertir el objeto a string en json
      monedaClase usd = crearMoneda("estado unidense","usd", 1);
      objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
      StringWriter stringEmp = new StringWriter();
		//objectMapper.writeValue(stringEmp, usd);
		//System.out.println("Moneda JSON is\n"+stringEmp);
      
   }
   //TODO: hacer q sirva
   //TODO: ponerle un nombre mas leible
   public static monedaClase crearMoneda(String nombre, String abreviatura, double unidad) {
		monedaClase mon = new monedaClase();
      mon.setAbreviatura(abreviatura);
		mon.setNombre(nombre);
		mon.setUnidad(unidad);
		return mon;
	}
   //TODO: hacer q sirva
   //TODO: ponerle un nombre mas leible
	public static void modificarJson(ArrayList<monedaClase> monedas) throws IOException{
      byte[] jsonData = Files.readAllBytes(Paths.get("moneda.json"));
      ArrayList<monedaClase> monedasJson = new ArrayList<>();
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(jsonData);
      Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
      while(fieldsIterator.hasNext()){
         
         Map.Entry<String, JsonNode> field = fieldsIterator.next();
         String nombre = field.getKey();
         String abreviatura = field.getValue().get("abreviatura").asText();
         Double unidad = field.getValue().get("unidad").asDouble();
         //Se crea una instancia de la moneda para 
         //crear el arraylist de las viejas monedas
         monedaClase moneda = new monedaClase(nombre, abreviatura, unidad);
         monedasJson.add(moneda);
      }
      //Crea un arraylist de las monedas que se van a cambiar
      ArrayList<monedaClase> cambiarMonedas = new ArrayList<>(monedasJson);
      cambiarMonedas.retainAll(monedas);
      monedasJson.removeAll(monedas);
      monedasJson.addAll(monedas);
      
      protoName(monedasJson);
	}
   //TODO: hacer q sirva
   //TODO: ponerle un nombre mas leible
   //Lee una key del json
   public static Map<String, Double> readSpecifyJson(String moneda){
      //Leer el archivo json
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Double> mapa = new HashMap<>();
      try{
         byte[] jsonData = Files.readAllBytes(Paths.get(".\\moneda.json"));
         JsonNode rootNode = objectMapper.readTree(jsonData);
         JsonNode nombreNode = rootNode.path(moneda);
         String nombre = nombreNode.get("nombre").asText();
         double unidad = nombreNode.get("unidad").asDouble();
         mapa.put(nombre, unidad);
      }catch(NullPointerException | IOException e){
         // manejar el caso en que no se encuentre el nodo con el nombre buscado
         throw new UnsupportedOperationException();
      }
      return mapa;
   }
   //TODO: hacer q sirva
   //TODO: poder hacer q esto no de null
   /* */
   public static Map<String, Double> readAllJson(){
      Map<String, Double> Map = new HashMap<>();

      File file  = new File(filepath);
      System.out.println("Existe: " + file.exists());
      System.out.println("Leer: " + file.canRead());
      if (!(file.exists() && file.canRead())) {
         System.out.println("EEE");
         return null;
      }

      try(FileReader fileReader = new FileReader(file)){
         ObjectMapper objectMapper = new ObjectMapper();
         byte[] jsonData = Files.readAllBytes(file.toPath());
         String content = new String(jsonData, StandardCharsets.UTF_8);
         System.out.println(content);
         JsonNode rootNode = objectMapper.readTree(jsonData);
         Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
         while(fieldsIterator.hasNext()){
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String abreviatura = field.getValue().get("abreviatura").asText();
            Double unidad = field.getValue().get("unidad").asDouble();
            Map.put(abreviatura, unidad);
         }
      }catch(IOException | NullPointerException e){
         e.printStackTrace();
         return null;
      }
      return Map;
   }



}

/*
 *    json persona = new json("Juan", 30);
 *    ObjectMapper mapper = new ObjectMapper();
 *    mapper.writeValue(new File("sadas.json"), persona);
 *    String json = mapper.writeValueAsString(persona);
 *    System.out.println(json);
 */