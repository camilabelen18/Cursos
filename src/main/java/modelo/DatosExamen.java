package modelo;

import java.util.ArrayList;
import java.util.List;

public class DatosExamen {
	
	  private List<DatosPregunta> datosPregunta = new ArrayList<>();
	  
	  public DatosExamen () { 
		  
	  }

	  public List<DatosPregunta> getDatosPregunta() {
	        return datosPregunta;
	  }

	  public void setDatosPregunta(ArrayList<DatosPregunta> datosPregunta) {
	        this.datosPregunta = datosPregunta;
	  }

	@Override
	public String toString() {
		return "DatosExamen [datosPregunta=" + datosPregunta + "]";
	}

	  
}
