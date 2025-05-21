package org.udec.tarea2;

public class Nota {
    private String contenido;

    public Nota(String contenido){
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return contenido;
    }
}
