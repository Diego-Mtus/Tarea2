package org.udec.tarea2;

public class ReunionPresencial extends Reunion{

    private String sala;

    public ReunionPresencial(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion, tipoReunion tipoReunion, String sala) {
        super(año, mes, dia, hora, minuto, minutosDeDuracion, tipoReunion);
        this.sala = sala;
    }
}
