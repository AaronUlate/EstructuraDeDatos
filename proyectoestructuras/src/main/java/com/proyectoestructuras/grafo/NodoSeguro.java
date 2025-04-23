/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoestructuras.grafo;

/**
 *
 * @author daval
 */
public class NodoSeguro {
    
    private Seguro dato;
    private NodoSeguro siguiente;
    private NodoSeguro inicio;
    private NodoSeguro fin;
    
    



    public NodoSeguro getInicio() {
        return inicio;
    }

    public void setInicio(NodoSeguro inicio) {
        this.inicio = inicio;
    }

    public NodoSeguro getFin() {
        return fin;
    }

    public void setFin(NodoSeguro fin) {
        this.fin = fin;
    }

    public NodoSeguro(NodoSeguro inicio) {
        this.inicio = inicio;
    }

    public NodoSeguro(Seguro dato) {
        this.dato = dato;
    }

    public Seguro getDato() {
        return dato;
    }

    public void setDato(Seguro dato) {
        this.dato = dato;
    }

    public NodoSeguro getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSeguro siguiente) {
        this.siguiente = siguiente;
    }
}

