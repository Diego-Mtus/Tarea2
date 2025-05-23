package org.udec.tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReunionTest {

    private ReunionVirtual reunion;
    private Empleado organizador;
    private Departamento departamento;
    private Empleado empleado1, empleado2;
    private InvitadoExterno invitadoExterno1;
    private ReunionPresencial reunionPresencial;


    @BeforeEach
    void setUp() throws FechaReunionInvalidaException, DuracionReunionInvalidaException {
        organizador = new Empleado("1", "Carlos", "Vega", "carlos@empresa.com");
        empleado1 = new Empleado("2", "Ana", "Soto", "ana@empresa.com");
        empleado2 = new Empleado("3", "Luis", "Martínez", "luis@empresa.com");
        invitadoExterno1 = new InvitadoExterno("María", "Lopez", "maria@externo.com");

        departamento = new Departamento("TI");
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);


        Instant horaPrevista = Instant.now().plusSeconds(3600); // 1 hora después
        reunion = new ReunionVirtual(horaPrevista, 60, tipoReunion.TÉCNICA, organizador, "https://zoom.com/reunion");
        reunionPresencial = new ReunionPresencial(horaPrevista, 60, tipoReunion.TÉCNICA, organizador, "Sala B-3");
    }

    //Test de reunión (Todos los tests posteriores se harán tomando en cuenta sólo reunion virtual.)

    @Test
    void testCrearReunionExitosa() {
        assertNotNull(reunion);
        assertEquals("https://zoom.com/reunion", reunion.getEnlace());
    }

    @Test
    void testReunionPresencialYDespliegueOrganizador(){
        assertNotNull(reunionPresencial);
        assertEquals("Sala B-3", reunionPresencial.getSala());
        assertEquals(organizador.toString(), reunionPresencial.getNombreOrganizador());
    }

    @Test
    void testCalcularTiempoReal(){
        reunion.iniciar();
        reunion.finalizar(5);
        assertEquals(5.0f, reunion.calcularTiempoReal());
    }

    @Test
    void testCalcularTiempoRealSinIniciar(){
        assertEquals(0.0f, reunion.calcularTiempoReal());
    }

    @Test
    void testCalcularTiempoRealSinFinalizar(){
        reunion.iniciar();
        // Suponemos que tiempo real no se ha definido así que será cero.
        assertEquals(0.0f, reunion.calcularTiempoReal());
    }


    // Tests de casos de invitación individuos.

    @Test
    void testInvitarIndividuo() {
        reunion.invitarIndividuo(empleado1);
        reunion.invitarIndividuo(invitadoExterno1);

        List<Invitacion> invitaciones = reunion.getListaInvitaciones();
        assertEquals(2, invitaciones.size());
    }

    @Test
    void testInvitarIndividuoRepetido() {
        reunion.invitarIndividuo(empleado1);
        reunion.invitarIndividuo(empleado1);

        // Revisa que solo se haya invitado una vez
        assertEquals(1, reunion.getListaInvitaciones().size());
    }

    @Test
    void testInvitarIndividuoNoExiste() {
        reunion.invitarIndividuo(empleado1);
        reunion.invitarIndividuo(empleado2);
        reunion.invitarIndividuo(null);

        assertEquals(2, reunion.getListaInvitaciones().size());
    }


    // Tests de departamentos.

    @Test
    void testInvitarDepartamento() {
        reunion.invitarDepartamento(departamento);
        List<Invitacion> invitaciones = reunion.getListaInvitaciones();

        assertEquals(2, invitaciones.size());
    }

    @Test
    void testDespliegueDepartamentoString(){
        assertEquals("Departamento TI: \n" +
                "Empleado Ana Soto (ID: 2) con el correo electrónico: ana@empresa.com.\n" +
                "Empleado Luis Martínez (ID: 3) con el correo electrónico: luis@empresa.com.\n", departamento.toString());
    }

    @Test
    void testInvitarDepartamentoNoExiste() {
        reunion.invitarDepartamento(null);
        List<Invitacion> invitaciones = reunion.getListaInvitaciones();

        assertEquals(0, invitaciones.size());
    }

    @Test
    void testInvitarDepartamentoRepetido() {
        reunion.invitarDepartamento(departamento);
        reunion.invitarDepartamento(departamento);

        List<Invitacion> invitaciones = reunion.getListaInvitaciones();

        // Deberían estar sólo los 2 integrantes del departamento
        assertEquals(2, invitaciones.size());
    }

    @Test
    void testInvitarNullADepartamento() {
        Departamento departamento = new Departamento("test");
        departamento.agregarEmpleado(null);

        assertEquals(0, departamento.obtenerCantidadEmpleados());
    }

    // Tests de registro de asistencia.

    @Test
    void testRegistrarAsistencia() {
        reunion.invitarIndividuo(empleado1);
        reunion.registrarPresencia(empleado1, Instant.now());

        List<Invitable> asistencia = reunion.obtenerAsistencias();
        assertEquals(1, asistencia.size());
    }

    @Test
    void testRegistrarAsistenciaRepetida() {
        reunion.invitarIndividuo(empleado1);
        reunion.registrarPresencia(empleado1, Instant.now());
        reunion.registrarPresencia(empleado1, Instant.now());
        List<Invitable> asistencia = reunion.obtenerAsistencias();
        assertEquals(1, asistencia.size());
    }

    @Test
    void testRegistrarAsistenciaNoInvitado() {
        reunion.invitarIndividuo(empleado1);
        reunion.registrarPresencia(empleado2, Instant.now());
        List<Invitable> asistencia = reunion.obtenerAsistencias();
        assertEquals(0, asistencia.size());
    }

    @Test
    void testRegistrarAsistenciaIndividuoNoExiste() {
        reunion.registrarPresencia(null, Instant.now());
        List<Invitable> asistencia = reunion.obtenerAsistencias();
        assertEquals(0, asistencia.size());
    }

    @Test
    void testRegistrarAsistenciaHoraNull() {
        reunion.invitarIndividuo(empleado1);
        reunion.registrarPresencia(empleado1, null);
        List<Invitable> asistencia = reunion.obtenerAsistencias();
        assertEquals(0, asistencia.size());
    }

    @Test
    void testRegistrarRetraso() {
        reunion.invitarIndividuo(empleado1);

        // Registrar un retraso (llega 10 minutos tarde)
        Instant horaLlegada = reunion.getHoraPrevista().plusSeconds(600); // 10 minutos después
        reunion.registrarPresencia(empleado1, horaLlegada);

        List<Retraso> retrasos = reunion.obtenerRetrasos();
        assertEquals(1, retrasos.size());
        assertEquals(empleado1, retrasos.get(0).getInvitado());
        assertEquals(horaLlegada, retrasos.get(0).getHora());
    }

    @Test
    void testRegistrarSinRetrasos() {
        reunion.registrarPresencia(empleado1, Instant.now());
        List<Retraso> retrasos = reunion.obtenerRetrasos();
        assertEquals(0, retrasos.size());
    }

    @Test
    void testRegistrarAusencias(){
        reunion.invitarIndividuo(empleado1);
        reunion.iniciar();
        reunion.finalizar(5);
        // No se marcó presente
        List<Invitable> ausencias = reunion.obtenerAusencias();
        assertEquals(1, ausencias.size());
        assertEquals(empleado1, ausencias.get(0));
    }

    // Tests de cálculo de datos.
    void testTotalAsistencias(){
        reunion.invitarIndividuo(empleado1);
        reunion.invitarIndividuo(empleado2);
        reunion.registrarPresencia(empleado1, Instant.now());
        reunion.registrarPresencia(empleado2, Instant.now());
        assertEquals(2, reunion.obtenerTotalAsistencias());
    }

    void testPorcentajeAsistencias(){
        reunion.invitarIndividuo(empleado1);
        reunion.invitarIndividuo(empleado2);
        reunion.registrarPresencia(empleado1, Instant.now());
        reunion.iniciar();
        reunion.finalizar();
        assertEquals(50f, reunion.obtenerPorcentajeAsistencia());
    }

    void testPorcentajeAsistenciasDivisorCero(){
        reunion.iniciar();
        reunion.finalizar();
        assertEquals(0f, reunion.obtenerPorcentajeAsistencia());
    }

    // Tests de notas

    @Test
    void testAgregarNota() {
        reunion.crearNota("Primera nota", Instant.now());
        reunion.crearNota("Segunda nota", Instant.now().plusSeconds(5));

        List<Nota> notas = reunion.getListaDeNotas();
        assertEquals(2, notas.size());
    }

    @Test
    void testOrdenCronologicoDeNotas() {
        // Crear tres notas con diferentes instantes de creación
        Instant instante1 = Instant.now();
        Instant instante2 = Instant.now().plusSeconds(60); // 1 minuto después
        Instant instante3 = Instant.now().plusSeconds(120); // 2 minutos después

        // Están desordenadas en tiempo
        reunion.crearNota("Primera nota", instante2);
        reunion.crearNota("Segunda nota", instante3);
        reunion.crearNota("Tercera nota", instante1);

        // Obtener la lista de notas
        List<Nota> notas = reunion.getListaDeNotas();

        // Verificar que están ordenadas cronológicamente (verificacion mensaje).
        assertEquals("Tercera nota", notas.get(0).getContenido());
        assertEquals("Primera nota", notas.get(1).getContenido());
        assertEquals("Segunda nota", notas.get(2).getContenido());

        // Verificar que están ordenadas cronológicamente (verificacion Instant).
        assertEquals(instante1, notas.get(0).getHoraDeCreacion());
        assertEquals(instante2, notas.get(1).getHoraDeCreacion());
        assertEquals(instante3, notas.get(2).getHoraDeCreacion());
    }


    //Tests de excepciones
    @Test
    void testExcepcionHoraNoValida() {
        // Intentar crear una reunión con hora pasada y capturar la excepción
        assertThrows(FechaReunionInvalidaException.class, () -> {
            new ReunionVirtual(Instant.now().minusSeconds(3600), 60, tipoReunion.TÉCNICA, organizador, "url");
        });
    }

    @Test
    void testExcepcionDuracionNoValida() {
        // Intentar crear una reunión con duración 0 o negativa
        assertThrows(DuracionReunionInvalidaException.class, () -> {
            new ReunionVirtual(Instant.now().plusSeconds(3600), -30, tipoReunion.MARKETING, organizador, "url");
        });
    }

    @Test
    void testExcepcionOrganizadorNull() {
        assertThrows(OrganizadorInvalidoException.class, () -> {
            new ReunionVirtual(Instant.now().plusSeconds(3600), 60, tipoReunion.OTRO, null, "url");
        });
    }



}