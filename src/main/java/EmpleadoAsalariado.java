public class EmpleadoAsalariado extends Empleado {
    private double salarioBase;

    public EmpleadoAsalariado(String nombre, double salarioBase) {
        super(nombre);
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return salarioBase;
    }

    // Getter para salarioBase
    public double getSalarioBase() {
        return salarioBase;
    }
}
