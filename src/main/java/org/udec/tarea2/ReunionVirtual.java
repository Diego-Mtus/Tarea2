package org.udec.tarea2;

import java.time.Instant;

public class ReunionVirtual extends Reunion{

    private String enlace;

    public ReunionVirtual(Instant horaPrevista, int minutosDeDuracion, tipoReunion tipoReunion, String enlace) {
        super(horaPrevista, minutosDeDuracion, tipoReunion);
        this.enlace = enlace;
    }

    public String getEnlace() {
        return enlace;
    }
}
