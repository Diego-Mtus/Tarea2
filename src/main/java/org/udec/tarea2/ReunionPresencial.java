package org.udec.tarea2;

public class ReunionPresencial extends Reunion{

    private String sala;

    public ReunionPresencial(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion, Empleado organizador, String sala) {
        super(año, mes, dia, hora, minuto, minutosDeDuracion, organizador);
        this.sala = sala;
    }
}
