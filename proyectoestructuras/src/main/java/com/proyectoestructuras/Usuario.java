package com.proyectoestructuras;

/***
 * Clase que representa un unsuario en el sistema EcoColones
 * Almacena la informacion de un usuario para poder ser utilizado en el sistema
 */

public class Usuario {
    // Atributos de la clase representan las credenciales
    private String correo;
    private String contrasena;

    /***
     * Constructor de la clase
     * @param correo correo del usuario
     * @param contrasena contrasena del usuario
     */
    public Usuario(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters y Setters de los atributos de la clase

    /***
     * Metodo que retorna el correo del usuario
     * @return correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /***
     * Metodo que establece el correo del usuario
     * @param correo correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /***
     * Metodo que retorna la contrasena del usuario
     * @return contrasena del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /***
     * Metodo que establece la contrasena del usuario
     * @param contrasena contrasena del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    

}
