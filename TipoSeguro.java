/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoestrucutras.seguros;

/**
 *
 * @author daval
 */
public enum TipoSeguro {
    VIDA("Seguro de Vida"),
    VEHICULO("Seguro de Vehículo"),
    HOGAR("Seguro de Hogar"),
    MEDICO("Seguro Médico");

    private final String descripcion;

    TipoSeguro(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

