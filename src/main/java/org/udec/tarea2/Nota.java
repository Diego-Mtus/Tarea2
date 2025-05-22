package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

public class Nota {
    private String contenido;
    private Instant horaDeCreacion;

    public Nota(String contenido, Instant horaDeCreacion){
        this.contenido = contenido;
        this.horaDeCreacion = horaDeCreacion;
    }

    @Override
    public String toString() {

        return "Nota creada a las " + Reunion.SDF_HORA.format(Date.from(horaDeCreacion)) + ": " + contenido + ".";
    }
}
