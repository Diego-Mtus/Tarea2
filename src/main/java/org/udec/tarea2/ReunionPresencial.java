package org.udec.tarea2;

import java.time.Instant;

/** Clase que representa una reunión presencial, heredando los atributos y comportamientos
 * de la clase {@link Reunion}. Este tipo de reunión se organiza en una sala asignada.
 */
public class ReunionPresencial extends Reunion{

    /** Representa la sala asignada para llevar a cabo la reunión presencial.
     */
    private String sala;


    /** Esta clase extiende de {@link Reunion}, heredando sus funcionalidades básicas y adaptándolas
     * a reuniones que requieren un lugar definido.
     */
    public ReunionPresencial(Instant horaPrevista, int minutosDeDuracion, tipoReunion tipoReunion, String sala) throws FechaReunionInvalidaException, DuracionReunionInvalidaException{
        super(horaPrevista, minutosDeDuracion, tipoReunion);
        this.sala = sala;
    }

    /**
     * Obtiene el nombre de la sala asignada para la reunión presencial.
     * @return El nombre de la sala donde se llevará a cabo la reunión presencial.
     */
    public String getSala() {
        return sala;
    }
}
