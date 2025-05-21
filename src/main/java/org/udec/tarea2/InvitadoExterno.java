package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

public class InvitadoExterno implements Invitable{

    private String nombre, apellidos, correo;
    private boolean yaInvitado = false;

    public InvitadoExterno(String nombre, String apellidos, String correo){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean isInvitado() {
        return yaInvitado;
    }

    @Override
    public void invitar(Instant hora) {
        System.out.println("Se ha invitado a externo " + getNombreCompleto() + " a reunión a citada " + Reunion.SDF.format(Date.from(hora)));
    }

    @Override
    public String toString() {
        return "Invitado externo " + getNombreCompleto() + " con el correo electrónico: " + getCorreo() + ".";
    }
}
