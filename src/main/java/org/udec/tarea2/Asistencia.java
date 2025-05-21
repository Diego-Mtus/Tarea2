package org.udec.tarea2;

import java.time.Instant;

public class Asistencia {

    private final Invitable invitado;
    private final Instant horaLlegada;
    private final boolean presente;

    public Asistencia(Invitable invitado, boolean presente, Instant horaLlegada) {
        this.invitado = invitado;
        this.horaLlegada = horaLlegada;
        this.presente = presente;
    }

    public Invitable getInvitado() {
        return invitado;
    }

    public boolean isPresente() {
        return presente;
    }

    public Instant getHoraLlegada() {
        return horaLlegada;
    }

}






