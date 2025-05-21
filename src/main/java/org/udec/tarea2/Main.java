package org.udec.tarea2;

public class Main {
    public static void main(String[] args) {

        Departamento dTI = new Departamento("TI");
        Empleado emp0 = new Empleado("1", "Juan", "López", "juan@empresa.com");
        Empleado emp1 = new Empleado("2", "Ana", "Soto", "ana@empresa.com");
        Empleado emp2 = new Empleado("3", "Carlos", "Paz", "carlos@empresa.com");
        System.out.println(dTI);

        Reunion test = new ReunionVirtual(2025, 5, 20, 20, 2, 25, emp0, "zoom.com");
        System.out.println(test);

        test.invitarIndividuo(emp0);
        test.invitarIndividuo(emp0);

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