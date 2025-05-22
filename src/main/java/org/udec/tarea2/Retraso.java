package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

/**
 * Clase que representa un retraso en una reunión. Contiene información sobre un invitado que llegó tarde
 * y la hora exacta de su llegada.
 */
public class Retraso {

    /** Variable que representa la hora en la que llegó un invitado atrasado.
     */
    private final Instant hora;
    /**
     * Representa a un invitado que implementa la interfaz {@link Invitable} que se atrasó en la reunión.
     */
    private final Invitable invitado;

    /**
     * Constructor que representa un retraso en una reunión. Contiene información sobre un invitado que llegó tarde
     * y la hora exacta de su llegada.
     */
    public Retraso(Invitable invitado, Instant hora){
        this.hora = hora;
        this.invitado = invitado;
    }

    /** Método que obtiene el objeto {@link Invitable} correspondiente al invitado que tuvo un
     *  retraso en la reunión.
     *
     * @return El invitado que implementa la interfaz {@link Invitable} y que llegó tarde
     *  a la reunión.
     */
    public Invitable getInvitado() {
        return invitado;
    }

    /**
     * Devuelve el instante en el que ocurrió el retraso.
     *
     * @return Un objeto {@link Instant} que representa la hora exacta en la que ocurrió el retraso.
     */
    public Instant getHora() {
        return hora;
    }

    /**
     * Devuelve una representación en string del retraso, indicando el nombre completo
     * del invitado y la hora exacta de su llegada.
     *
     * @return Una cadena que describe el retraso del invitado, incluyendo su nombre completo
     * y la hora de su llegada formateada.
     */
    @Override
    public String toString() {
        return invitado.getNombreCompleto() + " ha tenido un retraso. Ha llegado a las " + Reunion.SDF_HORA.format(Date.from(hora)) + ".";
    }
}
