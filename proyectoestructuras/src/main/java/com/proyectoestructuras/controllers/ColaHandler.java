package com.proyectoestructuras.controllers;

import java.io.Serializable;

import com.proyectoestructuras.estructures.Cola;
import com.proyectoestructuras.estructures.ColaGenerales;

/**
 * Clase que maneja las colas del programa
 * Se utiliza para manejar las colas del programa en una clase centralizada con una solo instancia global
 */

public class ColaHandler implements Serializable{


    // Atributos de la clase, 1 cola preferencial, 1 cola rapida y una cola de colas generales con tamaño variable
    private static ColaHandler globalInstace = null;
    private Cola colaPreferencial;
    private Cola colaRapida;
    private ColaGenerales colasGenerales = new ColaGenerales("Colas Generales");

    /**
     * Constructor de la clase ColaHandler
     * Se inicia la cola preferencial y la cola rapida
     * Este constructor es privado para evitar que se puedan crear mas instancias de la clase
     */
    private ColaHandler() {
        this.colaPreferencial = new Cola("Preferencial");
        this.colaRapida = new Cola("Rapida");
    }

    /**
     * Metodo para obtener la instancia global de la clase ColaHandler
     * @return instancia global de la clase ColaHandler
     */
    public static ColaHandler getInstance() {
        if (globalInstace == null) {
            globalInstace = new ColaHandler();
        }
        return globalInstace;
    }

    // Getters y Setters de los atributos de la clase
    public static ColaHandler getGlobalInstace() {
        return globalInstace;
    }

    public static void setGlobalInstace(ColaHandler globalInstace) {
        ColaHandler.globalInstace = globalInstace;
    }

    public Cola getColaPreferencial() {
        return colaPreferencial;
    }

    public void setColaPreferencial(Cola colaPreferencial) {
        this.colaPreferencial = colaPreferencial;
    }

    public Cola getColaRapida() {
        return colaRapida;
    }

    public void setColaRapida(Cola colaRapida) {
        this.colaRapida = colaRapida;
    }

    public ColaGenerales getColasGenerales() {
        return colasGenerales;
    }

    public void setColasGenerales(ColaGenerales colasGenerales) {
        this.colasGenerales = colasGenerales;
    }

    public Cola colaGeneralMasDisponible(){   
        return colasGenerales.colaMasDisponible();
    }

    

}
