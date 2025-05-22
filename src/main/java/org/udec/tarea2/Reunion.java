package org.udec.tarea2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/** Clase que representa una reunión, la cual puede ser de diferentes tipos
 * como {@link ReunionPresencial} o {@link ReunionVirtual}. La reunión tiene
 * un horario previsto de inicio y duración, y puede gestionarse desde las
 * invitaciones hasta las asistencias de los participantes.
 *
 * Se permite el registro de notas, así como obtener informes completos
 * sobre la reunión, asistentes, ausencias y retrasos.
 */
public abstract class Reunion {


    /** Representa la hora prevista para el inicio de la reunión.
     * Se utiliza para gestionar retrasos.
     */
    private Instant horaPrevista;

    /** Variable que representa la duración prevista de la reunión.
     */
    private Duration duracionPrevista;

    /** Hora exacta en la que inicia la reunión.
     * Se establece cuando el método {@code iniciar()} es invocado.
     * Permite calcular la duración real de la reunión.
     */
    private Instant horaInicio;

    /**
     * Variable que representa la hora de finalización real de una reunión.
     * Se establece cuando el método {@code finalizar()} es invocado.
     * <p>
     * Este instante puede ser utilizado para calcular la duración real de la reunión, verificar retrasos y ausencias.
     */
    private Instant horaFin;

    /**
     * Lista que almacena las notas asociadas a la reunión.
     * Cada nota representa información relevante o decisiones tomadas durante la reunión.
     * Las notas son de tipo {@link Nota}, que encapsulan el contenido textual de cada nota.
     *
     * Esta lista puede ser actualizada mediante el método {@code crearNota()} para agregar nuevas notas
     * a lo largo del desarrollo de la reunión.
     */
    private List<Nota> listaDeNotas = new ArrayList<>();;

    /**
    *   Variable que representa al organizador de la reunión, para poder desplegarlo al momento de mostrar informe.
     */
    private Empleado organizador;

    /** Enum que especifica el tipo de reunión.
     * <p>
     * TÉCNICA, MARKETING, u OTRO.
     * </p>
     */
    private tipoReunion tipoReunion;

    /** Lista de invitaciones enviadas a los diversos individuos para una reunión específica.

     * <p></p>Este registro permite verificar si un individuo específico está invitado y es fundamental
     *  para gestionar la asistencia y los retrasos, asegurando que solo los invitados sean considerados
     *  en el contexto de la reunión.
     */

    private List<Invitacion> listaInvitaciones = new ArrayList<>();

    /**
     * Mapa que relaciona a cada invitado de la reunión con su correspondiente {@link Asistencia}.
     * Este mapa registra si los participantes asistieron o no a la reunión, junto con la hora de llegada
     * y su estado de presencia.
     *
     * Cada clave del mapa es un objeto que implementa la interfaz {@link Invitable}, representando al invitado,
     * y cada valor es una instancia de {@link Asistencia} asociada a dicho invitado.
     */
    private Map<Invitable, Asistencia> asistentes = new HashMap<>();

    /** Lista que almacena los retrasos registrados en una reunión.
     * Cada elemento de la lista es un {@link Retraso}, que representa
     * un invitado que llegó tarde junto con la hora de su llegada.
     */
    private List<Retraso> retrasos = new ArrayList<>();

    /** Constante estática que define un formato estándar de fecha y hora.
     */
    final static SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss a z, dd/MM/yyyy");

    /** Constante estática que define un formato estándar de hora.
     */
    final static SimpleDateFormat SDF_HORA = new SimpleDateFormat("hh:mm:ss a z");

    /**
     * Clase que representa una reunión con una hora prevista, duración prevista y un tipo específico.
     * La reunión valida que la hora prevista sea posterior a la actual y que la duración sea mayor a cero.
     *
     * @param horaPrevista La hora específica en la que se espera que comience la reunión.
     * @param minutosDeDuracion La duración estimada de la reunión en minutos.
     * @param tipoReunion El tipo de reunión, definido por el enumerador {@link tipoReunion}.
     * @param organizador El empleado que organiza la reunión.
     *
     * @throws FechaReunionInvalidaException Si la hora prevista es anterior a la actual o si la duración es no positiva.
     */
    public Reunion(Instant horaPrevista, int minutosDeDuracion, tipoReunion tipoReunion, Empleado organizador) throws FechaReunionInvalidaException, DuracionReunionInvalidaException, OrganizadorInvalidoException{
        long actual = System.currentTimeMillis();

        if(horaPrevista.toEpochMilli() < actual){
            throw new FechaReunionInvalidaException();
        }
        if(minutosDeDuracion <= 0){
            throw new DuracionReunionInvalidaException();
        }
        if(organizador == null){
            throw new OrganizadorInvalidoException();
        }

        this.organizador = organizador;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = Duration.of(minutosDeDuracion, ChronoUnit.MINUTES);
        this.tipoReunion = tipoReunion;
    }

    /**
     * Método que obtiene la hora prevista de inicio de la reunión.
     * @return La hora prevista de inicio de la reunión como un objeto {@link Instant}.
     */
    public Instant getHoraPrevista(){
        return this.horaPrevista;
    }

    /** Método que obtiene datos del organizador de reunión.
     *
     * @return Los datos del organizador de la reunión como string.
     */
    public String getNombreOrganizador(){
        return organizador.toString();
    }

    /**
     * Calcula el tiempo real transcurrido en minutos entre el inicio y el fin de una reunión.
     * Si no se han definido las horas de inicio y fin, se retorna 0.
     *
     * @return El tiempo transcurrido en minutos entre horaInicio y horaFin. Si alguna de las dos es null, retorna 0.
     */
    public float calcularTiempoReal(){
        if (horaInicio != null && horaFin != null) {
            return Duration.between(horaInicio, horaFin).toMinutes();
        }
        return 0;
    }


    // Sección de gestion de Notas.

    /**
     * Método que crea una nueva nota y la agrega a la lista de notas de la reunión.
     *
     * @param contenido El contenido de la nota a crear.
     */
    public void crearNota(String contenido, Instant horaDeNota){
        listaDeNotas.add(new Nota(contenido, horaDeNota));
    }

    /**
     * Método que devuelve una lista con todas las notas relacionadas a la reunión ordenadas cronológicamente.
     *
     * @return Una copia de la lista que contiene las notas de reunión ordenadas cronológicamente.
     */
    public List<Nota> getListaDeNotas(){
        ArrayList<Nota> listaOrdenadaAux = new ArrayList<>(listaDeNotas);
        listaOrdenadaAux.sort(Nota::compareTo);
        return listaOrdenadaAux;
    }

    // Sección de invitaciones.

    /**
     * Método que invita a un {@link Invitable} a una reunión.
     * Si el invitado ya ha sido invitado previamente, no se lo volverá a agregar
     * y se informará por consola que ya está invitado.
     *
     * @param invitado La instancia de {@link Invitable} que será invitada a la reunión.
     */
    private void invitarReunion(Invitable invitado){
        // Revista la lista de invitaciones por si ya está el invitado.
        try {
            boolean yaInvitado = listaInvitaciones.stream().anyMatch(i -> i.getInvitado().equals(invitado));
            if (!yaInvitado) {
                listaInvitaciones.add(new Invitacion(invitado, this));
            } else {
                System.out.println(invitado.getNombreCompleto() + " ya ha sido invitado anteriormente.");
            }
        } catch (NullPointerException e){
            System.out.println("Error: El invitado no puede ser nulo.");
        }
    }

    /**
     * Método que realiza la invitación de un individuo a la reunión.
     * Este método permite invitar a un individuo que implemente la interfaz {@link Invitable}.
     * Llama internamente al método privado invitarReunion para gestionar la invitación.
     *
     * @param invitado El individuo a ser invitado a la reunión.
     */
    public void invitarIndividuo(Invitable invitado){
        invitarReunion(invitado);
    }

    /**
     * Método que invita a todos los miembros de un departamento a una reunión.
     * Invita a cada uno de los empleados que lo conforman.
     *
     * @param departamento El {@link Departamento} cuyos empleados serán invitados a la reunión.
     */
    public void invitarDepartamento(Departamento departamento){
        departamento.invitar();
        for(Empleado e: departamento.getEmpleados()){
            invitarReunion(e);
        }
    }

    // Sección de gestión de asistencia

    /** Método que registra la presencia de un invitado en la reunión.
     * Si el invitado está en la lista de invitaciones, se marca su asistencia y se registra la hora de llegada
     * .
     * Además, si la hora de llegada es posterior a la hora prevista para la reunión, se añade a la lista de retrasos.
     * Si el invitado no estaba invitado previamente, se avisará en consola.
     *
     * @param invitado El {@link Invitable} que intenta registrarse en la reunión.
     * @param hora La hora en que el invitado intenta registrarse.
     */
    public void registrarPresencia(Invitable invitado, Instant hora){
        if(invitado == null){
            // No hace nada
        }
        else if(listaInvitaciones.stream().anyMatch(i -> i.getInvitado().equals(invitado))) {
            asistentes.put(invitado, new Asistencia(invitado, true, hora));
            if (hora.isAfter(horaPrevista)) {
                retrasos.add(new Retraso(invitado, hora));
            }
        } else {
            System.out.println(invitado.getNombreCompleto() + " ha intentado unirse, pero no ha sido invitado/a previamente.");
        }
    }

    // Sección de inicio y fin de reunión
    /** Método que registra el inicio de la reunión.
     * Se almacena la hora actual como la hora de inicio de la reunión.
     */

    public void iniciar(){
        this.horaInicio = Instant.now();
    }

    /** Método que finaliza la reunión registrando el momento de término
     * y asegurando que todos los invitados estén registrados adecuadamente en las asistencias.
     *
     * La hora de finalización se asigna como el instante actual. También,
     * todos los invitados que no marcaron presencia antes o durante la reunión serán registrados como ausentes.
     */
    public void finalizar(){
        this.horaFin = Instant.now();
        for(Invitacion inv: listaInvitaciones){
            // Si está invitado pero no se marcó presente antes o durante el transcurso de la reunión
            if(!asistentes.containsKey(inv.getInvitado())){
                asistentes.put(inv.getInvitado(), new Asistencia(inv.getInvitado(), false, null));
            }
        }
    }

    /** Método que establece la finalización de una reunión basada en un intervalo de tiempo desde
     *  el momento actual.
     * También asigna estado de ausencia a aquellos invitados que no se registraron
     *  como presentes durante la reunión.
     *
     * @param minutosDesdeAhora Cantidad de minutos desde el instante actual en que
     *  debe finalizar la reunión.
     */
    // Overload por si se quiere establecer un fin de reunion en intervalo mayor de tiempo para motivos de testeo.
    public void finalizar(int minutosDesdeAhora){
        this.horaFin = Instant.now().plus(minutosDesdeAhora, ChronoUnit.MINUTES);
        for(Invitacion inv: listaInvitaciones){
            // Si está invitado pero no se marcó presente antes o durante el transcurso de la reunión
            if(!asistentes.containsKey(inv.getInvitado())){
                asistentes.put(inv.getInvitado(), new Asistencia(inv.getInvitado(), false, null));
            }
        }
    }

    // Sección de obtener datos.

    /**
     * Método que obtiene una lista de los asistentes que se encuentran presentes en la reunión.
     * Recorre la lista de asistentes y, si el asistente está marcado como presente, lo agrega a la lista de asistencia.
     *
     * @return Una lista de objetos {@link Invitable} que representan a los asistentes presentes en la reunión.
     */
    public List<Invitable> obtenerAsistencias(){
        List<Invitable> listaAsistencia = new ArrayList<>();
        for (var e: asistentes.entrySet()){
            if(e.getValue().isPresente()){
                listaAsistencia.add(e.getKey());
            }
        }
        return listaAsistencia;
    }

    /**
     * Método que obtiene la lista de retrasos registrados en la reunión.
     * La lista contiene información sobre los individuos que llegaron tarde
     * y la hora en la que se registró su retraso.
     *
     * @return Lista de objetos {@link Retraso} que representa los retrasos
     * registrados en la reunión.
     */
    public List<Retraso> obtenerRetrasos(){
        return new ArrayList<>(retrasos);
    }

    /**
     * Método que obtiene la lista de invitados ausentes en la reunión.
     * Se consideran ausentes aquellos que no han sido marcados como presentes en la lista de asistentes.
     *
     * @return Una lista de objetos de tipo {@link Invitable} que representan a los invitados que no asistieron a la reunión.
     */
    public List<Invitable> obtenerAusencias(){
        List<Invitable> listaAusencias = new ArrayList<>();
        for (var e: asistentes.entrySet()){
            if(!e.getValue().isPresente()){
                listaAusencias.add(e.getKey());
            }
        }
        return listaAusencias;
    }

    /**
     * Método que calcula y devuelve el total de asistencias registradas en una reunión.
     * Este total se obtiene contando los elementos presentes en la lista de asistencias.
     *
     * @return El número total de asistencias registradas.
     */
    public int obtenerTotalAsistencias(){
        return obtenerAsistencias().size();
    }

    /**
     * Calcula el porcentaje de asistencia a la reunión considerando la cantidad de presentes
     * en comparación con el total de invitados.
     *
     * @return El porcentaje de asistencia a la reunión como un valor flotante.
     */
    public float obtenerPorcentajeAsistencia(){
        if(!listaInvitaciones.isEmpty()) {
            return (float) obtenerAsistencias().size() / listaInvitaciones.size() * 100;
        } else{
            return 0;
        }
    }


    /**
     * Método que genera un informe detallado de una reunión, incluyendo tipo de reunión,
     * lugar o enlace, lista de invitados, tiempos de inicio y fin real, duración,
     * y participación.
     *
     * @param path Ruta del archivo donde se almacenará el informe generado.
     * @throws IOException Si ocurre un error durante la escritura en el archivo.
     */
    public void generarInforme(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("=== Informe de reunión ===\n\n");
        writer.write("Tipo de reunión: " + tipoReunion + "\n");
        writer.write(this + "\n");
        writer.write("Organizado por: " + this.getNombreOrganizador() + "\n");
        if (this instanceof ReunionPresencial) {
            writer.write("Sala: " + ((ReunionPresencial) this).getSala() + "\n");
        } else if (this instanceof ReunionVirtual){
            writer.write("Enlace: " + ((ReunionVirtual) this).getEnlace() + "\n");
        }

        writer.write("\n--- Invitados ---\n");
        for(Invitacion i : listaInvitaciones){
            writer.write(i.getInvitado().toString() + "\n");
        }

        if(horaInicio != null && horaFin != null){
            writer.write("\nHora de inicio real: " + SDF_HORA.format(Date.from(horaInicio)) + "\n");
            writer.write("Hora de fin real: " + SDF_HORA.format(Date.from(horaFin)) + "\n");
            writer.write("Duración real: " + calcularTiempoReal() + " minutos. \n");

            writer.write("\n--- Participantes ---\n");
            List<Invitable> asistenciasInforme = obtenerAsistencias();
            List<Retraso> retrasosInforme = obtenerRetrasos();
            List<Invitable> ausenciasInforme = obtenerAusencias();
            writer.write("Total de asistencia: " + obtenerTotalAsistencias() + "\n");
            writer.write("Porcentaje de asistencia: " + obtenerPorcentajeAsistencia() + "%\n");

            writer.write("\n- Presentes -\n");
            for(Invitable invitado : asistenciasInforme){
                writer.write(invitado.getNombreCompleto() + "\n");
            }
            writer.write("\n- Atrasos -\n");
            for(Retraso invitado : retrasosInforme){
                writer.write(invitado.toString() + "\n");
            }
            writer.write("\n- Ausentes -\n");
            for(Invitable invitado: ausenciasInforme){
                writer.write(invitado.getNombreCompleto() + "\n");
            }



            writer.write("\n--- Notas de la reunión ---\n");
            for(Nota n: this.getListaDeNotas()){
                writer.write(n.toString() + "\n");
            }

            writer.write("=========================\n");
        }

        writer.close();
        System.out.println("Informe generado correctamente en: " + path);
    }

    /**
     * Método que proporciona un String del estado de la reunión
     *
     * @return Una cadena de texto que indica la hora de inicio prevista y la duración de la reunión en minutos,
     * o un mensaje indicando que la hora de inicio o la duración no están definidas.
     */
    @Override
    public String toString() {
        if(horaPrevista == null || duracionPrevista == null){
            return "Hora inicio o duración no establecida.";
        }
        return "La reunión comienza a las " + SDF.format(Date.from(horaPrevista)) + " y dura " + duracionPrevista.getSeconds()/60 + " minutos.";
    }

}
