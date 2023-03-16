package conversores;
import java.text.DecimalFormat;

public class moneda extends conversorBase{
	unidadDeDinero unidadActual;
	public moneda(){
		this("USD");
	}
	public moneda(String unidad){
		this.unidadActual = unidadDeDinero.EstablecerUnidad(unidad);
		this.unidadEstablecida();
	}

	private enum unidadDeDinero{
		USD,
		ARG,
		EUR;
		public static final double DOLAR = 1;
		private static final unidadDeDinero EstablecerUnidad(String unidad){
			try{
            return unidadDeDinero.valueOf(unidad);
         }
         
         catch(IllegalArgumentException noUnidad){
            return unidadDeDinero.USD;
         }
		}
		
		
	}

	@Override
	protected void unidadEstablecida(){
		DecimalFormat redondeo = new DecimalFormat("#.###");
		super.setNombre(this.unidadActual.toString());
		switch(this.unidadActual){
			case ARG:
				//USD to ARG
				super.setUnidad(Double.valueOf(redondeo.format(unidadDeDinero.DOLAR/200.73)));
				break;
			case EUR:
				//USD to EUR
				super.setUnidad(Double.valueOf(redondeo.format(unidadDeDinero.DOLAR/0.94)));
				break;
			default:
				//Se base en el dolar
				super.setUnidad(unidadDeDinero.DOLAR);
		}
	}
	@Override
	public void cambiarUnidad(String unidad){
		this.unidadActual = unidadDeDinero.EstablecerUnidad(unidad);
		this.unidadEstablecida();
	}

}
