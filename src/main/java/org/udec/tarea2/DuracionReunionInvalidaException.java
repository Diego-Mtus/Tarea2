package org.udec.tarea2;

/**
 * Excepción que se lanza cuando la duración de una reunión es inválida, es decir, que tenga una duración menor o igual
 * a cero.
 */
public class DuracionReunionInvalidaException extends Exception {
    public DuracionReunionInvalidaException() {
        super("Duración de reunión debe ser mayor a 0 minutos.");
    }
}
