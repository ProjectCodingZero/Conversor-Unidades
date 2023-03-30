package main.conversores;

import java.util.Map;
import java.util.HashMap;
import main.json.*;
import java.text.DecimalFormat;

//Monedas basadas en dolar
public final class moneda extends conversorBase{
	public static HashMap<String, String> monedaRelacion = NombreAndAbreviatura();


	public moneda(){
		this("United States Dollar");
	}

	public moneda(String nombre){
		super.setNombre(monedaJSON.MapaMonedas.get(nombre).getNombre());
		this.unidadEstablecida();
	}

	public static String getMapaAbreviatura(String nombre){
		return monedaRelacion.get(nombre);
	}
	private static HashMap<String, String> NombreAndAbreviatura(){
		HashMap<String, String> mapaRelacion = new HashMap<>();
		for(Map.Entry<String, monedaClase> entry : monedaJSON.MapaMonedas.entrySet()){
			monedaClase moneda = entry.getValue();
			mapaRelacion.put(moneda.getNombre(), moneda.getAbreviatura());
		}
		return mapaRelacion;
	}
	
	@Override
	protected void unidadEstablecida(){
		monedaClase moneda = monedaJSON.MapaMonedas.get(this.getNombre());
		super.setUnidad(moneda.getUnidad());
	}
	@Override
	public String convertirUnidad(conversorBase unidadHasta, double unidad){
		double MonedaBase = 1 / unidadHasta.getUnidad();
		double unidadConvertida = unidad * MonedaBase * this.getUnidad();
		DecimalFormat df = new DecimalFormat("#.####");
		return df.format(unidadConvertida);
   }
	@Override
	public void cambiarUnidad(String nombre){
		super.setNombre(nombre);
		this.unidadEstablecida();
	}

}
