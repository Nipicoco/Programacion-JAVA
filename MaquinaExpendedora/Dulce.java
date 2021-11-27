

import java.math.BigDecimal;

public class Dulce extends Items {

	public Dulce(String nombre, BigDecimal precio) {
		super(nombre, precio);
	}

	@Override
	public String getLinea() {
		return "Dsifrutalo!";
	}
	
}