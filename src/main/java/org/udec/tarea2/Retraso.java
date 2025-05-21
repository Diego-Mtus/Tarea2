package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

public class Retraso {
    private final Instant hora;
    private final Invitable invitado;

    public Retraso(Invitable invitado, Instant hora){
        this.hora = hora;
        this.invitado = invitado;
    }

    public Invitable getInvitado() {
        return invitado;
    }

    public Instant getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return invitado.getNombreCompleto() + " ha tenido un retraso. Ha llegado a las " + Reunion.SDF_HORA.format(Date.from(hora)) + ".";
    }
}
