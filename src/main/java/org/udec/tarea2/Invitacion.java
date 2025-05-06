package org.udec.tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;


public class Invitacion {
    private Instant horaInicio;
    private Duration duracion;

    public Invitacion(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion){
        Date fechaAuxiliar = new Date(año-1900, mes - 1, dia, hora, minuto);
        horaInicio = Instant.ofEpochMilli(fechaAuxiliar.getTime());
        System.out.println("Hora inicio es " + horaInicio.toString());

    }
}
