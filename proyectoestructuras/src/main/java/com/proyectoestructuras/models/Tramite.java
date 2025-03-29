package com.proyectoestructuras.models;

/**
 * Enumeración de los tipos de trámites que se pueden realizar en el banco
 * Se utiliza para definir los tipos de trámites que se pueden realizar en el banco
 */

public enum Tramite {

    // Enumeración de los tipos de trámites que se pueden realizar en el banco


    DEPOSITOS("Depósitos bancarios"),
    MONEDAS("CAMBIO DE MONEDAS"),
    CAMBIO_DE_DIVISAS("Cambio de divisas"),
    CANCELACIONES("Cancelaciones"),
    PLANES_ECOLOGICOS("Planes ecológicos"),
    EMERGENCIA_EJECUTIVO("Emergencia ejecutivo"),
    RETIROS("Retiros bancarios"),
    SERVICIOS("Pago de servicios");

    // Almacena la descripción detallada del tipo de trámite
    private final String descripcion;

    /**
     * Constructor del enum que inicializa la descripción del trámite.
     * @param descripcion texto que describe el tipo de trámite
     */
    Tramite(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción detallada del trámite.
     * @return la descripción del trámite como String
     */
    public String getDescripcion() {
        return descripcion;
    }

}
