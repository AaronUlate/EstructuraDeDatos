package com.proyectoestructuras.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tiquete implements Serializable{
    private static int globalID = 0;
    private final int id;
    //La clase cliente contiene los datos del cliente
    private Cliente cliente;
    //CreadoA es una clase que contiene la fecha y hora en la que se crea el tiquete
    private final String creadoA;
    //AtendidoA es una clase que contiene la fecha y hora en la que se atendio el tiquete
    private String atendidoA;
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
        this.atendidoA = "N.A.";
        this.id = Tiquete.globalID++;

        //formateador de la fecha y hora para una mejor visualizacion
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.creadoA = LocalDateTime.now().format(formateador);

    }

    /**
     * Metodo toString de la clase Tiquete
     * Se planea estilizar en un futuro
     */
    @Override
    public String toString() {
        if(atendidoA != null){
            return "Tiquete de tipo: " + tipoTiquete + ", creado a las: " + creadoA + ", atendido a: " + atendidoA + ", cliente: " + cliente;
        }
        return "Tiquete de tipo: " + tipoTiquete + ", creado a: " + creadoA + ", cliente: " + cliente;
        
    }

   //Getters y Setters        
    public static int getGlobalID() {
        return globalID;
    }

    public boolean esEmergencia() {
        return this.TipoTramite == Tramite.EMERGENCIA_EJECUTIVO;
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

    public String getCreadoA() {
        return creadoA;
    }

    public String getAtendidoA() {
        return atendidoA;
    }
    public void setAtendidoA(String atendidoA) {
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
