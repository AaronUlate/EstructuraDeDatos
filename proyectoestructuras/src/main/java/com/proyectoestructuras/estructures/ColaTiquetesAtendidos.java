package com.proyectoestructuras.estructures;

import java.io.Serializable;

import com.proyectoestructuras.models.TiqueteAtentido;

public class ColaTiquetesAtendidos implements Serializable{
    // Atributos de la clase
    private NodoGeneric<TiqueteAtentido> inicio;
    private NodoGeneric<TiqueteAtentido> fin;
    private int size;
    private String colaName;

    /**
     * Constructor de la clase ColaTiquetesAtendidos
     * Se inicializan los atributos de la clase
     */
    public ColaTiquetesAtendidos(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    /**
     * Metodo para encolar un tiquete atendido
     * @param tiqueteAtentido
     * 
     * Se crea un nuevo nodo con el tiquete atendido
     * Si la cola esta
     **/
    public void encolar(TiqueteAtentido tiqueteAtentido) {
        NodoGeneric<TiqueteAtentido> nuevo = new NodoGeneric<>(tiqueteAtentido);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            // Necesitamos encontrar el último nodo real de la cola (al deserializar se pierde el fin)
            NodoGeneric<TiqueteAtentido> actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // Conectar el nuevo nodo al final real de la cola para poder seguir con el nodo
            actual.setSiguiente(nuevo);
            fin = nuevo;
        }
        size++;
    }

    /**
     * Metodo para desencolar un tiquete atendido
     * @return TiqueteAtentido
     * 
     * Se desencola el primer tiquete atendido de la cola
     * Si la cola esta vacia se retorna null
     * Si la cola no esta vacia se actualiza el inicio y se decrementa el tamaño de la cola
     */
    public TiqueteAtentido desencolar() {
        if (estaVacia()) {
            return null;
        }
        TiqueteAtentido tiqueteAtentido = inicio.getDato();
        inicio = inicio.getSiguiente();
        if (inicio == null) {
            fin = null;
        }
        size--;
        return tiqueteAtentido;
    }

    public void verificarIntegridad() {
        if (inicio == null) {
            fin = null;
            size = 0;
            return;
        }
        // Recorrer la cola contando nodos y encontrando el último (esto es necesario porque sin esto el fin se pierde y no se puede agregar nada despues de cargar de nuevo)
        int contador = 1;
        NodoGeneric<TiqueteAtentido> actual = inicio;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            contador++;
        }
        
        // Asegurarse que el fin apunte al último nodo
        fin = actual;
        // Actualizar el tamaño real
        size = contador;
    }


    public boolean estaVacia() {
        return inicio == null;
    }

    // Getters y Setters de los atributos de la clase
    public NodoGeneric<TiqueteAtentido> getinicio() {
        return inicio;
    }

    public void setinicio(NodoGeneric<TiqueteAtentido> inicio) {
        this.inicio = inicio;
    }

    public NodoGeneric<TiqueteAtentido> getFin() {
        return fin;
    }

    public void setFin(NodoGeneric<TiqueteAtentido> fin) {
        this.fin = fin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
