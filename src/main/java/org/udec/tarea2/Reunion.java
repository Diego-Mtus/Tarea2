package org.udec.tarea2;

import java.time.*;
import java.util.Date;
import java.util.List;

public abstract class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private Asistencia asistenciasDeReunion;

    public Reunion(Instant horaDeReunion){

    }

}
