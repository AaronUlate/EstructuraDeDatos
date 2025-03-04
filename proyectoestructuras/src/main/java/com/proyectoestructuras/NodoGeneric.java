package com.proyectoestructuras;

import java.io.Serializable;

/**
 * Clase que representa un nodo generico que puede almacenar cualquier tipo de dato
 * @param <T> tipo de dato que se va a almacenar en el nodo
 * 
 * Se utiliza de forma generica para poder almacenar cualquier tipo de dato en una lista enlazada
 */

public class NodoGeneric<T> implements Serializable{

    // Atributos de la clase
    private T dato;
    private NodoGeneric<T> siguiente;

    /**
     * Constructor de la clase NodoGeneric
     * @param dato dato que se va a almacenar en el nodo
     * 
     */
    public NodoGeneric(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getters y Setters de los atributos de la clase
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoGeneric<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGeneric<T> siguiente) {
        this.siguiente = siguiente;
    }
    

}
