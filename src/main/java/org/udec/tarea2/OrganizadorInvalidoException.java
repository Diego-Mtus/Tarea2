package org.udec.tarea2;

/**
 * Excepción que se lanza cuando el organizador de una reunión es inválido, es decir, que se ingrese un organizador null.

 */
public class OrganizadorInvalidoException extends Exception {
    public OrganizadorInvalidoException() {
        super("Organizador no puede ser null");
    }
}
