package org.udec.tarea2;

import java.util.ArrayList;
import java.util.List;

// Departamento no cuenta con interfaz de invitable, ya que se aplica a los individuos;
public class Departamento{

    private String nombre;
    private List<Empleado> empleados;

    public Departamento(String nombre){
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public int obtenerCantidadEmpleados(){
        return empleados.size();
    }

    public List<Empleado> getEmpleados(){
        return new ArrayList<>(empleados); // Retorna copia para que no sea modificable la lista original.
    }

    @Override
    public String toString() {
        StringBuilder stringAuxiliarEmpleados = new StringBuilder();
        for (Empleado e: empleados){
            stringAuxiliarEmpleados.append(e.toString()).append("\n");
        }

        return "Departamento " + this.nombre + ": \n" + stringAuxiliarEmpleados;
    }

    public void invitar() {
        System.out.println("Se ha invitado a departamento " + nombre + ":");
    }

}
