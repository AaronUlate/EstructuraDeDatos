package com.proyectoestructuras;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Tiquete implements Serializable{
    private static int globalID = 0;
    private final int id;
    //La clase cliente contiene los datos del cliente
    private Cliente cliente;
    //CreadoA es una clase que contiene la fecha y hora en la que se crea el tiquete
    private final LocalDateTime creadoA;
    //AtendidoA es una clase que contiene la fecha y hora en la que se atendio el tiquete
    private LocalDateTime atendidoA;
    //TipoTiquete es una enum que contiene el tipo de tiquete que se va a crear
    private final TipoTiquete tipoTiquete;
    //TipoTramite es una enum que contiene el tipo de tramite que se va a realizar
    private Tramite TipoTramite;


    /**
     * Constructor de la clase Tiquete
     * 
     * @param cliente
     * @param tipoTiquete
     * @param TipoTramite
     * 
     * Se inicia el creadoA con la fecha y hora actual y 2 segundos
     * Se inicia el atendidoA con null
     * Se inicia el id con el globalID y se incrementa en 1
     */
    public Tiquete(Cliente cliente, TipoTiquete tipoTiquete, Tramite TipoTramite) {
        this.cliente = cliente;
        this.tipoTiquete = tipoTiquete;
        this.TipoTramite = TipoTramite;
        this.creadoA = LocalDateTime.now().withSecond(2).withNano(0);
        this.atendidoA = null;
        this.id = Tiquete.globalID++;
    }

    /**
     * Metodo toString de la clase Tiquete
     * Se planea estilizar en un futuro
     */
    @Override
    public String toString() {
        return "Tiquete{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", creadoA=" + creadoA +
                ", atendidoA=" + atendidoA +
                ", tipoTiquete=" + tipoTiquete +
                ", TipoTramite=" + TipoTramite +
                '}';
    }

   //Getters y Setters        
    public static int getGlobalID() {
        return globalID;
    }

    

    public static void setGlobalID(int globalID) {
        Tiquete.globalID = globalID;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getCreadoA() {
        return creadoA;
    }

    public LocalDateTime getAtendidoA() {
        return atendidoA;
    }

    public void setAtendidoA(LocalDateTime atendidoA) {
        this.atendidoA = atendidoA;
    }

    public TipoTiquete getTipoTiquete() {
        return tipoTiquete;
    }

    public Tramite getTipoTramite() {
        return TipoTramite;
    }

    public void setTipoTramite(Tramite tipoTramite) {
        TipoTramite = tipoTramite;
    }

    public int getPrioridad() {
        return tipoTiquete.getPrioridad();
    }

}
