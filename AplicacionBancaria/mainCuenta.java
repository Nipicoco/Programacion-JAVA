import java.util.*;
/* Una empresa pidio una aplicacion bancaria con las siguientes funciones, agregar cuentas, retirar dinero, aplicar intereses a cuentas de ahorro, depositar dinero y mostrar el
monto de las cuentas deseadas, tambien existe la opcion de crear una cuenta de ahorro o corrientes*/ 
public class mainCuenta {


    public static void main(String [] args) {

        Scanner teclado = new Scanner(System.in);

        Cuenta[] cuentas = new Cuenta[10];
        int numeroCuentas = 0;
        

        int eleccion;

        do {
            eleccion = menu(teclado);
            System.out.println();
            
            if(eleccion == 1) {
                cuentas[numeroCuentas++] = crearCuenta(teclado);
            } else if(eleccion == 2) {
                depositar(cuentas, numeroCuentas, teclado);
            } else if(eleccion == 3) {
                retirarDinero(cuentas, numeroCuentas, teclado);
            } else if(eleccion == 4) {
                aplicarInteres(cuentas, numeroCuentas, teclado);
            } else if(eleccion == 5) {
                mostrarMonto(cuentas, numeroCuentas, teclado);
            } else {
                System.out.println("Adios!");
            }
            System.out.println();
            }while(eleccion != 6);
    }

    public static void mostrarMonto(Cuenta[] cuentas, int count, Scanner teclado){
        System.out.print("\nIngresar numero de cuenta: ");
        int numeroCuenta = teclado.nextInt();

        int index = buscarCuenta(cuentas, count, numeroCuenta);

        if(index >= 0) {

            cuentas[index].mostrar();
        } else if(index<0) {
            System.out.println("No existe ninguna cuenta con ese numero: " + numeroCuenta);
            return;
            
        }
        
    }


    public static int menuCuentas(Scanner teclado) {
        System.out.println("Seleccionar tipo de cuenta");
        System.out.println("1. Cuenta Corriente");
        System.out.println("2. Cuenta de Ahorro");

        int eleccion;
        do {
            System.out.print("Ingresar eleccion: ");
            eleccion = teclado.nextInt();
        }while(eleccion < 1 || eleccion > 2);

        return eleccion;
    }

    public static int buscarCuenta(Cuenta[] cuentas, int count, int numeroCuenta) {

        for(int i=0; i<count; i++) {
            if(cuentas[i].getNumeroCuenta() == numeroCuenta) {
                return i;
            }
        }

        return -1;
    }


    public static void depositar(Cuenta[] cuentas, int count, Scanner teclado) {

        System.out.print("\nIngresar numero de cuenta: ");
        int numeroCuenta = teclado.nextInt();


        int index = buscarCuenta(cuentas, count, numeroCuenta);

        if(index >= 0) {

            System.out.print("Ingresar monto a depositar: ");
            double monto = teclado.nextDouble();

            cuentas[index].depositar(monto);
        } else if(index<0) {
            System.out.println("No existe ninguna cuenta con ese numero: " + numeroCuenta);
            return;
        }
    }

    public static void retirarDinero(Cuenta[] cuentas, int count, Scanner teclado) {

        System.out.print("\nIngresar numero de cuenta: ");
        int numeroCuenta = teclado.nextInt();


        int index = buscarCuenta(cuentas, count, numeroCuenta);

        if(index >= 0) {
            System.out.print("Ingresar monto a retirar: ");
            double monto = teclado.nextDouble();
            cuentas[index].retirar(monto);
        } else if(index<0) {
            System.out.println("No existe ninguna cuenta con ese numero: " + numeroCuenta);
            return;
        }
    }


    public static void aplicarInteres(Cuenta[] cuentas, int count, Scanner teclado) {
        System.out.print("\nIngresar numero de cuenta ahorro: ");
        int numeroCuenta = teclado.nextInt();

        int index = buscarCuenta(cuentas, count, numeroCuenta);

        if (index >= 0) {

            if (cuentas[index] instanceof CuentaAhorro) {
                ((CuentaAhorro) cuentas[index]).aplicarInteres();
            }
            if (cuentas[index] instanceof CuentaCorriente) {
                System.out.println("ERROR!, Numero de cuenta pertenece a cuenta corriente.");
                return;
            }
            } else {
            System.out.println("El numero de cuenta ingresado no existe: " + numeroCuenta);
        }
    }
    


    public static Cuenta crearCuenta(Scanner teclado) {
        Cuenta cuenta;
        int eleccion = menuCuentas(teclado);
        int numeroCuenta;

        System.out.print("Ingresar numero de cuenta: ");
        numeroCuenta = teclado.nextInt();
        if(eleccion == 1)  {
            System.out.print("Ingresar tarifa de transaccion: ");
            double tarifa = teclado.nextDouble();
            cuenta = new CuentaCorriente(numeroCuenta, tarifa);
        }
        else {

            System.out.print("Ingresar tasa de interes: ");
            double ir = teclado.nextDouble();
            cuenta = new CuentaAhorro(numeroCuenta, ir);
        }

        return cuenta;
    }


    public static int menu(Scanner teclado) {
        System.out.println("BANCO DE NICOLAS");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar Fondos");
        System.out.println("3. Retirar Fondos");
        System.out.println("4. Aplicar Intereses a cuenta de ahorro");
        System.out.println("5. Revisar monto cuenta");
        System.out.println("6. Salir");

        int eleccion;

        do {
            System.out.print("Ingresar eleccion: ");
            eleccion = teclado.nextInt();
        } while(eleccion < 1 || eleccion > 6);

        return eleccion;
    }
}