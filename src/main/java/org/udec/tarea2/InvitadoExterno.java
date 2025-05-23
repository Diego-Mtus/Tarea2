package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

public class InvitadoExterno implements Invitable{

    /** Variables que almacenan los datos del invitado.
     */
    private String nombre, apellidos, correo;


    /** Constructor de la clase InvitadoExterno, que inicializa los datos básicos de un invitado,
     * incluyendo su nombre, apellidos y correo electrónico.
     *
     * @param nombre El nombre del invitado externo.
     * @param apellidos Los apellidos del invitado externo.
     * @param correo El correo electrónico asociado al invitado externo.
     */
    public InvitadoExterno(String nombre, String apellidos, String correo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    /**
     * Devuelve el nombre completo del invitado concatenando nombre y apellidos.
     *
     * @return Un string que representa el nombre completo del invitado.
     */
    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    /**
     * Obtiene la dirección de correo electrónico asociada al invitado externo.
     *
     * @return Un string del correo electrónico del invitado externo.
     */
    public String getCorreo() {
        return correo;
    }

    /** Método que simula la invitación a un invitado externo a una reunión en un horario específico.
     * Muestra un mensaje indicando que el invitado ha sido invitado junto con el horario de la reunión.
     *
     * @param hora El {@link Instant} que representa el horario de la reunión.
     */
    @Override
    public void invitar(Instant hora) {
        System.out.println("Se ha invitado a externo " + getNombreCompleto() + " a reunión citada en horario: " + Reunion.SDF.format(Date.from(hora)));
    }

    /**
     * Devuelve una representación en string del objeto InvitadoExterno.
     * Incluye el nombre completo y el correo electrónico asociado.
     *
     * @return Un string con la información en el formato:
     * "Invitado externo [Nombre Completo] con el correo electrónico: [Correo].".
     */
    @Override
    public String toString() {
        return "Invitado externo " + getNombreCompleto() + " con el correo electrónico: " + getCorreo() + ".";
    }
}
