package org.udec.tarea2;

import java.time.Instant;


/** Clase que representa una reunión virtual que extiende de la clase {@link Reunion}.
 * Además de los atributos básicos de una reunión, incluye un enlace.
 */
public class ReunionVirtual extends Reunion{

    /** Variable que almacena el enlace de la reunión virtual.
     */
    private String enlace;


    /** Esta clase extiende de {@link Reunion}, heredando sus funcionalidades básicas y adaptándolas
     * a reuniones que requieren un enlace al que conectarse.
     */
    public ReunionVirtual(Instant horaPrevista, int minutosDeDuracion, tipoReunion tipoReunion, String enlace) throws FechaReunionInvalidaException{
        super(horaPrevista, minutosDeDuracion, tipoReunion);
        this.enlace = enlace;
    }


    /** Método que obtiene el enlace de la reunión virtual.
     *
     * @return El enlace de la reunión virtual.
     */
    public String getEnlace() {
        return enlace;
    }
}
