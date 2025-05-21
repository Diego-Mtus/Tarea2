package org.udec.tarea2;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class Empleado implements Invitable{

    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    public Empleado(String id, String nombre, String apellidos, String correo) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Empleado " + getNombreCompleto() + " (ID: " + getId() + ") con el correo electrónico: " + getCorreo() + ".";
    }

    public String getId() {
        return id;
    }

    @Override
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public void invitar(Instant hora) {
        System.out.println("Se ha invitado a empleado " + getNombreCompleto() + " a reunión a citada " + Reunion.SDF.format(Date.from(hora)));
    }
}
