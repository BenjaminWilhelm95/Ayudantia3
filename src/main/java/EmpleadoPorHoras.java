public class EmpleadoPorHoras extends Empleado {
    private double salarioPorHora;
    private int horasTrabajadas;

    public EmpleadoPorHoras(String nombre, double salarioPorHora, int horasTrabajadas) {
        super(nombre);
        this.salarioPorHora = salarioPorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSalario() {
        return salarioPorHora * horasTrabajadas;
    }

    // Getter para salarioPorHora
    public double getSalarioPorHora() {
        return salarioPorHora;
    }

    // Getter para horasTrabajadas
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
}
