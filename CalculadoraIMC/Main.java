import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 /*Calculadora hecha para poder calcular el imc de las personas, los impuestos e intereses dadas las opciones disponibles
 * Se usara BufferedReader para leer caracteres o numeros desde un input, en este caso, implementado en BufferedReader lectura
 * para poder tomar el input del usuario en las opciones
 * Al igual usaremos IOException para atrapar errores en caso de encontrar errores de calculo a lo que se hara display del error y se cerrara la aplicacion
*/

public abstract class Main {
    public static void main(String[] args) throws IOException {
        int opcion;
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1. Calcular IMC");
        System.out.println("2. Calcular Impuesto");
        System.out.println("3. Calcular Interes");
        System.out.println("4. Calcular Potencia enesima");
        System.out.println("4. Calcular Raiz enesima");
        System.out.println("Seleccione la opcion que requiera: ");
        try {
            opcion = Integer.parseInt(lectura.readLine());

            switch (opcion) {
                case 1 : {
                    CalculadoraIMC calculadora = new CalculadoraIMC();
                    double peso, estatura;
                    System.out.println("Ingrese su peso: ");
                    peso = Double.parseDouble(lectura.readLine());
                    System.out.println("Ingrese su altura(metros separado con punto): ");
                    estatura = Double.parseDouble(lectura.readLine());

                    double imc = calculadora.calcularIMC(peso, estatura);

                    System.out.println("Su IMC es : " + imc);
                    System.out.println("La clasificacion es : " + calculadora.clasificar(imc));
                    return;
                }
                case 2 : {
                    CalculadoraImpuesto calculadora = new CalculadoraImpuesto();
                    double precio, porcentajeImpuesto;

                    System.out.println("Ingrese el precio del articulo: ");
                    precio = Double.parseDouble(lectura.readLine());
                    System.out.println("Ingrese el porcentaje de impuesto: ");
                    porcentajeImpuesto = Double.parseDouble(lectura.readLine());

                    double impuesto = calculadora.calcularImpuesto(precio, porcentajeImpuesto);
                    System.out.println("El valor del impuesto es: " + impuesto);
                    System.out.println("El precio final del articulo es: " + (precio + impuesto));
                    return;
                }
                case 3 : {
                    double capital, tasaInteres;
                    int anios;
                    CalculadoraInteres calculadora = new CalculadoraInteres();
                    System.out.println("Que tipo de interes desea calcular: ");
                    System.out.println("1. Simple");
                    System.out.println("2. Compuesto.");

                    opcion = Integer.parseInt(lectura.readLine());

                    switch (opcion) {
                        case 1 : {
                            System.out.println("Ingrese el capital inicial: ");
                            capital = Double.parseDouble(lectura.readLine());
                            System.out.println("Ingrese la tasa de interes(%): ");
                            tasaInteres = Double.parseDouble(lectura.readLine());
                            System.out.println("Ingrese la cantidad de anios: ");
                            anios = Integer.parseInt(lectura.readLine());

                            double interes = calculadora.calcularInteresSimple(capital, tasaInteres, anios);
                            System.out.println("El interes es: " + interes);
                            System.out.println("El capital final es: " + (capital + interes));
                            return;
                        }
                        case 2 : {
                            System.out.println("Ingrese el capital inicial: ");
                            capital = Double.parseDouble(lectura.readLine());
                            System.out.println("Ingrese la tasa de interes(%): ");
                            tasaInteres = Double.parseDouble(lectura.readLine());
                            System.out.println("Ingrese la cantidad de anios: ");
                            anios = Integer.parseInt(lectura.readLine());

                            double interes = calculadora.calcularInteresCompuesto(capital, tasaInteres, anios);
                            System.out.println("El interes es: " + interes);
                            System.out.println("El capital final es: " + (capital + interes));
                            return;
                        }
                    }
                }
                case 4 : {
                    int n1, n2;
                    CalculadoraPotencias calculadora = new CalculadoraPotencias();
                    System.out.println("Ingrese base: ");
                    n1 = Integer.parseInt(lectura.readLine());
                    System.out.println("Ingrese exponente: ");
                    n2 = Integer.parseInt(lectura.readLine());

                    double resu = calculadora.potencias(n1, n2);

                    System.out.println("Su resultado es : " + resu);
        
                    return;
                }
                case 5 : {
                    int n1, n2;
                    CalculadoraRaiz calculadora = new CalculadoraRaiz();
                    System.out.println("Ingrese base: ");
                    n1 = Integer.parseInt(lectura.readLine());
                    System.out.println("Ingrese exponente: ");
                    n2 = Integer.parseInt(lectura.readLine());

                    double res = calculadora.raiz(n1, n2);

                    System.out.println("Su resultado es : " + (double)res);
        
                    return;
                }
            }
        } catch (IOException exception) {
            System.out.println("Hubo un error al obtener los datos. La aplicacion se cerrara.");
        }
    }
}

