package org.udec.tarea2;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

// !!
// No tiene sentido crear instancias de Retraso, Asistencia y Nota fuera del contexto de la reunión, lo podríamos cambiar encapsulando esas clases
// dentro de la clase reunión, para que solamente Reunion tenga acceso a esos constructores. No lo cambiamos a eso para que todas las clases tengan
// su archivo .java propio y cumplir con la pauta.
// !!

public class Main {

    /**
     * Método que crea un objeto {@link Instant} a partir de los valores proporcionados para el año, mes, día, hora y minuto.
     *
     * @param año El año deseado para el objeto Instant.
     * @param mes El mes deseado (1 para enero, 12 para diciembre).
     * @param dia El día del mes deseado.
     * @param hora La hora del día deseada en formato de 24 horas.
     * @param minuto El minuto deseado.
     * @return Un objeto {@link Instant} correspondiente a la fecha y hora especificadas.
     */
    public static Instant creadorDeInstant(int año, int mes, int dia, int hora, int minuto){
        Date fechaAuxiliar = new Date(año-1900, mes - 1, dia, hora, minuto);
        return Instant.ofEpochMilli(fechaAuxiliar.getTime());
    }

    public static void main(String[] args) {

        Departamento dTI = new Departamento("TI");
        Empleado emp0 = null;
        Empleado emp1 = new Empleado("2", "Ana", "Soto", "ana@empresa.com");
        Empleado emp2 = new Empleado("3", "Carlos", "Paz", "carlos@empresa.com");
        InvitadoExterno ext1 = new InvitadoExterno("María", "Rojas", "maria@ext.com");
        InvitadoExterno ext2 = new InvitadoExterno("Tomás", "Villanueva", "tomas@ext.com");
        dTI.agregarEmpleado(emp0);
        dTI.agregarEmpleado(emp1);
        dTI.agregarEmpleado(emp2);
        System.out.println(dTI);

        Instant fechaPrueba = creadorDeInstant(2025, 5, 20, 20, 2);

        try{
            Reunion test = new ReunionVirtual(Instant.now().plusSeconds(20),1, tipoReunion.TÉCNICA, emp0,"zoom.com");
            System.out.println(test);

            // Sección de invitar personas
            test.invitarIndividuo(emp0);
            test.invitarDepartamento(dTI);
            test.invitarIndividuo(ext1);


            test.registrarPresencia(ext1, Instant.now());
            test.registrarPresencia(ext2, Instant.now());

            test.iniciar();
            System.out.println("\nInicio de reunión:");


            test.registrarPresencia(emp0, Instant.now().plusSeconds(25));
            test.registrarPresencia(emp1, null);
            test.crearNota("Nota 1 test", Instant.now().plusSeconds(2));
            test.crearNota("Nota 2 test", Instant.now());

            test.finalizar(6);

            System.out.println(test.getListaDeNotas());
            System.out.println("Reunion ha durado " + test.calcularTiempoReal() + " minutos.");

            System.out.println("Lista de asistencias:\n" + test.obtenerAsistencias());
            System.out.println("Lista de retrasos:\n" + test.obtenerRetrasos());
            System.out.println("Lista de ausencias:\n" + test.obtenerAusencias());
            System.out.println("Cantidad de asistencias: " + test.obtenerTotalAsistencias());
            System.out.println("Porcentaje de asistencia: " + test.obtenerPorcentajeAsistencia() + "%");

            try {
                test.generarInforme("test.txt");
            } catch (IOException e) {
                System.out.println("Error: Informe no generado.\n");
            }

        } catch (FechaReunionInvalidaException | DuracionReunionInvalidaException | OrganizadorInvalidoException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }



    }
}