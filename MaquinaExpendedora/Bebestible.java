

import java.math.BigDecimal;

public class Bebestible extends Items {

	public Bebestible (String nombre, BigDecimal precio) {
		super(nombre, precio);
	}

	@Override
	public String getLinea() {
		return "Disfrutalo!";
	}
	
}