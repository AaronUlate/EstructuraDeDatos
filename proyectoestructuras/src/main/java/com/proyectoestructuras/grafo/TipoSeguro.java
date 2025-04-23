/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoestructuras.grafo;

/**
 *
 * @author daval
 */
public enum TipoSeguro {
    VIDA("Seguro de Vida"),
    VEHICULO("Seguro de Veh�culo"),
    HOGAR("Seguro de Hogar"),
    MEDICO("Seguro M�dico");

    private final String descripcion;

    TipoSeguro(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

