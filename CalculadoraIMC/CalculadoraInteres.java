public class CalculadoraInteres extends Calculadora {
    public double calcularInteresSimple(double capitalInicial, double tasaDeInteres, int anios) {
        double interes;
        interes = multiplicar(dividir(tasaDeInteres, 100), capitalInicial) ;
        interes = multiplicar(interes, anios);
        return interes;
    }

    public double calcularInteresCompuesto(double capitalInicial, double tasaDeInteres, int anios) {
        double interesC;
        interesC = multiplicar(capitalInicial, potencia(dividir(tasaDeInteres, 100), anios));
        return interesC;
    }
}
