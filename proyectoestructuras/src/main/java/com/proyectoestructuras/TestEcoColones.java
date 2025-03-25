package com.proyectoestructuras;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.proyectoestructuras.controllers.EcoColones;
import com.proyectoestructuras.estructures.Cola;
import com.proyectoestructuras.models.Cliente;
import com.proyectoestructuras.models.TipoTiquete;
import com.proyectoestructuras.models.Tiquete;
import com.proyectoestructuras.models.TiqueteAtentido;
import com.proyectoestructuras.models.Tramite;
import com.proyectoestructuras.models.Usuario;

/**
 * Clase para pruebas automatizadas del sistema EcoColones
 * Permite generar datos de prueba y simular diferentes escenarios
 */
public class TestEcoColones {
    
    private static final String[] NOMBRES = {"Juan", "María", "Carlos", "Ana", "Pedro", "Sofía", "Luis", "Elena", "Diego", "Carmen"};
    private static final String[] APELLIDOS = {"Pérez", "González", "Rodríguez", "López", "Martínez", "Sánchez", "Ramírez", "Torres", "Flores", "Vargas"};
    private static final Random random = new Random();
    private static final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static void main(String[] args) {
        System.out.println("Iniciando pruebas automatizadas de EcoColones...");
        
        // Crear o cargar sistema
        EcoColones ecoColones = cargarOCrearSistema();
        if (ecoColones == null) {
            System.out.println("No se pudo inicializar el sistema. Finalizando pruebas.");
            return;
        }
        
        // Ejecutar pruebas
        probarCreacionTiquetes(ecoColones, 20);
        probarAtencionTiquetes(ecoColones, 15);
        
        // Guardar sistema
        ecoColones.save(Constants.FILENAME);
        System.out.println("Pruebas completadas y sistema guardado correctamente.");
    }
    
    /**
     * Carga el sistema existente o crea uno nuevo para pruebas
     */
    private static EcoColones cargarOCrearSistema() {
        EcoColones ecoColones = EcoColones.load(Constants.FILENAME);
        
        if (ecoColones == null) {
            System.out.println("Creando nuevo sistema para pruebas...");
            ecoColones = EcoColones.getInstance("EcoColones Test");
            
            // Configurar colas generales
            for (int i = 0; i < 3; i++) {
                ecoColones.getColaHandler().getColasGenerales().encolar(new Cola("General " + (i + 1)));
            }
            
            // Crear usuario de prueba
            Usuario usuario = new Usuario("test@ecocolones.com", "test123");
            ecoColones.getColaUsuarios().encolar(usuario);
            
            // Guardar configuración inicial
            ecoColones.save(Constants.FILENAME);
            System.out.println("Sistema de prueba creado con 3 colas generales y usuario test@ecocolones.com/test123");
        } else {
            System.out.println("Sistema cargado correctamente: " + ecoColones.getNombre());
        }
        
        return ecoColones;
    }
    
    /**
     * Genera y agrega múltiples tiquetes aleatorios al sistema
     */
    private static void probarCreacionTiquetes(EcoColones ecoColones, int cantidad) {
        System.out.println("\n=== PRUEBA DE CREACIÓN DE TIQUETES ===");
        System.out.println("Generando " + cantidad + " tiquetes aleatorios...");
        
        for (int i = 0; i < cantidad; i++) {
            // Generar cliente aleatorio
            Cliente cliente = generarClienteAleatorio();
            
            // Seleccionar tipo de tiquete aleatorio
            TipoTiquete tipoTiquete = TipoTiquete.values()[random.nextInt(TipoTiquete.values().length)];
            
            // Seleccionar trámite aleatorio (considerando probabilidad para emergencias)
            Tramite tramite = obtenerTramiteAleatorio();
            
            // Crear tiquete
            Tiquete tiquete = new Tiquete(cliente, tipoTiquete, tramite);
            
            // Encolar según tipo
            Cola cola = null;
            if (tipoTiquete == TipoTiquete.PREFERENCIAL) {
                ecoColones.getColaHandler().getColaPreferencial().encolar(tiquete);
                cola = ecoColones.getColaHandler().getColaPreferencial();
            } else if (tipoTiquete == TipoTiquete.UN_TRAMITE) {
                ecoColones.getColaHandler().getColaRapida().encolar(tiquete);
                cola = ecoColones.getColaHandler().getColaRapida();
            } else {
                cola = ecoColones.getColaHandler().colaGeneralMasDisponible();
                if (cola != null) {
                    cola.encolar(tiquete);
                }
            }
            
            if (cola != null) {
                System.out.printf("Tiquete #%d: %s - %s, %s - encolado en %s\n", 
                    tiquete.getId(), cliente.getNombre(), tipoTiquete, tramite, cola.getColaName());
            }
        }
        
        // Mostrar estadísticas de colas
        imprimirEstadisticasColas(ecoColones);
    }
    
    /**
     * Atiende múltiples tiquetes del sistema
     */
    private static void probarAtencionTiquetes(EcoColones ecoColones, int cantidad) {
        System.out.println("\n=== PRUEBA DE ATENCIÓN DE TIQUETES ===");
        System.out.println("Atendiendo " + cantidad + " tiquetes...");
        
        int atendidos = 0;
        
        // Intentar atender la cantidad especificada
        for (int i = 0; i < cantidad; i++) {
            // Determinar de qué cola atender (priorizar preferenciales, luego rápidas, luego generales)
            Cola cola = null;
            String tipoCola = "";
            
            if (!ecoColones.getColaHandler().getColaPreferencial().estaVacia()) {
                cola = ecoColones.getColaHandler().getColaPreferencial();
                tipoCola = "preferencial";
            } else if (!ecoColones.getColaHandler().getColaRapida().estaVacia()) {
                cola = ecoColones.getColaHandler().getColaRapida();
                tipoCola = "rápida";
            } else {
                // Buscar en colas generales
                for (int j = 0; j < ecoColones.getColaHandler().getColasGenerales().getSize(); j++) {
                    Cola colaGen = ecoColones.getColaHandler().getColasGenerales().get(j);
                    if (!colaGen.estaVacia()) {
                        cola = colaGen;
                        tipoCola = "general " + (j+1);
                        break;
                    }
                }
            }
            
            // Si encontramos una cola con tiquetes, atender
            if (cola != null && !cola.estaVacia()) {
                Tiquete tiquete = cola.desencolar();
                tiquete.setAtendidoA(LocalDateTime.now().format(formateador));
                
                // Crear tiquete atendido
                TiqueteAtentido tiqueteAtendido = new TiqueteAtentido(tiquete, cola.getColaName(), tiquete.getAtendidoA());
                ecoColones.getTiqueteAtentidoJson().agregarTiquete(tiqueteAtendido);
                
                System.out.printf("Atendido tiquete #%d de cola %s: %s - %s\n", 
                    tiquete.getId(), tipoCola, tiquete.getCliente().getNombre(), tiquete.getTipoTramite());
                
                atendidos++;
            } else {
                System.out.println("No hay más tiquetes para atender.");
                break;
            }
        }
        
        System.out.println("Se atendieron " + atendidos + " tiquetes en total.");
        
        // Mostrar estadísticas actualizadas
        imprimirEstadisticasColas(ecoColones);
    }
    
    /**
     * Imprime estadísticas de las colas del sistema
     */
    private static void imprimirEstadisticasColas(EcoColones ecoColones) {
        System.out.println("\n--- ESTADO ACTUAL DE LAS COLAS ---");
        System.out.println("Cola Preferencial: " + ecoColones.getColaHandler().getColaPreferencial().getSize() + " tiquetes");
        System.out.println("Cola Rápida: " + ecoColones.getColaHandler().getColaRapida().getSize() + " tiquetes");
        
        System.out.println("Colas Generales:");
        for (int i = 0; i < ecoColones.getColaHandler().getColasGenerales().getSize(); i++) {
            Cola cola = ecoColones.getColaHandler().getColasGenerales().get(i);
            System.out.println("  - " + cola.getColaName() + ": " + cola.getSize() + " tiquetes");
        }
    }
    
    /**
     * Genera un cliente con datos aleatorios
     */
    private static Cliente generarClienteAleatorio() {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)] + " " + APELLIDOS[random.nextInt(APELLIDOS.length)];
        int edad = 18 + random.nextInt(70); // Edades entre 18 y 87
        return new Cliente(nombre, edad);
    }
    
    /**
     * Genera un trámite aleatorio (con baja probabilidad para emergencias)
     */
    private static Tramite obtenerTramiteAleatorio() {
        // 10% de probabilidad de emergencia
        if (random.nextInt(100) < 10) {
            return Tramite.EMERGENCIA_EJECUTIVO;
        } else {
            // Para los demás trámites, excluir EMERGENCIA_EJECUTIVO (que es el índice 5)
            int indice = random.nextInt(Tramite.values().length - 1);
            if (indice >= 5) indice++; // Saltarse EMERGENCIA_EJECUTIVO
            return Tramite.values()[indice];
        }
    }
}