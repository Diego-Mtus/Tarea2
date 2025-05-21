package org.udec.tarea2;

import java.time.Instant;

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
}
