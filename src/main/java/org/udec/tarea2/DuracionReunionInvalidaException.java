package org.udec.tarea2;

public class DuracionReunionInvalidaException extends Exception {
    public DuracionReunionInvalidaException() {
        super("Duración de reunión debe ser mayor a 0 minutos.");
    }
}
