package org.udec.tarea2;

import java.time.Instant;


/**
 * Clase que registra la asistencia de un invitado que implementa la interfaz {@link Invitable}
 * a una reunión, indicando su presencia/ausencia y la hora de llegada.
 */
public class Asistencia {

    /** Variable que representa a un invitado que implementa la interfaz {@link Invitable}.
     */
    private final Invitable invitado;

    /**
     * Variable que registra el instante exacto en que un invitado llegó a una reunión.
     */
    private final Instant horaLlegada;

    /** Indica si un invitado estuvo presente en la reunión.
     * Su valor será {@code true} si el invitado asistió y se registró su presencia,
     * o {@code false} en caso contrario.
     */
    private final boolean presente;

    /**
     * Clase que registra la asistencia de un invitado que implementa la interfaz {@link Invitable}
     * a una reunión, indicando su presencia/ausencia y la hora de llegada.
     *
     * @param invitado Invitado al que se marca su asistencia.
     * @param presente Estado de presencia del invitado, {@code true} si asistió y {@code false} si no.
     * @param horaLlegada Instante de tiempo en que invitado llegó a la reunión.
     */
    public Asistencia(Invitable invitado, boolean presente, Instant horaLlegada) {
        this.invitado = invitado;
        this.horaLlegada = horaLlegada;
        this.presente = presente;
    }

    /**
     * Método que obtiene al invitado asociado con la asistencia.
     *
     * @return El invitado registrado en esta asistencia.
     */
    public Invitable getInvitado() {
        return invitado;
    }

    /**
     * Indica si un invitado estuvo presente en la reunión.
     *
     * @return {@code true} si el invitado asistió a la reunión, {@code false} en caso contrario.
     */
    public boolean isPresente() {
        return presente;
    }

    /**
     * Método que obtiene la hora de llegada de un invitado a la reunión.
     *
     * @return Un objeto {@link Instant} que representa el instante exacto en el que el invitado llegó a la reunión.
     */
    public Instant getHoraLlegada() {
        return horaLlegada;
    }

}






