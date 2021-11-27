
public class CalculadoraIMC extends Calculadora {
    public double calcularIMC(double peso, double estatura) {
        double imc;
        double temp = multiplicar(estatura, estatura);

        imc = dividir(peso, temp);
        return (imc);
    }

    public String clasificar(double imc) {
        if (imc <= 18.4) {
            return "Bajo";
        } else if (imc >= 18.5 && imc <= 24.9) {
            return "Normal";
        } else if (imc >= 25 && imc <= 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc <= 34.9) {
            return "Obesidad grado 1";
        }

        return "Error: Valor muy bajo o muy alto.";
    }
}
