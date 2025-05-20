package org.udec.tarea2;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public abstract     class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private Asistencia asistenciasDeReunion;
    final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a z, dd/MM/yyyy");

    public Reunion(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion){
        long actual = System.currentTimeMillis();

        Date fechaAuxiliar = new Date(año-1900, mes - 1, dia, hora, minuto);
        Instant horaInicioAux = Instant.ofEpochMilli(fechaAuxiliar.getTime());


        if(horaInicioAux.toEpochMilli() <= actual || minutosDeDuracion <= 0){
            // Se tira exception FechaReunionInvalidaException
            System.out.println("ERROR");
        }

        fecha = fechaAuxiliar;
        horaPrevista = horaInicioAux;
        duracionPrevista = Duration.of(minutosDeDuracion, ChronoUnit.MINUTES);

    }

    public Instant getHoraPrevista(){
        return horaPrevista;
    }

    @Override
    public String toString() {
        if(horaPrevista == null || duracionPrevista == null){
            return "Hora inicio o duración no establecida.";
        }
        return "La reunión comenzará a las " + sdf.format(Date.from(horaPrevista)) + " y durará " + duracionPrevista.getSeconds()/60 + " minutos.";
    }

}
