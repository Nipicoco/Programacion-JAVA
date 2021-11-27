

import java.math.BigDecimal;

public class Chicle extends Items {

	public Chicle (String nombre, BigDecimal precio) {
		super(nombre, precio);
	}

	@Override
	public String getLinea() {
		return "Disfrutalo!";
	}
	
}