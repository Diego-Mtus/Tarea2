package org.udec.tarea2;

public class Main {
    public static void main(String[] args) {

        Reunion test = new ReunionVirtual(2025, 5, 20, 20, 2, 25, "test");
        System.out.println(test);

        Departamento dTI = new Departamento("TI");
        Empleado emp0 = new Empleado("1", "Juan", "López", "juan@empresa.com", dTI);
        Empleado emp1 = new Empleado("2", "Ana", "Soto", "ana@empresa.com", dTI);
        Empleado emp2 = new Empleado("3", "Carlos", "Paz", "carlos@empresa.com", dTI);
        System.out.println(dTI);

        test.iniciar();
        System.out.println(test.getHoraInicio());
        test.finalizar(6);
        System.out.println(test.getHoraFin());
        System.out.println(test.calcularTiempoReal() + " minutos.");

        test.crearNota("Nota 1 test");
        test.crearNota("Nota 2 test");

        System.out.println(test.getListaDeNotas());
    }
}