public class CuentaAhorro extends Cuenta {

    private final double tasaInteres;
    private long balance;

    public CuentaAhorro(int numeroCuenta, double tasaInteres) {
        super(numeroCuenta);
        this.tasaInteres = tasaInteres;
    }

    public double calcInteres() {
        return balance * tasaInteres;
    }

    public void aplicarInteres() {
        double interes = calcInteres();
        System.out.printf("Monto de intereses %.2f agregado al balance%n", interes);
        depositar(interes);
    }
    public void mostrar(){
        System.out.printf("El balance de la cuenta es de " + balance);
        System.out.println("");
    }
    public void depositar(double monto) {

        if(monto <= 0){
            System.out.printf("No se puede depositar un monto negativo o igual a 0.\n");
            return;
        }
        else if (monto > 0) {
            balance += monto;
            System.out.printf("Monto de %.2f depositado%n", monto);
            System.out.printf("Balance actual es de: " + balance);

        } else {
            System.out.println("No se puede depositar un monto negativo!");
        }
    }
    

    public void retirar(double monto) {

        if(monto>balance){
            System.out.println("El monto a retirar supera el balance actual!");
            return;
        }
        if (monto > 0) {
            if ((monto) <= balance) {
                System.out.printf("Monto de %.2f retirado de la cuenta%n", monto);
                balance -= monto;
                System.out.printf("Balance actual es de: %.2f%n", balance);
            }

        } else {
            System.out.println("No se puede retirar un monto negativo!");
        }
    }

}