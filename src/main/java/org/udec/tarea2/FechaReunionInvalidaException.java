package org.udec.tarea2;

public class FechaReunionInvalidaException extends Exception {
    public FechaReunionInvalidaException() {
        super("Hora ingresada es anterior a hora actual.");
    }
}
