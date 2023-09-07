import java.io.*;
import java.util.*;

public class GestorEmpleados {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Empleado> listaEmpleados = new ArrayList<>();

        // Registro de empleados
        System.out.print("Ingrese el número de empleados: ");
        int numEmpleados = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numEmpleados; i++) {
            System.out.print("Ingrese el nombre del empleado " + (i + 1) + ": ");
            String nombre = scanner.nextLine();

            System.out.print("¿Es asalariado o trabaja por horas? (asalariado/horas): ");
            String tipoEmpleado = scanner.nextLine();

            if (tipoEmpleado.equalsIgnoreCase("asalariado")) {
                System.out.print("Ingrese el salario base: ");
                double salarioBase = scanner.nextDouble();
                scanner.nextLine();

                EmpleadoAsalariado empleado = new EmpleadoAsalariado(nombre, salarioBase);
                listaEmpleados.add(empleado);
            } else if (tipoEmpleado.equalsIgnoreCase("horas")) {
                System.out.print("Ingrese el salario por hora: ");
                double salarioPorHora = scanner.nextDouble();
                System.out.print("Ingrese las horas trabajadas: ");
                int horasTrabajadas = scanner.nextInt();
                scanner.nextLine();

                EmpleadoPorHoras empleado = new EmpleadoPorHoras(nombre, salarioPorHora, horasTrabajadas);
                listaEmpleados.add(empleado);
            }
        }

        String archivoCSV = "empleados.csv";
        escribirEmpleadosCSV(listaEmpleados, archivoCSV);
        System.out.println("Datos de empleados almacenados en " + archivoCSV);

        // Lectura de datos desde el archivo CSV
        List<Empleado> empleadosDesdeArchivo = leerEmpleadosCSV(archivoCSV);

        System.out.println("Nombres y salarios de empleados almacenados en el archivo:");
        for (Empleado empleado : empleadosDesdeArchivo) {
            System.out.println("Nombre: " + empleado.nombre);
            System.out.println("Salario: " + empleado.calcularSalario());
            System.out.println("-----------------------------");
        }

        scanner.close();
    }

    private static void escribirEmpleadosCSV(List<Empleado> empleados, String archivoCSV) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoCSV))) {
            for (Empleado empleado : empleados) {
                if (empleado instanceof EmpleadoAsalariado) {
                    writer.println(empleado.nombre + ",Asalariado," + ((EmpleadoAsalariado) empleado).getSalarioBase());
                } else if (empleado instanceof EmpleadoPorHoras) {
                    EmpleadoPorHoras empPorHoras = (EmpleadoPorHoras) empleado;
                    writer.println(empleado.nombre + ",Por Horas," + empPorHoras.getSalarioPorHora() + "," + empPorHoras.getHorasTrabajadas());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Empleado> leerEmpleadosCSV(String archivoCSV) {
        List<Empleado> empleados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String tipoEmpleado = datos[1];
                if (tipoEmpleado.equalsIgnoreCase("Asalariado")) {
                    double salarioBase = Double.parseDouble(datos[2]);
                    empleados.add(new EmpleadoAsalariado(nombre, salarioBase));
                } else if (tipoEmpleado.equalsIgnoreCase("Por Horas")) {
                    double salarioPorHora = Double.parseDouble(datos[2]);
                    int horasTrabajadas = Integer.parseInt(datos[3]);
                    empleados.add(new EmpleadoPorHoras(nombre, salarioPorHora, horasTrabajadas));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return empleados;
    }
}