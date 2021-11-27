

import java.math.BigDecimal;

public abstract class Items {

	private String nombre;
	private BigDecimal precio;
	
	//constructor
	public Items(String nombre, BigDecimal precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public abstract String getLinea();
	
	public String getNombre() {
		return nombre;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	
}
