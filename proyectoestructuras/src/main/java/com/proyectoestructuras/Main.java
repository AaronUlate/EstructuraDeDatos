package com.proyectoestructuras;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

/**
 * Clase principal del programa
 * Se utiliza para ejecutar el programa
 * Cuando se ejecuta el programa se carga el archivo del sistema y se inicia el programa
 * Se valida el usuario y se ejecutan las operaciones del sistema
 * Cuenta con un menu para ejecutar las operaciones del sistema
 * Ademas de metodos estaticos para agregar tiquetes y atender tiquetes, validar usuario y crear el sistema
 * Se espera realizar una mejor estilizacion del codigo en un futuro y mejores anotaciones sobre el funcionamiento del programa
 */

public class Main {
    public static void main(String[] args) {
        //Se inica el programa cargando el archivo
        
            EcoColones ecoColones = EcoColones.load(Constants.FILENAME);
            while (ecoColones == null) {
                try {
                    ecoColones = EcoColones.load(Constants.FILENAME);
                    if (ecoColones == null) {
                        ecoColones = iniciarEcoColones();
                        if (ecoColones == null) {
                            int option = JOptionPane.showConfirmDialog(null, "Error al crear banco o Operacion cancelada. ¿Desea intentar nuevamente?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                            if (option == JOptionPane.NO_OPTION) {
                                JOptionPane.showMessageDialog(null, "Gracias por utilizar EcoColones", "Hasta luego", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                    }
                } catch (Error e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar/crear banco + e.getMessage()", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
            boolean usuarioValido = false;
            while (!usuarioValido) {
                try {
                    if (!validarUsuario(ecoColones)) {
                        int option = JOptionPane.showConfirmDialog(null, "Usuario no válido o Operacion cancelada. ¿Desea intentar nuevamente?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                        if (option == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Gracias por utilizar EcoColones", "Hasta luego", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    } else {
                        usuarioValido = true;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al validar usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            int opcion = 0;
            do {
                String input = JOptionPane.showInputDialog("1. Crear tiquete\n2. Atender tiquete\n3. Mostrar tiquetes\n4. Agregar nuevo usuario\n5. Guardar y salir");

                if (input == null) {
                    break;
                }

                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Opción inválida, ingrese un valor numerico valido", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al ejecutar la solicitud", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }


                switch (opcion) {
                    case 1:
                        agregarTiquete(ecoColones);
                        ecoColones.save(Constants.FILENAME);
                        break;
                    case 2:
                        atenderTiquete(ecoColones);
                        ecoColones.save(Constants.FILENAME);
                        break;
                    case 3:
                        // ecoColones.getColaHandler().mostrarTiquetes();
                        break;
                    case 4:
                        ecoColones.crearUsuario();
                        ecoColones.save(Constants.FILENAME);
                        break;
                    case 5:
                        ecoColones.save(Constants.FILENAME);
                        break;
                }
            } while (opcion != 5);
            JOptionPane.showMessageDialog(null, "Gracias por utilizar EcoColones", "Hasta luego", JOptionPane.INFORMATION_MESSAGE);
    
    }

    private static void agregarTiquete(EcoColones ecoColones) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada o nombre vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String edadStr = JOptionPane.showInputDialog("Ingrese la edad del cliente");
        if (edadStr == null || edadStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada o edad vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nombre, edad);

        String tipoTiqueteStr = JOptionPane.showInputDialog("Ingrese el tipo de tiquete\n1. Preferencial\n2. Un trámite\n3. Dos o más trámites");
        if (tipoTiqueteStr == null || tipoTiqueteStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada o tipo de tiquete vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TipoTiquete tipoTiquete;
        try {
            tipoTiquete = TipoTiquete.values()[Integer.parseInt(tipoTiqueteStr) - 1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Tipo de tiquete inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tramiteStr = JOptionPane.showInputDialog("Ingrese el tipo de trámite\n1. Depósitos bancarios\n2. Retiros bancarios\n3. Cambio de divisas\n4. Pago de servicios");
        if (tramiteStr == null || tramiteStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada o tipo de trámite vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Tramite tramite;
        try {
            tramite = Tramite.values()[Integer.parseInt(tramiteStr) - 1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Tipo de trámite inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tiquete tiquete = new Tiquete(cliente, tipoTiquete, tramite);
        if (tipoTiquete == TipoTiquete.PREFERENCIAL) {
            ecoColones.getColaHandler().getColaPreferencial().encolar(tiquete);
        } else if (tipoTiquete == TipoTiquete.UN_TRAMITE) {
            ecoColones.getColaHandler().getColaRapida().encolar(tiquete);
        } else {
            Cola cola = ecoColones.getColaHandler().colaGeneralMasDisponible();
            if (cola != null) {
                cola.encolar(tiquete);
            } else {
                JOptionPane.showMessageDialog(null, "No hay ventanillas disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void atenderTiquete(EcoColones ecoColones) {
        StringBuilder sb = new StringBuilder("Seleccione la cola a atender:\n");
        Cola colaPrefencial = ecoColones.getColaHandler().getColaPreferencial();
        Cola colaRapida = ecoColones.getColaHandler().getColaRapida();
        sb.append("1. Cola Preferencial\n").append(colaPrefencial.getSize()).append(" tiquetes\n");
        sb.append("2. Cola Rápida\n").append(colaRapida.getSize()).append(" tiquetes\n");
        for (int i = 0; i < ecoColones.getColaHandler().getColasGenerales().getSize(); i++) {
            sb.append(i + 3).append(". Cola General ").append(i + 1).append("\n").append(ecoColones.getColaHandler().getColasGenerales().get(i).getSize()).append(" tiquetes\n");
        }

        String input = JOptionPane.showInputDialog(sb.toString());
        if(input == null){
            return;
        }
        try {
            int opcion = Integer.parseInt(input);
            Cola cola;
            if (opcion == 1) {
                cola = colaPrefencial;
            } else if (opcion == 2) {
                cola = colaRapida;
            } else {
                cola = ecoColones.getColaHandler().getColasGenerales().get(opcion - 3);
            }
            if (cola != null) {
                Tiquete tiquete = cola.desencolar();
                tiquete.setAtendidoA(LocalDateTime.now().withSecond(2).withNano(0));
                JOptionPane.showMessageDialog(null, "Tiquete atendido:\n" + tiquete);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opción inválida, ingrese un valor numerico valido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la solicitud", "Error", JOptionPane.ERROR_MESSAGE);
        }          
    }

    public static boolean  validarUsuario(EcoColones ecoColones) {
        while (true) {
            String correo = JOptionPane.showInputDialog("Ingrese el correo asociado para el sistema " + ecoColones.getNombre());
            if(correo == null){
                JOptionPane.showMessageDialog(null, "Cancelando operacion", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(correo.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña");
            if(contrasena == null){
                JOptionPane.showMessageDialog(null, "Cancelando operacion", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(contrasena.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Contraseña no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            ColaUsuarios colaUsuarios = ecoColones.getColaUsuarios();
            NodoGeneric<Usuario> nodo = colaUsuarios.getInicio();
            while(nodo != null){
                Usuario usuario = nodo.getDato();
                if(usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)){
                    return true;
                }
                nodo = nodo.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, "Usuario no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static EcoColones iniciarEcoColones() {
        String nombreBanco = JOptionPane.showInputDialog("Ingrese el nombre del banco");
        if (nombreBanco == null) {
            JOptionPane.showMessageDialog(null, "Cancelando operacion", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (nombreBanco.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre del banco no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        EcoColones ecoColones = EcoColones.getInstance(nombreBanco);
        String cantVentanillasStr = JOptionPane.showInputDialog("Ingrese la cantidad de ventanillas");
        if (cantVentanillasStr == null) {
            JOptionPane.showMessageDialog(null, "Cancelando operacion", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (cantVentanillasStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cantidad de ventanillas no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int cantVentanillas;
        try {
            cantVentanillas = Integer.parseInt(cantVentanillasStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad de ventanillas debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        ColaGenerales colas = new ColaGenerales("Colas Generales");

        for (int i = 0; i < cantVentanillas; i++) {
            colas.encolar(new Cola("General " + (i + 1)));
        }
        ecoColones.getColaHandler().setColasGenerales(colas);
        String correo = JOptionPane.showInputDialog("Ingrese un correo para su usuario");
        if (correo == null) {
            JOptionPane.showMessageDialog(null, "Cancelando operacion", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (correo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String contrasena = JOptionPane.showInputDialog("Ingrese la nueva contraseña del usuario");
        if (contrasena == null) {
            JOptionPane.showMessageDialog(null, "Cancelando Operacion", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (contrasena.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Contraseña no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Usuario usuario = new Usuario(correo, contrasena);
        ecoColones.getColaUsuarios().encolar(usuario);
        ecoColones.save(Constants.FILENAME);
        return ecoColones;
    }
}