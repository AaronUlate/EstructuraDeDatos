package com.proyectoestructuras;

public enum TipoTiquete {   
    PREFERENCIAL(1),
    UN_TRAMITE(2),
    DOS_O_MAS_TRAMITES(3);

    private final int tipo;

    TipoTiquete(int tipo) {
        this.tipo = tipo;
    }

    public int getPrioridad() {
        return tipo;
    }
}
