/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoestrucutras.seguros;

/**
 *
 * @author daval
 */
public class ListaSeguros {
    
    private NodoSeguro inicio;
    private NodoSeguro cabeza;

    public ListaSeguros(NodoSeguro inicio, NodoSeguro cabeza) {
        this.inicio = inicio;
        this.cabeza = cabeza;
    }

    public NodoSeguro getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoSeguro cabeza) {
        this.cabeza = cabeza;
    }
    
   
    public ListaSeguros() {
        this.inicio = null;
    }
    
    public NodoSeguro getInicio() {
        return inicio;
    }

    public void setInicio(NodoSeguro inicio) {
        this.inicio = inicio;
    }

    public void agregar(Seguro seguro) {
        NodoSeguro nuevo = new NodoSeguro(seguro);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoSeguro actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public Seguro buscar(String nombre) {
        NodoSeguro actual = inicio;
        while (actual != null) {
            if (actual.getDato().getNombre().equals(nombre)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null; // Si no se encuentra el seguro
    }

    public ListaSeguros(NodoSeguro inicio) {
        this.inicio = inicio;
    }

    public void eliminar(String nombre) {
        if (inicio != null && inicio.getDato().getNombre().equals(nombre)) {
            inicio = inicio.getSiguiente();
            return;
        }
        NodoSeguro actual = inicio;
        while (actual != null && actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getNombre().equals(nombre)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    public void verificarIntegridad() {
        NodoSeguro actual = inicio;
        while (actual != null) {
            if (actual.getDato() == null) {
                throw new IllegalStateException("Nodo sin seguro asignado");
            }
            actual = actual.getSiguiente();
        }
    }

}
