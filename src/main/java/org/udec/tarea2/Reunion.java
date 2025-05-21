package org.udec.tarea2;

import org.junit.jupiter.api.parallel.ExecutionMode;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class Reunion {
    protected Date fecha;
    protected Instant horaPrevista;
    protected Duration duracionPrevista;

    protected Instant horaInicio;
    protected Instant horaFin;

    protected List<Nota> listaDeNotas = new ArrayList<>();;
    protected Empleado organizador;

    // Por la posible existencia de varias reuniones, se debe comprobar dentro de cada reunion si el individuo fue invitado o no.
    protected List<Invitacion> listaInvitaciones = new ArrayList<>();
    protected Map<Invitable, Asistencia> asistentes = new HashMap<>();

    final static SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss a z, dd/MM/yyyy");

    public Reunion(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion, Empleado organizador){
        long actual = System.currentTimeMillis();

        Date fechaAuxiliar = new Date(año-1900, mes - 1, dia, hora, minuto);
        Instant horaInicioAux = Instant.ofEpochMilli(fechaAuxiliar.getTime());


        if(horaInicioAux.toEpochMilli() < actual || minutosDeDuracion <= 0){
            // Se tira exception FechaReunionInvalidaException
            System.out.println("ERROR");
        }

        this.fecha = fechaAuxiliar;
        this.horaPrevista = horaInicioAux;
        this.duracionPrevista = Duration.of(minutosDeDuracion, ChronoUnit.MINUTES);
        this.organizador = organizador;
    }

    public Instant getHoraPrevista(){
        return this.horaPrevista;
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }

    public Instant getHoraFin() {
        return horaFin;
    }

    public void iniciar(){
        this.horaInicio = Instant.now();
    }

    public void finalizar(){
        this.horaFin = Instant.now();
    }

    // Overload por si se quiere establecer un fin de reunion en intervalo mayor de tiempo para motivos de testeo.
    public void finalizar(int minutosDesdeAhora){
        this.horaFin = Instant.now().plus(minutosDesdeAhora, ChronoUnit.MINUTES);
    }

    public float calcularTiempoReal(){
        if (horaInicio != null && horaFin != null) {
            return Duration.between(horaInicio, horaFin).toMinutes();
        }
        return 0;
    }


    // Sección de gestion de Notas.

    public void crearNota(String contenido){
        listaDeNotas.add(new Nota(contenido));
    }

    public String getListaDeNotas(){
        StringBuilder stringListaDeNotas = new StringBuilder();
        for (Nota n : this.listaDeNotas){
            stringListaDeNotas.append(n.getContenido()).append("\n");
        }
        return "" + stringListaDeNotas;
    }

    // Sección de invitaciones.

    private void invitarReunion(Invitable invitado){
        // Revista la lista de invitaciones por si ya está el invitado.
        boolean yaInvitado = listaInvitaciones.stream().anyMatch(i -> i.getInvitado().equals(invitado));
        if (!yaInvitado){
            listaInvitaciones.add(new Invitacion(invitado, this));
        } else{
            System.out.println(invitado.getNombreCompleto() + " ya ha sido invitado anteriormente.");
        }
    }

    public void invitarIndividuo(Invitable invitado){
        invitarReunion(invitado);
    }

    public void invitarDepartamento(Departamento departamento){
        for(Empleado e: departamento.getEmpleados()){
            invitarReunion(e);
        }
    }


    @Override
    public String toString() {
        if(horaPrevista == null || duracionPrevista == null){
            return "Hora inicio o duración no establecida.";
        }
        return "La reunión comienza a las " + SDF.format(Date.from(horaPrevista)) + " y dura " + duracionPrevista.getSeconds()/60 + " minutos.";
    }

}
