package org.udec.tarea2;

import java.time.Instant;


public class Invitacion {
    private final Instant horaInicio;
    private final Invitable invitado;

    public Invitacion(Invitable invitado, Reunion reunion){
        horaInicio = reunion.getHoraPrevista();
        this.invitado = invitado;
        this.invitado.invitar(horaInicio);
    }

    public Invitable getInvitado() {
        return invitado;
    }
}
