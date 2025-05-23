package org.udec.tarea2;

import java.time.Instant;


/** Clase que representa una invitación a una reunión específica,
 * asignando un horario a un individuo que será invitado.
 */
public class Invitacion {

    /** Representa el instante en el que comienza la reunión a la que se invita.
     */
    private final Instant horaInicio;

    /** Representa al individuo invitado a una reunión que implementa la interfaz {@link Invitable}.
     */
    private final Invitable invitado;

    /**
     * Clase que representa una invitación a una reunión específica,
     * asignando a un invitado un horario para asistir.
     *
     * @param invitado Individuo que implementa la interfaz {@link Invitable} al que se le quiere invitar.
     * @param reunion Reunión a la que se le está invitando.
     */
    public Invitacion(Invitable invitado, Reunion reunion){
        horaInicio = reunion.getHoraPrevista();
        this.invitado = invitado;
        this.invitado.invitar(horaInicio);
    }

    /**
     * Método para obtener el invitado asociado a la invitación.
     *
     * @return El invitado que implementa la interfaz {@link Invitable} y que fue invitado a la reunión.
     */
    public Invitable getInvitado() {
        return invitado;
    }
}
