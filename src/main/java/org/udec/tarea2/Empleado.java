package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

/** Clase que representa a un empleado, el cual puede ser invitado a reuniones mediante la implementación
 *  de {@link Invitable}.
 * Cada empleado tiene un identificador único, nombre, apellidos y un correo
 *  electrónico asociado.
 */
public class Empleado implements Invitable{

    /** Identificador único del empleado.
     */
    private String id;

    /** Apellidos del empleado.
     */
    private String apellidos;

    /** Variable que almacena el nombre del empleado.
     */
    private String nombre;

    /** Dirección de correo electrónico del empleado.
     */
    private String correo;

    /** Constructor de la clase Empleado, que inicializa los datos básicos de un empleado,
     * incluyendo su identificador único, nombre, apellidos y correo electrónico.
     *
     * @param id Identificador único del empleado.
     * @param nombre Nombre del empleado.
     * @param apellidos Apellidos del empleado.
     * @param correo Dirección de correo electrónico del empleado.
     */
    public Empleado(String id, String nombre, String apellidos, String correo) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.correo = correo;
    }

    /**
     * Devuelve una representación en string del objeto Empleado.
     * Incluye el nombre completo, el identificador único y el correo electrónico asociado.
     *
     * @return Un string con la información en el formato:
     * "Empleado [Nombre Completo] (ID: [Identificador]) con el correo electrónico: [Correo].".
     */
    @Override
    public String toString() {
        return "Empleado " + getNombreCompleto() + " (ID: " + getId() + ") con el correo electrónico: " + getCorreo() + ".";
    }



    /**
     * Devuelve el nombre completo del empleado concatenando nombre y apellidos.
     *
     * @return Un string que representa el nombre completo del empleado.
     */
    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    /**
     * Obtiene la dirección de correo electrónico asociada al empleado.
     *
     * @return Un string del correo electrónico del empleado.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Devuelve el identificador único del empleado.
     *
     * @return Un string del identificador único del empleado.
     */
    public String getId() {
        return id;
    }

    /** Método que simula la invitación a un empleado a una reunión en un horario específico.
     * Muestra un mensaje indicando que el empleado ha sido invitado junto con el horario de la reunión.
     *
     * @param hora El {@link Instant} que representa el horario de la reunión.
     */
    @Override
    public void invitar(Instant hora) {
        System.out.println("Se ha invitado a empleado " + getNombreCompleto() + " a reunión citada en horario: " + Reunion.SDF.format(Date.from(hora)));
    }
}
