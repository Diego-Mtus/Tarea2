package org.udec.tarea2;

import java.time.Instant;


public class Invitacion {
    private Instant horaInicio;

    public Invitacion(Reunion reunion){
        horaInicio = reunion.getHoraPrevista();
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }


}
