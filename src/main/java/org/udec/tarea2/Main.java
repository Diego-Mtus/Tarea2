package org.udec.tarea2;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

// Cambios respecto al UML y justificaciones (preliminar)
// 1) Se añadió met0do estático creadorDeInstant() para gestionar más facilmente los Instant.
// 2) Invitación ahora también almacena al individuo invitado, esto para que exista una lista de invitaciones en Reunion.
// 3) Departamento ya no cuenta con la interfaz invitable, dado a que invitable es una característica de cada individuo, por lo tanto
// si se quiere invitar a un Departamento, invitará individualmente a cada uno de sus integrantes.
// 4) Debido a que Invitable es una característica de individuos, la interfaz también cuenta con un prototipo getNombreCompleto().
// 5) La clase Asistencia cuenta con información del invitado, un bool de si es que fue o no a la reunión y la hora en que fue, t0do esto
// para implementar la lógica de las asistencias de la reunión, el booleano para marcarlo como presente o ausente, y la hora para manejar
// su retraso.
// 6) Se crea clase InvitadoExterno según lo solicitado en la pauta.
// 7) Retraso también almacena al individuo invitado, esto para que exista una lista de retrasos en Reunion.
// 8) Las invitaciones son gestionadas por la clase Reunion debido a que pueden existir varias reuniones, y cada reunion
// debe tener su propia lista de invitados (y no invitar a un mismo individuo más de una vez)
// 9) La asistencia es gestionada por la clase Reunion (clase Asistencia sólo existe dentro de Reunion), se crea un mapa
// que a cada invitado le asigna su Asistencia, y de acuerdo a si llego tarde o no, lo marca como atrasado.
// 10) Se integraron variables finales estáticas en Reunion para darle el formato correcto al querer imprimir un Instant.
// 11) Se encontró lógico que los retrasos se cuenten respecto a la hora prevista de la reunión y no la hora real, dado
// a que eso fue lo "avisado" al individuo invitado, cualquier diferencia entre la hora prevista y real no es culpa del invitado.
// 12) Todos los que fueron invitados y no se marcaron como presentes antes de que la reunión termine serán marcados como ausentes,
// esto se implemento viendo discrepancias entre lista de invitados y lista de asistencia.
// 13) El porcentaje de asistencia es respecto a los invitados, y no se puede dar el caso de que se registren como presentes personas
// no invitadas.

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
        Empleado emp0 = new Empleado("1", "Juan", "López", "juan@empresa.com");
        Empleado emp1 = new Empleado("2", "Ana", "Soto", "ana@empresa.com");
        Empleado emp2 = new Empleado("3", "Carlos", "Paz", "carlos@empresa.com");
        InvitadoExterno ext1 = new InvitadoExterno("María", "Rojas", "maria@ext.com");
        dTI.agregarEmpleado(emp0);
        dTI.agregarEmpleado(emp1);
        dTI.agregarEmpleado(emp2);

        Instant fechaPrueba = creadorDeInstant(2025, 5, 20, 20, 2);

        try{
            Reunion test = new ReunionVirtual(Instant.now().plusSeconds(20),-1, tipoReunion.TÉCNICA, "zoom.com");
            System.out.println(test);

            test.invitarIndividuo(emp0);
            test.invitarDepartamento(dTI);

            test.registrarPresencia(ext1, Instant.now());

            test.iniciar();
            System.out.println("\nInicio de reunión:");

            test.registrarPresencia(emp0, Instant.now().plusSeconds(25));
            test.crearNota("Nota 1 test");
            test.crearNota("Nota 2 test");

            test.finalizar(6);

            System.out.println(test.getListaDeNotas());
            System.out.println("Reunion ha durado " + test.calcularTiempoReal() + " minutos.");

            System.out.println("Lista de asistencias:\n" + test.obtenerAsistencias());
            System.out.println("Lista de retrasos:\n" + test.obtenerRetrasos());
            System.out.println("Lista de ausencias:\n" + test.obtenerAusencias());
            System.out.println("Cantidad de asistencias: " + test.obtenerTotalAsistencias());
            System.out.println("Porcentaje de asistencia: " + test.obtenerPorcentajeAsistencia() + "%");

            try {
                test.generarInforme("../informe.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FechaReunionInvalidaException | DuracionReunionInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }
}