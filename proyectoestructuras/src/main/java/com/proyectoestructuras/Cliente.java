package com.proyectoestructuras;

import java.io.Serializable;
/**
 * Clase que representa un cliente
 * Se utiliza para almacenar los datos de un cliente
 */
public class Cliente implements Serializable{

    // Atributos de la clase

    private static int globalID = 0;
    private final int id;

    private String nombre;
    private int Edad;

    /**
     * Constructor de la clase Cliente
     * @param nombre nombre del cliente
     * @param Edad edad del cliente
     * 
     * Se inicia el id con el globalID y se incrementa en 1
     */
    public Cliente(String nombre, int Edad) {
        this.nombre = nombre;
        this.Edad = Edad;
        this.id = Cliente.globalID++;
    }

    // Getters y Setters de los atributos de la clase
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

    // Metodo toString de la clase Cliente se espera estilizar en un futuro
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Edad='" + Edad + '\'' +
                '}';
    }
    


}
