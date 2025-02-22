package com.proyectoestructuras;

import java.io.Serializable;

public class Cliente implements Serializable{

    private static int globalID = 0;
    private final int id;

    private String nombre;
    private int Edad;

    public Cliente(String nombre, int Edad) {
        this.nombre = nombre;
        this.Edad = Edad;
        this.id = Cliente.globalID++;
    }

    public static int getGlobalID() {
        return globalID;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Edad='" + Edad + '\'' +
                '}';
    }
    


}
