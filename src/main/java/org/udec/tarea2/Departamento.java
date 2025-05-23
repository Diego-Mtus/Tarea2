package org.udec.tarea2;

import java.util.ArrayList;
import java.util.List;


/** Clase que representa un Departamento que agrupa varios empleados {@link Empleado}.
 * Permite gestionar empleados y realizar invitaciones de manera colectiva.
 */
public class Departamento{

    /** Variable que representa el nombre del departamento.
     */
    private String nombre;

    /** Esta lista almacena las instancias de {@link Empleado} que forman parte del departamento.
     */
    private List<Empleado> empleados;

    /** Constructor que inicializa un departamento con un nombre específico.
     *
     * @param nombre El nombre del departamento.
     */
    public Departamento(String nombre){
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    /** Método que agrega un empleado a la lista de empleados del departamento.
     * Verifica que el empleado no sea nulo.
     *
     * @param empleado {@link Empleado} que se desea agregar al departamento.
     */
    public void agregarEmpleado(Empleado empleado) {
        if(empleado !=null) {
            empleados.add(empleado);
        }
    }

    /**
     * Método que retorna la cantidad de empleados que forman parte del departamento.
     *
     * @return Número total de empleados en el departamento.
     */
    public int obtenerCantidadEmpleados(){
        return empleados.size();
    }

    /**
     * Método que retorna una copia de la lista de empleados pertenecientes al departamento.
     *
     * @return Lista de empleados registrados en el departamento.
     */
    public List<Empleado> getEmpleados(){
        return new ArrayList<>(empleados); // Retorna copia para que no sea modificable la lista original.
    }

    /**
     * Método que retorna una representación en string del Departamento,
     * incluyendo su nombre y la lista de empleados que contiene.
     * Cada empleado es listado en una línea separada.
     *
     * @return Cadena de texto con el nombre del departamento y los detalles de cada empleado.
     */
    @Override
    public String toString() {
        StringBuilder stringAuxiliarEmpleados = new StringBuilder();
        for (Empleado e: empleados){
                stringAuxiliarEmpleados.append(e.toString()).append("\n");
        }

        return "Departamento " + this.nombre + ": \n" + stringAuxiliarEmpleados;
    }

    /**
     * Método que simula la acción de invitar al departamento a la reunión.
     */
    public void invitar() {
        System.out.println("Se ha invitado a departamento " + nombre + ":");
    }

}
