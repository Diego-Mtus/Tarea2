package org.udec.tarea2;

import java.time.Instant;
import java.util.Date;

/**
 * Clase que representa una nota con un contenido específico y un instante de tiempo de creación.
 * Una nota se puede comparar con otra basándose en el momento que se creó.
 */
public class Nota implements Comparable<Nota> {
    /** Contenido textual de la nota.
     */
    private String contenido;

    /** Variable que representa el instante exacto en el que la nota fue creada.
     */
    private Instant horaDeCreacion;

    /**
     * Constructor que crea una {@link Nota} con su contenido y la hora de creación.
     *
     * @param contenido El contenido textual de la nota.
     * @param horaDeCreacion El instante exacto en el que se creó la nota.
     */
    public Nota(String contenido, Instant horaDeCreacion) {
        this.contenido = contenido;
        this.horaDeCreacion = horaDeCreacion;
    }

    /** Devuelve una representación en string de la nota.
     * La representación incluye la hora en la que fue creada y el contenido textual de la nota.
     *
     * @return Un string que describe la nota con su hora de creación y contenido.
     */
    @Override
    public String toString() {
        return "Nota creada a las " + Reunion.SDF_HORA.format(Date.from(horaDeCreacion)) + ": " + contenido + ".";
    }

    /** Obtiene el instante exacto en el que fue creada la nota.
     *
     * @return El instante de creación de la nota.
     */
    public Instant getHoraDeCreacion() {
        return horaDeCreacion;
    }

    /**
     * Método que proporciona el contenido textual de la nota.
     *
     * @return El contenido de la nota en un string.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Compara esta instancia de {@link Nota} con otra instancia proporcionada, basándose en el instante de creación.
     *
     * @param nota2 La {@link Nota} con la cual se desea comparar esta instancia.
     * @return -1 si esta nota fue creada antes que la nota proporcionada;
     *          0 si ambas notas fueron creadas al mismo tiempo;
     *          1 si esta nota fue creada después que la nota proporcionada.
     */
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
