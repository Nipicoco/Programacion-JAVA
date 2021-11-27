

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Inventario {
	private Map<String, Stack<Items>> contenido = new HashMap<>();
	private File file = new File("productos.csv");
	
	public Inventario() throws FileNotFoundException {
		
		Scanner leerArchivo = new Scanner(file);
		
		while(leerArchivo.hasNextLine()) {
			String lineaArchivo = leerArchivo.nextLine();
			String[] lineaArchivoArreglo = lineaArchivo.split("\\|");
			
			String code = lineaArchivoArreglo[0];
			String nombre = lineaArchivoArreglo[1];
			BigDecimal precio = new BigDecimal(lineaArchivoArreglo[2]);
			String type = lineaArchivoArreglo[3];
			
			if (contenido.containsKey(code) == false) {
				Stack<Items> stack = new Stack<>();
				contenido.put(code, stack);
			}
			
			Items nuevoItems;
			
			if (type.equals("Crujiente")) {nuevoItems = new Crujiente(nombre, precio);}
			else if (type.equals("Dulce")) {nuevoItems = new Dulce(nombre, precio);}
			else if (type.equals("Chicle")) {nuevoItems = new Chicle(nombre, precio);}
			else if (type.equals("Bebestible")) {nuevoItems = new Bebestible(nombre, precio);}
			else nuevoItems = null;
		
			
			
			if (nuevoItems != null) {
				for (int i = 0; i < 5; i++) {contenido.get(code).push(nuevoItems);}
			}
				
		}
		
	}

	public Map<String, Stack<Items>> getContenido() {
		return contenido;
	}
	
	public int getTamano(String code) {
		int tamano = contenido.get(code).size();
		return tamano;
	}
	
	public Items eliminarItem(String code) {
		try {
			Items returnItems = contenido.get(code).pop();
			return returnItems;
		} catch (EmptyStackException error) {
			return null;
		} 
	}
	
	public void printearProductos() throws FileNotFoundException {
		Scanner leerArchivo = new Scanner(file);
				
			while(leerArchivo.hasNextLine()) {
				String lineaArchivo = leerArchivo.nextLine();
				String[] lineaArchivoArreglo = lineaArchivo.split("\\|");
				
				String code = lineaArchivoArreglo[0];
				
				if (contenido.get(code).size() == 0) {
					System.out.println(lineaArchivo + " Cantidad: " + "AGOTADO");
				}
				else {
					System.out.println(lineaArchivo + " Cantidad: " + contenido.get(code).size());
				}

				
			}
		
		
		
	}

}

