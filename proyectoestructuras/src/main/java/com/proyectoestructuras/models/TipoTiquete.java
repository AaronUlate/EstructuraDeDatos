package com.proyectoestructuras.models;
/**
 * Enumeración que contiene los tipos de tiquetes que se pueden crear
 * Existen 3 tipos de tiquetes, se utilizan para asignar prioridades a los tiquetes y enviarlos a las colas correspondientes
 */
public enum TipoTiquete {   

    // Enumeración de los tipos de tiquetes que se pueden crear
    PREFERENCIAL(1),
    UN_TRAMITE(2),
    DOS_O_MAS_TRAMITES(3);

    // Almacena la prioridad del tipo de tiquete
    private final int tipo;

    /**
     * Constructor del enum que inicializa la prioridad del tipo de tiquete.
     * @param tipo número que representa la prioridad del tipo de tiquete
     */
    TipoTiquete(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la prioridad del tipo de tiquete.
     * @return la prioridad del tipo de tiquete como int
     */
    public int getPrioridad() {
        return tipo;
    }
}
