package org.udec.tarea2;

/** Eexcepción que se lanza para indicar que la fecha y hora especificada para la reunión
 *  es anterior a la hora actual.
 */
public class FechaReunionInvalidaException extends Exception {
    public FechaReunionInvalidaException() {
        super("Hora ingresada es anterior a hora actual.");
    }
}
