package org.udec.tarea2;

public class OrganizadorInvalidoException extends RuntimeException {
    public OrganizadorInvalidoException() {
        super("Organizador no puede ser null");
    }
}
