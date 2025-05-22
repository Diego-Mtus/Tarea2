package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

public class Nota implements Comparable<Nota>{
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

    public Instant getHoraDeCreacion() {
        return horaDeCreacion;
    }

    @Override
    public int compareTo(Nota nota2) {
        if(this.getHoraDeCreacion().isBefore(nota2.getHoraDeCreacion())){
            return -1;
        } else if (this.getHoraDeCreacion() == nota2.getHoraDeCreacion()){
            return 0;
        } else{
            return 1;
        }
    }
}
