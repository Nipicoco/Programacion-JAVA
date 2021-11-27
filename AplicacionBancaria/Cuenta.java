public abstract class Cuenta {

    private int numeroCuenta;

    public double balance;


    public Cuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        balance = 0;
    }



    public int getNumeroCuenta() {
        return this.numeroCuenta;
    }



    public abstract void depositar(double monto);


    public abstract void retirar(double monto);

    public abstract void mostrar();
}



