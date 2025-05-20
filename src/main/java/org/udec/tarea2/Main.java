package org.udec.tarea2;

public class Main {
    public static void main(String[] args) {

        Reunion test = new ReunionVirtual(2025, 5, 20, 15, 2, 25);
        System.out.println(test);

        Empleado empleado1 = new Empleado("Snake", "Metal Gear", "John", "bigboss@foxhound.org");
        System.out.println(empleado1);
    }
}