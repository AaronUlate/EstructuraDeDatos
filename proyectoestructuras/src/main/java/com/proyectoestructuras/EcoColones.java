package com.proyectoestructuras;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EcoColones implements Serializable{

    private final String nombre;
    private static EcoColones globalInstace = null;
    private final ColaHandler colaHandler = ColaHandler.getInstance();
    private ColaUsuarios colaUsuarios = new ColaUsuarios("Usuarios");


    private EcoColones(String nombre) {
        this.nombre = nombre;

    }

    public static EcoColones getInstance(String nombre) {
        if (globalInstace == null) {
            globalInstace = new EcoColones(nombre);
        }
        return globalInstace;
    }

    public EcoColones save(String filename){
        if (filename == null) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
            return this;
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        try(FileWriter writer = new FileWriter(filename)){
            gson.toJson(this, writer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo"+ e.getMessage());
        }
        return this;
    }

    public static EcoColones load(String filename){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        EcoColones ecoColones = null;
        try(FileReader reader = new FileReader(filename)){
            ecoColones = gson.fromJson(reader, EcoColones.class);
            ecoColones.getColaUsuarios().verificarIntegridad();
            ecoColones.getColaHandler().getColaPreferencial().verificarIntegridad();
            ecoColones.getColaHandler().getColaRapida().verificarIntegridad();


             // Verificar integridad de colas generales y sus colas internas
            ColaGenerales colasGenerales = ecoColones.getColaHandler().getColasGenerales();
            colasGenerales.verificarIntegridad();
            
            // Verificar cada cola dentro de colas generales
            NodoGeneric<Cola> actual = colasGenerales.getInicio();
            while (actual != null) {
                actual.getDato().verificarIntegridad();
                actual = actual.getSiguiente();
            }
            

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo"+ e.getMessage());
        }
        return ecoColones;
    }

    public void crearUsuario(){
        String correo = JOptionPane.showInputDialog("Ingrese un correo para su usuario");
        if(correo == null || correo.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Operación cancelada o correo invalido");
            return;
        }
        String contrasena = JOptionPane.showInputDialog("Ingrese la nuevo contraseña del usuario");
        if(contrasena == null || contrasena.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Operación cancelada o contraseña invalido");
            return;
        }
        Usuario nuevoUsuario = new Usuario(correo, contrasena);
        this.getColaUsuarios().encolar(nuevoUsuario);
    }

    public String getNombre() {
        return nombre;
    }

    public static EcoColones getGlobalInstace() {
        return globalInstace;
    }

    public static void setGlobalInstace(EcoColones globalInstace) {
        EcoColones.globalInstace = globalInstace;
    }

    public ColaHandler getColaHandler() {
        return colaHandler;
    }

    public ColaUsuarios getColaUsuarios() {
        return colaUsuarios;
    }

    public void setColaUsuarios(ColaUsuarios colaUsuarios) {
        this.colaUsuarios = colaUsuarios;
    }

    public void agregarUsuario(Usuario usuario){
        colaUsuarios.encolar(usuario);
    }


}
