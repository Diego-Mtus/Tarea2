package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

public class Main {

    public Instant creadorDeInstant(int año, int mes, int dia, int hora, int minuto){
        Date fechaAuxiliar = new Date(año-1900, mes - 1, dia, hora, minuto);
        return Instant.ofEpochMilli(fechaAuxiliar.getTime());
    }

    public static void main(String[] args) {

        Departamento dTI = new Departamento("TI");
        Empleado emp0 = new Empleado("1", "Juan", "López", "juan@empresa.com");
        Empleado emp1 = new Empleado("2", "Ana", "Soto", "ana@empresa.com");
        Empleado emp2 = new Empleado("3", "Carlos", "Paz", "carlos@empresa.com");
        System.out.println(dTI);

        Reunion test = new ReunionVirtual(2025, 5, 20, 20, 2, 25, tipoReunion.TECNICA, "zoom.com");
        System.out.println(test);

        dTI.agregarEmpleado(emp0);
        dTI.agregarEmpleado(emp1);
        dTI.agregarEmpleado(emp2);

        test.invitarIndividuo(emp0);
        test.invitarDepartamento(dTI);

        test.iniciar();
        test.registrarPresencia(emp0, Instant.now());
        test.finalizar(6);

        System.out.println("Reunion ha durado " + test.calcularTiempoReal() + " minutos.");

        System.out.println("Lista de asistencias:\n" + test.obtenerAsistencias());
        System.out.println("Lista de retrasos:\n" + test.obtenerRetrasos());
        System.out.println("Lista de ausencias:\n" + test.obtenerAusencias());

        test.crearNota("Nota 1 test");
        test.crearNota("Nota 2 test");

        System.out.println(test.getListaDeNotas());
    }
}