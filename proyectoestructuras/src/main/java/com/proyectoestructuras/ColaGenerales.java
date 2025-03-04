package com.proyectoestructuras;

import java.io.Serializable;

/**
 * Esta clase representa la cola de colas generales
 * Tiene metodos que permiten definir la cola mas adecuada para encolar el tiquete
 * 
 */

public class ColaGenerales implements Serializable{
    //Atributos de la clase, cuenta con 2 NodoGeneric de tipo cola para almacenar las colas en la cola de colas
    private static int globalId;
    private int size = 0;
    private NodoGeneric<Cola> inicio;
    private NodoGeneric<Cola> fin;
    private final int id;
    private final String colaName;


    /**
     * Constructor de la clase ColaGenerales
     * @param colaName nombre de la cola
     * 
     * Se inicia el inicio y el fin en null
     * Se inicia el id con el globalId y se incrementa en 1
     */
    public ColaGenerales(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.id = globalId++;
    }

    //Metodo para revisar si la cola esta vacia
    public boolean estaVacia() {
        return inicio == null;
    }   


    /**
     * Metodo para encolar una cola
     * @param cola
     *
     * Se crea un nuevo nodo con la cola
     * Si la cola esta vacia se inicia el inicio y el fin con el nuevo nodo
     * Si la cola no esta vacia se recorre la cola hasta encontrar el ultimo nodo y se conecta el nuevo nodo (esto es necesario porque al deserializar se pierde el fin)
     * Se actualiza el fin con el nuevo nodo
     * Se incrementa el tamaño de la cola
     */
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
    
    /**
     * Metodo para verificar la integridad de la cola
     * si la cola esta vacia se actualiza el fin y el tamaño
     * si la cola no esta vacia se recorre la cola contando nodos y encontrando el ultimo (esto es necesario porque sin esto el fin se pierde y no se puede agregar nada despues de cargar de nuevo)
     * Asegura que el fin apunte al ultimo nodo
     * Actualiza el tamaño real
     */
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

    /**
     * Metodo para desencolar una cola
     * Si la cola esta vacia se retorna null
     * Si la cola no esta vacia se retorna la cola y se actualiza el inicio
     */
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

    /**
     * Metodo para obtener una cola segun el id
     * @param id
     * @return cola
     * 
     * Si el id no es valido se lanza una excepcion
     * Se recorre la cola de colas hasta encontrar la cola con el id deseado
     * 
     */
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


    /**
     * Este metodo retorna la cola mas disponible, es decir la cola con menos tiquetes
     * cicla por todas las colas y retorna la cola con menos tiquetes al comparar el tamaño de las colas
     * @return colaMasDisponible
     */

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

    //Getters y Setters

    public int getSize() {
        return size;
    }

    /**
     * Este metodo busca una cola segun el id y la retorna
     * @param id
     * @return
     */

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
