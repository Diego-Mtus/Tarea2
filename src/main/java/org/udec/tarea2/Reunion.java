package org.udec.tarea2;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;

    private Instant horaInicio;
    private Instant horaFin;

    private List<Nota> listaDeNotas = new ArrayList<>();;
    private tipoReunion tipoReunion;

    // Por la posible existencia de varias reuniones, se debe comprobar dentro de cada reunion si el individuo fue invitado o no.
    private List<Invitacion> listaInvitaciones = new ArrayList<>();
    private Map<Invitable, Asistencia> asistentes = new HashMap<>();
    private List<Retraso> retrasos = new ArrayList<>();

    final static SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss a z, dd/MM/yyyy");
    final static SimpleDateFormat SDF_HORA = new SimpleDateFormat("hh:mm:ss a z");

    public Reunion(Instant horaPrevista, int minutosDeDuracion, tipoReunion tipoReunion){
        long actual = System.currentTimeMillis();

        Date fechaAuxiliar = Date.from(horaPrevista);


        if(horaPrevista.toEpochMilli() < actual || minutosDeDuracion <= 0){
            // Se tira exception FechaReunionInvalidaException
            System.out.println("ERROR");
        }

        this.fecha = fechaAuxiliar;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = Duration.of(minutosDeDuracion, ChronoUnit.MINUTES);
        this.tipoReunion = tipoReunion;
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
            stringListaDeNotas.append(n).append("\n");
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
        departamento.invitar();
        for(Empleado e: departamento.getEmpleados()){
            invitarReunion(e);
        }
    }

    // Sección de gestión de asistencia

    public void registrarPresencia(Invitable invitado, Instant hora){
        asistentes.put(invitado, new Asistencia(invitado, true, hora));
        if(hora.isAfter(horaPrevista)){
            retrasos.add(new Retraso(invitado, hora));
        }
    }

    // Sección de inicio y fin de reunión
    public void iniciar(){
        this.horaInicio = Instant.now();
    }

    public void finalizar(){
        this.horaFin = Instant.now();
        for(Invitacion inv: listaInvitaciones){
            // Si está invitado pero no se marcó presente antes o durante el transcurso de la reunión
            if(!asistentes.containsKey(inv.getInvitado())){
                asistentes.put(inv.getInvitado(), new Asistencia(inv.getInvitado(), false, null));
            }
        }
    }

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

    public List<Invitable> obtenerAsistencias(){
        List<Invitable> listaAsistencia = new ArrayList<>();
        for (var e: asistentes.entrySet()){
            if(e.getValue().isPresente()){
                listaAsistencia.add(e.getKey());
            }
        }
        return listaAsistencia;
    }

    public List<Retraso> obtenerRetrasos(){
        return new ArrayList<>(retrasos);
    }

    public List<Invitable> obtenerAusencias(){
        List<Invitable> listaAusencias = new ArrayList<>();
        for (var e: asistentes.entrySet()){
            if(!e.getValue().isPresente()){
                listaAusencias.add(e.getKey());
            }
        }
        return listaAusencias;
    }

    public int obtenerTotalAsistencias(){
        return obtenerAsistencias().size();
    }

    public float obtenerPorcentajeAsistencia(){
        return (float) obtenerAsistencias().size() / listaInvitaciones.size() * 100;
    }

    public void generarInforme(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("=== Informe de reunión ===\n\n");
        writer.write("Tipo de reunión: " + tipoReunion + "\n");
        writer.write(this.toString() + "\n");
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
            writer.write(getListaDeNotas());

            writer.write("=========================\n");
        }

        writer.close();
        System.out.println("Informe generado correctamente en: " + path);
    }

    @Override
    public String toString() {
        if(horaPrevista == null || duracionPrevista == null){
            return "Hora inicio o duración no establecida.";
        }
        return "La reunión comienza a las " + SDF.format(Date.from(horaPrevista)) + " y dura " + duracionPrevista.getSeconds()/60 + " minutos.";
    }

}
