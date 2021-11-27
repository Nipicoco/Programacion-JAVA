

import java.math.BigDecimal;

public class Crujiente extends Items {

	public Crujiente(String nombre, BigDecimal precio) {
		super(nombre, precio);
	}

	@Override
	public String getLinea() {
		return "Disfrutalo!";
	}
	
}
