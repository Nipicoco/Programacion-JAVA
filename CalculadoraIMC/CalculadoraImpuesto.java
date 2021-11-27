public class CalculadoraImpuesto extends Calculadora {
    public double calcularImpuesto(double precioOriginalArticulo, double porcentajeImpuesto) {
        double impuesto, temp;
        
        temp = dividir(porcentajeImpuesto, 100);
        impuesto= multiplicar(precioOriginalArticulo, temp);

        return impuesto;
    }
}
