package com.proyectoestructuras;

/**
 * Enumeración de los tipos de trámites que se pueden realizar en el banco
 * Se utiliza para definir los tipos de trámites que se pueden realizar en el banco
 */

public enum Tramite {

    // Enumeración de los tipos de trámites que se pueden realizar en el banco


    DEPOSITOS("Depósitos bancarios"),
    RETIROS("Retiros bancarios"),
    CAMBIO_DE_DIVISAS("Cambio de divisas"),
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
