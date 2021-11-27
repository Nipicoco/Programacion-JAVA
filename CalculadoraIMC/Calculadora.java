public abstract class Calculadora {
    public int sumar(int num1, int num2) {
        return (num1 + num2);
    }

    public double sumar(double num1, double num2) {
        return (num1 + num2);
    }

    public int restar(int num1, int num2) {
        return (num1 - num2);
    }

    public double restar(double num1, double num2) {
        return (num1 - num2);
    }

    public int multiplicar(int num1, int num2) {
        return (num1 * num2);
    }

    public double multiplicar(double num1, double num2) {
        return (num1 * num2);
    }

    public int dividir(int num1, int num2) {
        return (num1 / num2);
    }

    public double dividir(double num1, double num2) {
        return (num1 / num2);
    }
    public double potencia(double a, double b){
        return Math.pow(a, b);
    }
    public double potencias(int num1, int num2){
        return  Math.pow(num1, num2);
    }
    public double raiz(int num1, int num2){
        return  (double) Math.pow(num1, 1.0/num2);
    }
    
}
    
    
    
    


