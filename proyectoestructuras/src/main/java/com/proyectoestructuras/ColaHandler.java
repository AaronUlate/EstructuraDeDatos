package com.proyectoestructuras;

import java.io.Serializable;

public class ColaHandler implements Serializable{

    private static ColaHandler globalInstace = null;
    private Cola colaPreferencial;
    private Cola colaRapida;
    private ColaGenerales colasGenerales = new ColaGenerales("Colas Generales");

    private ColaHandler() {
        this.colaPreferencial = new Cola("Preferencial");
        this.colaRapida = new Cola("Rapida");
    }

    public static ColaHandler getInstance() {
        if (globalInstace == null) {
            globalInstace = new ColaHandler();
        }
        return globalInstace;
    }

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
