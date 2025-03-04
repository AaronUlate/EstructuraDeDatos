package com.proyectoestructuras;

import java.io.Serializable;
/**
 * Clase que representa una cola de usuarios
 * Se utiliza para almacenar los datos de los usuarios del sistema
 */
public class ColaUsuarios implements Serializable{

    // Atributos de la clase
    private static int globalId;
    private int size = 0;
    private NodoGeneric<Usuario> inicio;
    private NodoGeneric<Usuario> fin;
    private final int id;
    private final String colaName;

    /**
     * Constructor de la clase ColaUsuarios
     * @param colaName
     * 
     * Se inicia el inicio y el fin en null
     * Se inicia el id con el globalId y se incrementa en 1
     */
    public ColaUsuarios(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.id = globalId++;
    }

    /**
     * Metodo para encolar un usuario en la cola
     * @param usuario
     * 
     * Se crea un nuevo nodo con el usuario
     * Si la cola esta vacia se inicia el inicio y el fin con el nuevo nodo
     * Si la
     * Se recorre la cola hasta encontrar el ultimo nodo y se conecta el nuevo nodo (esto es necesario porque al deserializar se pierde el fin)
     */
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

    /**
     * Metodo para verificar la integridad de la cola
     * si la cola esta vacia se actualiza el fin y el tamaño
     * si la cola no esta vacia se recorre la cola contando nodos y encontrando el ultimo (esto es necesario porque sin esto el fin se pierde y no se puede agregar nada despues de cargar de nuevo)
     * Asegura que el fin apunte al ultimo nodo
     * Actualiza el tamaño real
     */
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

    /**
     * Metodo para desencolar un usuario de la cola
     * @return Usuario
     * 
     * Si la cola esta vacia se retorna null
     * Si la cola no esta vacia
     * Se obtiene el usuario del inicio de la cola
     * Se actualiza el inicio con el siguiente nodo
     */
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

    /**
     * Metodo para obtener el usuario del inicio
     * @return inicio
     */
    public NodoGeneric<Usuario> getInicio() {
        if (inicio == null) {
            return null;
        } else {
            return inicio;
        }
    }

    // Getters de los atributos de la clase
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
