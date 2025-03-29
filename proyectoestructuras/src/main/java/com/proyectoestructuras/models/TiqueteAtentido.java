package com.proyectoestructuras.models;

public class TiqueteAtentido {
    private Tiquete tiquete;
    private String colaOrigen;
    private String atendidoA;
    
    public TiqueteAtentido(Tiquete tiquete, String colaOrigen, String atendidoA) {
        this.tiquete = tiquete;
        this.colaOrigen = colaOrigen;
        this.atendidoA = atendidoA;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public String getColaOrigen() {
        return colaOrigen;
    }

    public void setColaOrigen(String colaOrigen) {
        this.colaOrigen = colaOrigen;
    }

    public String getAtendidoA() {
        return atendidoA;
    }

    public void setAtendidoA(String atendidoA) {
        this.atendidoA = atendidoA;
    }

    @Override
    public String toString() {
        return "TiqueteAtentido en la caja: " + colaOrigen + ", atendido a: " + atendidoA + ", tiquete: " + tiquete.toString();
    }



}
