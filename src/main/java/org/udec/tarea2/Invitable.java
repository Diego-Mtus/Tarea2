package org.udec.tarea2;

import java.time.Instant;

/**
 * Interfaz que define el comportamiento para los objetos que pueden ser invitados a reuniones.
 *
 * Los objetos que implementen esta interfaz deben definir los métodos para invitar a un individuo,
 * especificando la hora de la reunión, y para obtener el nombre completo del individuo invitado.
 */
public interface Invitable {

    void invitar(Instant hora);
    String getNombreCompleto();
}
