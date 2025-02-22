package com.proyectoestructuras;

import java.io.Serializable;

public class NodoGeneric<T> implements Serializable{
    private T dato;
    private NodoGeneric<T> siguiente;

    public NodoGeneric(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

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
