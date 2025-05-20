package org.udec.tarea2;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;


public class Invitacion {
    private Instant horaInicio;
    private Duration duracion;

    public Invitacion(){
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }

    public Duration getDuracion() {
        return duracion;
    }


}
