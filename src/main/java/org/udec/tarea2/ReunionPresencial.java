package org.udec.tarea2;

import java.time.Instant;

public class ReunionPresencial extends Reunion{

    private String sala;

    public ReunionPresencial(Instant horaPrevista, int minutosDeDuracion, tipoReunion tipoReunion, String sala) {
        super(horaPrevista, minutosDeDuracion, tipoReunion);
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }
}
