package com.proyectoestructuras;

import java.io.Serializable;

public class ColaGenerales implements Serializable{
    private static int globalId;
    private int size = 0;
    private NodoGeneric<Cola> inicio;
    private NodoGeneric<Cola> fin;
    private final int id;
    private final String colaName;

    public ColaGenerales(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.id = globalId++;
    }

    public boolean estaVacia() {
        return inicio == null;
    }   

    public void encolar(Cola cola) {
        NodoGeneric<Cola> nuevo = new NodoGeneric<>(cola);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            // Necesitamos encontrar el último nodo real de la cola (al deserializar se pierde el fin)
            NodoGeneric<Cola> actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // Conectar el nuevo nodo al final real de la cola para poder seguir con el nodo
            actual.setSiguiente(nuevo);
            fin = nuevo;
        }
        size++;
    }
    
    public void verificarIntegridad() {
        if (inicio == null) {
            fin = null;
            size = 0;
            return;
        }
        // Recorrer la cola contando nodos y encontrando el último (esto es necesario porque sin esto el fin se pierde y no se puede agregar nada despues de cargar de nuevo)
        int contador = 1;
        NodoGeneric<Cola> actual = inicio;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            contador++;
        }
        
        // Asegurarse que el fin apunte al último nodo
        fin = actual;
        // Actualizar el tamaño real
        size = contador;
    }

    public Cola desencolar() {
        if (inicio == null) {
            return null;
        } else {
            Cola cola = inicio.getDato();
            inicio = inicio.getSiguiente();
            size--;
            return cola;
        }

    }

    public Cola obtenerCola(int id){
        if(id < 0 || id >= size){
            throw new IndexOutOfBoundsException("El id no es valido");
        }
        NodoGeneric<Cola> actual = inicio;

        for (int i = 0; i < id; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public Cola colaMasDisponible() {
        Cola colaMasDisponible = null;
        int min = Integer.MAX_VALUE;
        NodoGeneric<Cola> actual = inicio;
        while (actual != null) {
            Cola cola = actual.getDato();
            if (cola.getSize() < min) {
                min = cola.getSize();
                colaMasDisponible = cola;
            }
            actual = actual.getSiguiente();
        }
        return colaMasDisponible;
    }

    public int getSize() {
        return size;
    }

    public Cola get(int id) {
        if (id < 0 || id >= size) {
            throw new IndexOutOfBoundsException("El id no es valido");
        }
        NodoGeneric<Cola> actual = inicio;
        for (int i = 0; i < id; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public NodoGeneric<Cola> getInicio() {
        return inicio;
    }


}
