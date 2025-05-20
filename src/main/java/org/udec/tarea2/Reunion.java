package org.udec.tarea2;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;

    private Instant horaInicio;
    private Instant horaFin;
    private List<Nota> listaDeNotas;

    final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a z, dd/MM/yyyy");

    public Reunion(int año, int mes, int dia, int hora, int minuto, int minutosDeDuracion){
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
        listaDeNotas = new ArrayList<>();
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

    @Override
    public String toString() {
        if(horaPrevista == null || duracionPrevista == null){
            return "Hora inicio o duración no establecida.";
        }
        return "La reunión comenzará a las " + sdf.format(Date.from(horaPrevista)) + " y durará " + duracionPrevista.getSeconds()/60 + " minutos.";
    }

}
