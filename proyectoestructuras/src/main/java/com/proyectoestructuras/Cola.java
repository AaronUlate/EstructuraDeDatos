package com.proyectoestructuras;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Clase que representa una cola
 * Se utiliza para almacenar los tiquetes de los clientes
 * Utiliza el nodo generico con un tipo de dato Tiquete
 */

public class Cola implements Serializable{

    // Atributos de la clase
    private static int globalId;
    private int size = 0;
    private NodoGeneric<Tiquete> inicio;
    private NodoGeneric<Tiquete> fin;
    private final int id;
    private final String colaName;

    /**
     * Constructor de la clase Cola
     * @param colaName
     * 
     * Se inicia el inicio y el fin en null
     * Se inicia el id con el globalId y se incrementa en 1
     */
    public Cola(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.id = globalId++;
    }
    
    /**
     * Metodo para encolar un tiquete
     * @param tiquete
     * 
     * Se crea un nuevo nodo con el tiquete
     * Si la cola esta vacia se inicia el inicio y el fin con el nuevo nodo
     * Si la cola no esta
     * Se recorre la cola hasta encontrar el ultimo nodo y se conecta el nuevo nodo (esto es necesario porque al deserializar se pierde el fin)
     * Se actualiza el fin con el nuevo nodo
     * Se incrementa el tamaño de la cola y se muestra un mensaje con la posicion del tiquete en la cola 
     */
    public void encolar(Tiquete tiquete) {
        NodoGeneric<Tiquete> nuevo = new NodoGeneric<>(tiquete);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            // Necesitamos encontrar el último nodo real de la cola (al deserializar se pierde el fin)
            NodoGeneric<Tiquete> actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // Conectar el nuevo nodo al final real de la cola para poder seguir con el nodo
            actual.setSiguiente(nuevo);
            fin = nuevo;
        }
        // Validacion del puesto del tiquete
        if(size == 0){
            JOptionPane.showMessageDialog(null, "tiquete creado, el tiquete se encuentra de primero en la cola " + getColaName());
        }else if(size == 1){
            JOptionPane.showMessageDialog(null, "Tiquete creado, el tiquete se encuentra de segundo en la cola " + getColaName());
        }else{ 
            JOptionPane.showMessageDialog(null, "Tiquete creado, existen "+size+" tiquetes por delante en la cola " + getColaName());
        }
        size++;
    }
    
    /**
     * Metodo para verificar la integridad de la cola
     * 
     * Si la cola esta vacia se actualiza el fin y el tamaño
     * Si la cola no esta vacia
     * Se recorre la cola contando los nodos y encontrando el ultimo nodo
     * Se asegura que el fin apunte al ultimo nodo
     * Se actualiza el tamaño real
     */
    public void verificarIntegridad() {
        if (inicio == null) {
            fin = null;
            size = 0;
            return;
        }
        // Recorrer la cola contando nodos y encontrando el último (esto es necesario porque sin esto el fin se pierde y no se puede agregar nada despues de cargar de nuevo)
        int contador = 1;
        NodoGeneric<Tiquete> actual = inicio;
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
     * Metodo para desencolar un tiquete
     * 
     * Si la cola esta vacia se retorna null
     * Si la cola no esta vacia
     * Se obtiene el tiquete del inicio
     * Se actualiza el inicio con el siguiente nodo
     * Se decrementa el tamaño de la cola
     * Se retorna el tiquete
     */
    public Tiquete desencolar() {
        if (inicio == null) {
            return null;
        } else {
            Tiquete tiquete = inicio.getDato();
            inicio = inicio.getSiguiente();
            size--;
            return tiquete;
        }

    }

    /**
     * Metodo para obtener el tiquete del inicio
     * 
     * Si el inicio es null se retorna null
     * Si el inicio no es null se retorna el tiquete del inicio
     */
    public Tiquete getInicio() {
        if (inicio == null) {
            return null;
        } else {
            return inicio.getDato();
        }
    }

    /**
     * Metodo para obtener el tiquete del fin
     * 
     * Si el fin es null se retorna null
     * Si el fin no es null se retorna el tiquete del fin
     */
    public boolean estaVacia() {
        return inicio == null;
    }

    /**
     * Metodo para vaciar la cola
     * 
     * Se actualiza el inicio y el fin en null
     */
    public void vaciar() {
        inicio = null;
        fin = null;
    }

    /**
     * Metodo para imprimir la cola
     * 
     * Se crea un nodo auxiliar que inicia en el inicio
     * Mientras el nodo auxiliar no sea null
     * Se imprime el tiquete del nodo auxiliar
     * Se actualiza el nodo auxiliar con el siguiente nodo
     * Se llama el metodo toString del tiquete
     */
    public void imprimir() {
        NodoGeneric<Tiquete> aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato().toString());
            aux = aux.getSiguiente();
        }
    }

    // Getters y Setters de los atributos de la clase
    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public String getColaName() {
        return colaName;
    }



}
