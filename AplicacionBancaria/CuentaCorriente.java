
public class CuentaCorriente extends Cuenta {

    private static double TARIFA = 2.5;


    public CuentaCorriente(int numeroCuenta, double tarifa) {
        super(numeroCuenta);
        TARIFA = tarifa;
    }


    public void depositar(double monto) {
        if(monto<TARIFA){
            System.out.println("No se puede depositar un monto negativo o menor a la tarifa de:"+TARIFA);
            return;
        }
        if( monto > 0) {
            balance += monto;
            System.out.printf("Monto de %.2f depositado%n", monto);

            balance -= TARIFA;
            System.out.printf("Tarifa de %.2f aplicada%n", TARIFA);
            System.out.printf("Balance actual es de: %.2f%n", balance);
        
        } else {
            System.out.println("error en el deposito!");
            
        } 
    }
    public void mostrar(){
        System.out.printf("El balance de la cuenta es de " + balance);
        System.out.println("\n");
    }
    public void retirar(double monto) {
        if(monto>balance){
            System.out.println("El monto a retirar supera el balance actual!");
            return;
        }
        if(monto >= 0) {
            if((monto+TARIFA) <= balance) {

                System.out.printf("Monto de %.2f retirado de la cuenta%n", monto);
                balance -= monto;
                balance -= TARIFA;
                System.out.printf("Tarifa de %.2f aplicada%n", TARIFA);
                System.out.printf("Balance actual es de: %.2f%n", balance);
            }
            else {
                System.out.println("No se puede retirar un monto negativo!");
            }
        
        }
    }
}