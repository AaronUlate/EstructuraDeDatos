package com.proyectoestructuras;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Cola implements Serializable{
    private static int globalId;
    private int size = 0;
    private NodoGeneric<Tiquete> inicio;
    private NodoGeneric<Tiquete> fin;
    private final int id;
    private final String colaName;


    public Cola(String colaName) {
        this.colaName = colaName;
        this.inicio = null;
        this.fin = null;
        this.id = globalId++;
    }

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

    public Tiquete getInicio() {
        if (inicio == null) {
            return null;
        } else {
            return inicio.getDato();
        }
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void vaciar() {
        inicio = null;
        fin = null;
    }

    public void imprimir() {
        NodoGeneric<Tiquete> aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato().toString());
            aux = aux.getSiguiente();
        }
    }

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
