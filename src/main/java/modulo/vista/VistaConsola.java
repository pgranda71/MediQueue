/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.vista;

import java.util.Scanner;
import modulo.estructuras.ColaPrioridadHospital;
import modulo.modelo.Paciente;

/**
 *
 * @author paula
 */
public class VistaConsola {
private ColaPrioridadHospital colaEspera;
    private Scanner teclado;

    public VistaConsola() {
        this.colaEspera = new ColaPrioridadHospital();
        this.teclado = new Scanner(System.in);
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
                    System.out.println("Cerrando sesion de seguridad en MediQueue...");
                    break;
                default:
                    System.out.println("Error: Opcion invalida.");
            }
        } while (opcion != 4);
    }

    private void mostrarMenu() {
        System.out.println("\n==================================================");
        System.out.println("    MEDIQUEUE PANEL - SIMULACION VISTA 1   ");
        System.out.println("==================================================");
        System.out.println("[1] Nuevo Ingreso (Registrar Paciente)");
        System.out.println("[2] Llamar Paciente (Atencion Medica)");
        System.out.println("[3]  Ver Monitor de la Sala de Espera");
        System.out.println("[4] Salir del Sistema");
        System.out.print("Simule un clic presionando una opcion: ");
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
}
