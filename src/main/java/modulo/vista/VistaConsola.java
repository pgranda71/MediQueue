/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.vista;

import java.util.Scanner;
import modulo.estructuras.ColaPrioridadHospital;
import modulo.modelo.Paciente;
import modulo.estructuras.GestorAlgoritmos;
import modulo.estructuras.ListaHistorialAtendidos;

/**
 *
 * @author paula
 */
public class VistaConsola {
private ColaPrioridadHospital colaEspera;
    private ListaHistorialAtendidos historial;
    private GestorAlgoritmos gestorAlgoritmos;
    private Scanner teclado;

    public VistaConsola() {
        this.colaEspera = new ColaPrioridadHospital();
        this.teclado = new Scanner(System.in);
        this.historial = new ListaHistorialAtendidos();
        this.gestorAlgoritmos = new GestorAlgoritmos();
    }

    public void iniciarSistema() {
        int opcion;
        do {
            mostrarMenu();
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    procesarRegistro();
                    break;
                case 2:
                    procesarAtencion();
                    break;
                case 3:
                    procesarMonitor();
                    break;
                case 4:
                    procesarBusqueda();
                    break;
                case 5:
                    procesarReporte();
                    break;
                case 6:
                    System.out.println("Cerrando sesion de seguridad en MediQueue...");
                    break;
                default:
                    System.out.println("Error: Opcion invalida.");
            }
        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("\n==================================================");
        System.out.println("    MEDIQUEUE PANEL - SIMULACION VISTA 1   ");
        System.out.println("==================================================");
        System.out.println("[1] Nuevo Ingreso (Registrar Paciente)");
        System.out.println("[2] Llamar Paciente (Atencion Medica)");
        System.out.println("[3]  Ver Monitor de la Sala de Espera");
        System.out.println("[4] Buscar Paciente en Historial");
        System.out.println("[5] Ver Reporte de Atendidos");
        System.out.println("[6] Salir del Sistema");
        System.out.print("Seleccione una opcion: ");
    }

    private void procesarRegistro() {
        System.out.println("\n--- FORMULARIO DE TRIAJE - SIMULACION VISTA 2 ---");
        System.out.print("Numero de Cedula: ");
        String cedula = teclado.nextLine();
        System.out.print("Nombre Completo del Paciente: ");
        String nombre = teclado.nextLine();
        System.out.println("Botones de Gravedad : (1:Rojo, 2:Naranja, 3:Amarillo, 4:Verde, 5:Azul)");
        System.out.print("Presione un boton de triaje (1-5): ");
        int triaje = teclado.nextInt();

        if (triaje >= 1 && triaje <= 5) {
            Paciente nuevo = new Paciente(cedula, nombre, triaje);
            colaEspera.insertar(nuevo);
            System.out.println("[EXITO]: Nodo enlazado correctamente. Redirigiendo al Panel Principal...");
        } else {
            System.out.println("[ERROR]: Nivel de triaje fuera de rango estandar.");
        }
    }

    private void procesarAtencion() {
        Paciente atendido = colaEspera.atender();
        if (atendido != null) {
            historial.registrarAtencion(atendido);
            System.out.println("\n*** [PANTALLA EMERGENTE]: ATENDIENDO PACIENTE CRITICO ***");
            System.out.println("Paciente: " + atendido.getNombre() + " | Cedula: " + atendido.getCedula());
            System.out.println("Nivel de Gravedad Despachado: " + atendido.getNivelTriaje());
        } else {
            System.out.println("\n[PANTALLA EMERGENTE]: No existen pacientes en la cola de espera.");
        }
    }

    private void procesarMonitor() {
        System.out.println("\n--- MONITOR DE SALA DE ESPERA - SIMULACION VISTA 3 ---");
        System.out.println("Total de pacientes en fila activa: " + colaEspera.obtenerCantidadPacientes());
        
        Paciente[] listaParaTabla = colaEspera.transformarAArreglo();
        
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-10s\n", "CEDULA", "PACIENTE", "TRIAJE");
        System.out.println("--------------------------------------------------");
        for (Paciente p : listaParaTabla) {
            System.out.printf("%-12s | %-20s | Nivel %d\n", p.getCedula(), p.getNombre(), p.getNivelTriaje());
        }
        System.out.println("--------------------------------------------------");
    }

    private void procesarBusqueda() {
        System.out.println("\n--- BUSQUEDA BINARIA EN HISTORIAL ---");
        if (historial.obtenerTotal() == 0) {
            System.out.println("[INFO]: No hay pacientes en el historial todavia.");
            return;
        }
        System.out.print("Ingrese la cedula a buscar: ");
        String cedula = teclado.nextLine();

        Paciente[] arreglo = historial.obtenerHistorialComoArreglo();
        gestorAlgoritmos.ordenarHistorialRecursivo(arreglo, 0, arreglo.length - 1);
        Paciente resultado = gestorAlgoritmos.buscarPacienteBinario(arreglo, cedula, 0, arreglo.length - 1);

        if (resultado != null) {
            System.out.println("[ENCONTRADO]: " + resultado.getNombre() + " | Triaje: " + resultado.getNivelTriaje());
        } else {
            System.out.println("[NO ENCONTRADO]: La cedula " + cedula + " no esta en el historial.");
        }
    }

    private void procesarReporte() {
        System.out.println("\n--- REPORTE DE PACIENTES ATENDIDOS ---");
        System.out.println("Total atendidos: " + historial.obtenerTotal());
        Paciente[] atendidos = historial.obtenerHistorialComoArreglo();
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-10s\n", "CEDULA", "PACIENTE", "TRIAJE");
        System.out.println("--------------------------------------------------");
        for (Paciente p : atendidos) {
            System.out.printf("%-12s | %-20s | Nivel %d\n", p.getCedula(), p.getNombre(), p.getNivelTriaje());
        }
        System.out.println("--------------------------------------------------");
    }
}
