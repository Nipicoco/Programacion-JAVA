

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Registro {

	private Map<String, Integer> TotalVentas = new HashMap<>(); // hashmap almacena elementos en pares "key / Value" y puede acceder a ellos mediante un índice de otro tipo (por ejemplo, una String).
	private File mapeo = new File("productos.csv");
	private BigDecimal TotalVentasBrutas;
	
	public Registro() throws FileNotFoundException {
		
		Scanner leerArchivo = new Scanner(mapeo);
		
		TotalVentasBrutas = new BigDecimal("0.000");
		
		while(leerArchivo.hasNextLine()) {
			String lineaArchivo = leerArchivo.nextLine();
			String[] lineaArchivoArreglo = lineaArchivo.split("\\|");
			
			String nombreProductos = lineaArchivoArreglo[1];
			
			if (!TotalVentas.containsKey(nombreProductos)) {
				TotalVentas.put(nombreProductos, 0);
			}
		}
	}
	
	public Map<String, Integer> getTotalVentas() {
		return TotalVentas;
	}
	
	public int calcularVentas(String keyInput) {
		return TotalVentas.get(keyInput);
	}
	
	public void nuevaVenta(String keyInput, BigDecimal precio) {
		if (TotalVentas.containsKey(keyInput)) {
			int numerodeVentas = calcularVentas(keyInput);
			numerodeVentas++;
			TotalVentas.put(keyInput, numerodeVentas);
			TotalVentasBrutas = TotalVentasBrutas.add(precio);
			
		}
	}
	
	public BigDecimal getTotalVentasBrutas() {
		return TotalVentasBrutas;
	}
	
	public void printearRegistro(String horaVentas) {
		File TotalVentasConTiempo = new File("Hora de venta:" + (horaVentas.replace(" ", "_").replace(":", "_").replace("/", "_") +".txt"));
		
		try {
			FileWriter escritor = new FileWriter(TotalVentasConTiempo, true);
			PrintWriter printeador = new PrintWriter(escritor);
			
			for (String key : TotalVentas.keySet()) {
				printeador.println(key + "|" + calcularVentas(key) + "\r");
				printeador.flush();
			}
			
			printeador.println("**TOTAL VENTAS** $" + getTotalVentasBrutas());
			printeador.close();
			
		} catch (IOException error) {
			error.printStackTrace();
		}
		
	}

}
