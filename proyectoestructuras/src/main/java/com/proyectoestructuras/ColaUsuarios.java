package com.proyectoestructuras;

import java.io.Serializable;

public class ColaUsuarios implements Serializable{
    private static int globalId;
    private int size = 0;
    private NodoGeneric<Usuario> inicio;
    private NodoGeneric<Usuario> fin;
    private final int id;
    private final String colaName;


    public ColaUsuarios(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.id = globalId++;
    }

    public void encolar(Usuario usuario) {
        NodoGeneric<Usuario> nuevo = new NodoGeneric<>(usuario);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            // Necesitamos encontrar el último nodo real de la cola (al deserializar se pierde el fin)
            NodoGeneric<Usuario> actual = inicio;
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
        // Si la cola está vacía, reiniciar todo
        if (inicio == null) {
            fin = null;
            size = 0;
            return;
        }
        
        // Recorrer la cola contando nodos y encontrando el último (esto es necesario porque sin esto el fin se pierde y no se puede agregar nada despues de cargar de nuevo)
        int contador = 1;
        NodoGeneric<Usuario> actual = inicio;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            contador++;
        }
        
        // Corregir el puntero fin y el tamaño
        fin = actual;
        size = contador;
    }

    public Usuario desencolar() {
        if (inicio == null) {
            return null;
        } else {
            Usuario Usuario = inicio.getDato();
            inicio = inicio.getSiguiente();
            size--;
            return Usuario;
        }

    }

    public NodoGeneric<Usuario> getInicio() {
        if (inicio == null) {
            return null;
        } else {
            return inicio;
        }
    }

    public int getSize() {
        return size;
    }

    public String getColaName() {
        return colaName;
    }

    public int getId() {
        return id;
    }

}
