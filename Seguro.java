/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoestrucutras.seguros;

/**
 *
 * @author daval
 */
public class Seguro {
    
    private String nombre;
    private String descripcion;
    private double monto;
    private TipoSeguro tipo;

    public Seguro(String nombre, String descripcion, double monto, TipoSeguro tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoSeguro getTipo() {
        return tipo;
    }

    public void setTipo(TipoSeguro tipo) {
        this.tipo = tipo;
    }
}

