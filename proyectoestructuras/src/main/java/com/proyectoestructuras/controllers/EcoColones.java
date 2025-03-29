package com.proyectoestructuras.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyectoestructuras.Constants;
import com.proyectoestructuras.estructures.Cola;
import com.proyectoestructuras.estructures.ColaGenerales;
import com.proyectoestructuras.estructures.ColaUsuarios;
import com.proyectoestructuras.estructures.NodoGeneric;
import com.proyectoestructuras.models.Usuario;

/**
 * Clase que representa el sistema EcoColones
 * 
 * Se utiliza para almacenar los datos del sistema y realizar las operaciones del sistema
 * Esta clase utiliza el patron Singleton para que solo se pueda crear una instancia de la clase
 */

public class EcoColones implements Serializable{

    // Atributos de la clase
    //Cuenta con un atributo nombre, un atributo globalInstace, un atributo colaHandler (manejador de las colas del sistema) y un atributo colaUsuarios (cola de usuarios del sistema)

    private final String nombre;
    private static EcoColones globalInstace = null;
    private final ColaHandler colaHandler = ColaHandler.getInstance();
    private ColaUsuarios colaUsuarios = new ColaUsuarios("Usuarios");
    private TiqueteAtentidoJson tiqueteAtentidoJson = TiqueteAtentidoJson.getInstance();


    /**
     * Constructor de la clase EcoColones
     * @param nombre nombre del sistema
     * 
     * Se inicia el atributo nombre con el nombre del sistema
     * Es privado para que solo se pueda crear una instancia de la clase
     */
    private EcoColones(String nombre) {
        this.nombre = nombre;

    }

    /**
     * Metodo para obtener la instancia global de la clase EcoColones
     * @param nombre nombre del sistema
     * @return instancia global de la clase EcoColones
     */
    public static EcoColones getInstance(String nombre) {
        if (globalInstace == null) {
            globalInstace = new EcoColones(nombre);
        }
        return globalInstace;
    }

    /**
     * Metodo para guardar el sistema en un archivo
     * @param filename nombre del archivo
     * @return instancia del sistema
     * Se utiliza la libreria Gson para guardar el sistema en un archivo
     * Se muestra un mensaje de error si no se puede guardar el archivo
     */
    public EcoColones save(String filename){
        if (filename == null) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
            return this;
        }

        Gson gson = new GsonBuilder().create();
        try(FileWriter writer = new FileWriter(filename)){
            gson.toJson(this, writer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo"+ e.getMessage());
        }

        this.tiqueteAtentidoJson.save(Constants.TIQUETES);

        return this;
    }

    /**
     * Metodo para cargar el sistema de un archivo
     * @param filename nombre del archivo
     * @return instancia del sistema
     * Se utiliza la libreria Gson para cargar el sistema de un archivo
     * Se muestra un mensaje de error si no se puede cargar el archivo
     */

    public static EcoColones load(String filename){
        //Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        Gson gson = new GsonBuilder().create();
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
        TiqueteAtentidoJson tiquetesAtendidos = TiqueteAtentidoJson.load(Constants.TIQUETES);
        if(ecoColones != null){
            ecoColones.setTiqueteAtentidoJson(tiquetesAtendidos);
        }
        return ecoColones;
    }

    /**
     * Metodo para crear un usuario
     * 
     * Se solicita un correo y una contraseña para el usuario
     * Si el correo o la contraseña son invalidos se muestra un mensaje de error
     * Si el usuario se crea correctamente se encola en la cola de usuarios
     */
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

    // Getters y Setters de los atributos de la clase
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

    public TiqueteAtentidoJson getTiqueteAtentidoJson() {
        return tiqueteAtentidoJson;
    }

    public void setTiqueteAtentidoJson(TiqueteAtentidoJson tiqueteAtentidoJson) {
        this.tiqueteAtentidoJson = tiqueteAtentidoJson;
    }


}
