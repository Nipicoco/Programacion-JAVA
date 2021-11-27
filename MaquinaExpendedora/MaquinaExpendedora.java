
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/*Se pidio  a una empresa el desarrollo de una maquina expendedora con variadas funciones tales como
AGREGAR DINERO
AGREGAR PRODUCTOS(NO TERMINADO)
HACER UN LOG DE LOS PRODUCTOS VENDIDOS EN UN TEXTO
ALMACENAR PRODUCTOS DENTRO DE UN CSV
USAR UN HASHMAP PARA MAPEAR EL TOTAL DE VENTAS*/

public class MaquinaExpendedora {

	private static Inventario inventario;
	private static Registro registroVentaas;
	private static BigDecimal balance = new BigDecimal("0.000");
	private static Scanner keyboardInput = new Scanner(System.in);
	private static File log = new File("REGISTRO.txt");
	private static FileWriter escritor;
	private static PrintWriter printeador;
	
	
	public static void main(String[] args) throws IOException {
		
		
		inventario = new Inventario();
		registroVentaas = new Registro();
		escritor = new FileWriter(log,true);
		printeador = new PrintWriter(escritor);
		printeador.println("\r" + ">" + tiempo() + " INICIANDO MAQUINA");
		printeador.flush();
		

		
		bienvenido();
		
		menuInicio();
		
		
	
	}
	
	
	
	
	
	
	
	
	public static void bienvenido() {
		System.out.println("*****************************************");
		System.out.println("  MAQUINA EXPENDEDORA DE NICOLAS VERGARA ");
		System.out.println("*****************************************");
		System.out.println();
	}
	
	
	public static void menuInicio() {
		
		System.out.println("> (1) Mostrar productos disponibles");
		System.out.println("> (2) Comprar");
		System.out.println("> (3) Salir");
		

		
		String eleccion = keyboardInput.nextLine();
		
		if (eleccion.equals("1")) {
			try {
				inventario.printearProductos();
			} catch (FileNotFoundException error) {
				error.printStackTrace();  
			}
			System.out.println();
			menuInicio();
			
		}
		else if (eleccion.equals("2")) {
			menuCompra();
			
		}
		else if (eleccion.equals("3")) {
			gracias();
		}
		
		else {
			System.out.println("ELECCION INVALIDA");
			menuInicio();
		}
	}
	
	
	public static void menuCompra() {
		
		System.out.println("> (1) Ingresar Dinero");
		System.out.println("> (2) Seleccionar Productos");
		System.out.println("> (3) Finalizar Compras");
		System.out.println();
		System.out.println("Dinero ingresado: $" + balance);
		
		String eleccionCompra = keyboardInput.nextLine();
		
		if (eleccionCompra.equals("1")) {
			ingresarDinero();
		}
		else if (eleccionCompra.equals("2")) {
			comprarItem();
		}
		else if (eleccionCompra.equals("3")) {
			finalizar();

		}
		else {
			System.out.println("Eleccion Invalida!");
			menuCompra();
		}
		
		
	}
	
	public static void ingresarDinero() {

		System.out.println("Balance actual $" + balance);
		System.out.println();
		
		System.out.println("> (1) Insertar $1 mil pesos");
		System.out.println("> (2) Insert $2 mil pesos");
		System.out.println("> (5) Insert $5 mil pesos");
		System.out.println("> (10) Insert $10 mil pesos");
		System.out.println("> (X) Terminar Ingreso.");
		
		String dinero = keyboardInput.nextLine();
		
		if (dinero.equals("1") || dinero.equals("2") || dinero.equals("5") || dinero.equals("10")) {
			BigDecimal inputDinero = new BigDecimal(dinero + ".000");
			balance = balance.add(inputDinero);
			
			
			printeador.println("\r" + ">" + tiempo() + " INGRESAR DINERO $" + inputDinero + " $" + balance);
			printeador.flush();
			ingresarDinero();
		}
		else if (dinero.toLowerCase().equals("x")) {
			menuCompra();
		}
		else if (dinero.equals("20")) {
			System.out.println("La maquina no acepta billetes de 20 mil!!");
			ingresarDinero();
		}
		else {
			System.out.println("Opcion Invalida!");
			System.out.println("******************************");
			ingresarDinero();
		}
		
	}
	
	
	public static void comprarItem() {

		
		try {
			inventario.printearProductos();
		} catch (FileNotFoundException error) {
			error.printStackTrace();
		}
		
		System.out.println("Que desea consumir hoy?");
		System.out.println("****** O ingresar \"X\" para salir ******");
		
		String eleccion = keyboardInput.nextLine();
		
		eleccion = eleccion.toUpperCase(); //Convierte el input a mayusculas
		
		if (eleccion.toLowerCase().equals("x")) {
			menuCompra();
		}
		
		if (!inventario.getContenido().containsKey(eleccion)) {
			System.out.println("Seleccion Invalida!");
			System.out.println("=========================================");
			comprarItem();
		}

		
		Items itemVendido = inventario.eliminarItem(eleccion);
		
		if (itemVendido != null) {
		
			if (balance.compareTo(itemVendido.getPrecio()) > 0) {
				System.out.println(itemVendido.getNombre());
				System.out.println(itemVendido.getPrecio());
				
				BigDecimal preBalance = balance;
				
				balance = balance.subtract(itemVendido.getPrecio());
				System.out.println("El balance es de $" + balance);
				
				System.out.println(itemVendido.getLinea());
				

				printeador.println("\r" + ">" + tiempo() + " " + itemVendido.getNombre() + " " + eleccion + " $" + preBalance + " $" + balance);
				printeador.flush();
				
				registroVentaas.nuevaVenta(itemVendido.getNombre(), itemVendido.getPrecio());

				menuCompra();
			}
			else {
				inventario.getContenido().get(eleccion).push(itemVendido);
				System.out.println("Dinero INSUFICIENTE!");
				System.out.println("******** INSERTAR MAS DINERO *********");
				System.out.println();
				comprarItem();
			}
		}
		else {
			System.out.println("OPCION INVALIDA!");
			System.out.println("*****************************************");
			comprarItem();
		}
	}
	
	public static void finalizar() {
		
		System.out.println("Vuelto entregado: $" + balance);

		BigDecimal preBalance = balance;
		
		BigDecimal quinientos = new BigDecimal("0.500");
		int quinientospesos = 0;
		
		BigDecimal cien = new BigDecimal("0.100");
		int cienpesos = 0;
		
		BigDecimal cincuenta = new BigDecimal("0.050");
		int cincuentapasos = 0;
		
		while(balance.compareTo(quinientos) >= 0) {
			quinientospesos++;
			balance = balance.subtract(quinientos);
		}
		
		while(balance.compareTo(cien) >= 0) {
			cienpesos++;
			balance = balance.subtract(cien);
		}
		
		while(balance.compareTo(cincuenta) >= 0) {
			cincuentapasos++;
			balance = balance.subtract(cincuenta);
		}
		
		System.out.println("Quinientos pesos: " + quinientospesos + ", " + "Cien pesos: " + cienpesos + ", " + "Cincuenta pesos: " + cincuentapasos);
		
		
		balance = balance.subtract(balance);
		
		
		printeador.println("\r" + ">" + tiempo() + " Cambio de $" + preBalance + " $" + balance);
		printeador.flush();
		

		gracias();
	}


	public static void gracias() {
		System.out.println("Gracias por usar la maquina de nicolas!");
		System.out.println("===== Vuelva pronto! =====");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}
	
	public static String tiempo() {
		Date date = new Date();
		Timestamp horaVentas = new Timestamp(date.getTime());
		SimpleDateFormat patronHorario = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		return patronHorario.format(horaVentas);
	}
	
}
