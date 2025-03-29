package com.proyectoestructuras.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyectoestructuras.estructures.ColaTiquetesAtendidos;
import com.proyectoestructuras.models.TiqueteAtentido;


public class TiqueteAtentidoJson {

    private static TiqueteAtentidoJson globalInstance = null;
    private final ColaTiquetesAtendidos colaTiquetesAtendidos = new ColaTiquetesAtendidos("Tiquetes Atendidos");


    public static TiqueteAtentidoJson getInstance() {
        if (globalInstance == null) {
            globalInstance = new TiqueteAtentidoJson();
        }
        return globalInstance;
    }

    /**
     * Agrega un tiquete atendido a la cola
     * @param tiquete El tiquete atendido a agregar
     */
    public void agregarTiquete(TiqueteAtentido tiquete) {
        colaTiquetesAtendidos.encolar(tiquete);
    }

    /**
     * Obtiene la cola de tiquetes atendidos
     * @return La cola de tiquetes atendidos
     */
    public ColaTiquetesAtendidos getColaTiquetesAtendidos() {
        return colaTiquetesAtendidos;
    }

    /**
     * Guarda la cola de tiquetes atendidos en un archivo JSON
     * @param filename El nombre del archivo
     * @return La instancia actual
     */
    public TiqueteAtentidoJson save(String filename){
        if (filename == null) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
            return this;
        }
        //Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        Gson gson = new GsonBuilder().create();
        try(FileWriter writer = new FileWriter(filename)){
            gson.toJson(this, writer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo"+ e.getMessage());
        }
        return this;
    }

    /**
     * Metodo para cargar el sistema de un archivo
     * @param filename nombre del archivo
     * @return instancia del sistema
     * Se utiliza la libreria Gson para cargar el sistema de un archivo
     * Se muestra un mensaje de error si no se puede cargar el archivo
     */
    public static TiqueteAtentidoJson load(String filename){
        //Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        Gson gson = new GsonBuilder().create();
        TiqueteAtentidoJson tiquetesAtentidos;
        try(FileReader reader = new FileReader(filename)){
            tiquetesAtentidos = gson.fromJson(reader, TiqueteAtentidoJson.class);
            if (tiquetesAtentidos != null) {
                globalInstance = tiquetesAtentidos;
                tiquetesAtentidos.colaTiquetesAtendidos.verificarIntegridad();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
                return getInstance();
            }
        } catch (FileNotFoundException e) {
            return getInstance();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo"+ e.getMessage());
            return getInstance();
        }
        return globalInstance;
    }


}
