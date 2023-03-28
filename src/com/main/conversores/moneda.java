package main.conversores;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.lang.Math;
import java.util.Arrays;
import java.util.HashMap;
import java.math.MathContext;
import java.math.BigDecimal;
import main.json.monedaClase;
import main.json.monedaJSON;

public final class moneda extends conversorBase{
	public final static String[] nombresArray;
	private abreviaturaDinero unidadActual;
	private abreviaturaDinero.nombreDinero nombreActual;
	private String abreviatura;
	private HashMap<String, Double> MapaUnidad;
	
	static{
		abreviaturaDinero.nombreDinero[] enumNombres = abreviaturaDinero.nombreDinero.values();
		nombresArray = enumArreglado(enumNombres);
		abreviaturaDinero[] enumAbreviatura = abreviaturaDinero.values();
	}

	public moneda(){
		this.unidadActual = abreviaturaDinero.EstablecerUnidad("USD");
		this.unidadEstablecida();
	}
	public moneda(String unidad){
		this.unidadActual = abreviaturaDinero.EstablecerUnidad(unidad);
		this.unidadEstablecida();
	}
	public HashMap<String, Double> getMapaUnidad(){
		return this.MapaUnidad;
	}
	private void setMapaUnidad(HashMap<String, Double> mapaUnidad){
		this.MapaUnidad = mapaUnidad;
	}

	private enum abreviaturaDinero{
		USD,
		ARG,
		EUR,
		MXN;
		private enum nombreDinero{
			United_States_Dollar,
			Argentine_Peso,
			Euro,
			Mexican_Peso;
		}
		public static final double DOLAR = 1;
		private static final abreviaturaDinero EstablecerUnidad(String unidad){
			try{
            return abreviaturaDinero.valueOf(unidad);
         }
         catch(IllegalArgumentException noUnidad){
            return abreviaturaDinero.USD;
         }
		}
		private static final nombreDinero setNombre(String nombre){
			String tempName = nombre.replace(" ", "_");
			try{
            return nombreDinero.valueOf(tempName);
         }
         catch(IllegalArgumentException noUnidad){
            return nombreDinero.United_States_Dollar;
         }
		}
	}

	private static String[] enumArreglado(abreviaturaDinero.nombreDinero[] nombres){
      String[] unidadStrings = new String[nombres.length];
      for(int i = 0; i < unidadStrings.length; i++){
         unidadStrings[i] = nombres[i].name();
      }
      return unidadStrings;
   }
	
	@Override
	protected void unidadEstablecida(){
		this.abreviatura = this.unidadActual.toString();
		super.setNombre(this.unidadActual.toString());
		DecimalFormat redondeo = new DecimalFormat("#.####");
		double unidad = monedaJSON.readSpecifyJsonDouble(getNombre());
		String redondeoString = redondeo.format(unidad);
	
		try {
			unidad = redondeo.parse(redondeoString).doubleValue();
		} catch (ParseException e) {
			unidad = monedaJSON.readSpecifyJsonDouble(getNombre());
		}
		setUnidad(unidad);
	}
	@Override
   public String convertirUnidad(conversorBase unidadHasta, double unidad){
      double MonedaBase = 1 / unidadHasta.getUnidad();
		double unidadConvertida = MonedaBase * unidadHasta.getUnidad();
      System.out.println(unidadConvertida);
      return "" + unidadConvertida;
      }
	@Override
	public void cambiarUnidad(String unidad){
		this.unidadActual = abreviaturaDinero.EstablecerUnidad(unidad);
		this.unidadEstablecida();
	}
	@Override
   public void setUnidad(double unidad){
      setUnidad(monedaJSON.readSpecifyJsonDouble(getNombre()));
   }

}
