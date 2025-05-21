package org.udec.tarea2;

import java.time.Instant;

public class Asistencia {

    private final Invitable invitado;
    private final boolean presente;
    private final boolean tarde;
    private final Instant horaLlegada;

    public Asistencia(Invitable invitado, boolean presente, boolean tarde, Instant horaLlegada) {
        this.invitado = invitado;
        this.presente = presente;
        this.tarde = tarde;
        this.horaLlegada = horaLlegada;
    }

    public Invitable getInvitado() {
        return invitado;
    }

    public boolean isPresente() {
        return presente;
    }

    public boolean isTarde() {
        return tarde;
    }

    public Instant getHoraLlegada() {
        return horaLlegada;
    }

}






