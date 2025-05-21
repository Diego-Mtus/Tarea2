package org.udec.tarea2;

public class ReunionVirtual extends Reunion{

    private String enlace;

    public ReunionVirtual(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion, tipoReunion tipoReunion, String enlace) {
        super(año, mes, dia, hora, minuto, minutosDeDuracion, tipoReunion);
        this.enlace = enlace;
    }
}
