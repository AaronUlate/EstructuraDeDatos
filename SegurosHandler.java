/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectoestrucutras.seguros;

        
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author daval
 */
public class SegurosHandler {
    
    private static SegurosHandler instance;
    private ListaSeguros listaSeguros;

    private SegurosHandler() {
        listaSeguros = new ListaSeguros();
    }

    public static SegurosHandler getInstance() {
        if (instance == null) {
            instance = new SegurosHandler();
        }
        return instance;
    }

    public void agregarSeguro(Seguro seguro) {
        listaSeguros.agregar(seguro);
    }

    public Seguro buscarSeguro(String nombre) {
        return listaSeguros.buscar(nombre);
    }

    public void eliminarSeguro(String nombre) {
        listaSeguros.eliminar(nombre);
    }

    public void guardar(String archivo) {
        Gson gson = new GsonBuilder().create();
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SegurosHandler cargar(String archivo) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(archivo)) {
            instance = gson.fromJson(reader, SegurosHandler.class);
            instance.listaSeguros.verificarIntegridad();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public ListaSeguros getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ListaSeguros listaSeguros) {
        this.listaSeguros = listaSeguros;
    }
}

