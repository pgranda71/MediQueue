/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.estructuras;

import modulo.modelo.NodoPaciente;
import modulo.modelo.Paciente;

/**
 *
 * @author paula
 */
public class ColaPrioridadHospital {
    private NodoPaciente frente;

    public ColaPrioridadHospital() {
        this.frente = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void insertar(Paciente nuevoPaciente) {
        NodoPaciente nuevoNodo = new NodoPaciente(nuevoPaciente);

        if (estaVacia() || nuevoPaciente.getNivelTriaje() < frente.paciente.getNivelTriaje()) {
            nuevoNodo.siguiente = frente;
            frente = nuevoNodo;
        } else {
            NodoPaciente actual = frente;
            while (actual.siguiente != null && actual.siguiente.paciente.getNivelTriaje() <= nuevoPaciente.getNivelTriaje()) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
    }

    public Paciente atender() {
        if (estaVacia()) {
            return null;
        }
        Paciente atendido = frente.paciente;
        frente = frente.siguiente;
        return atendido;
    }

    public int obtenerCantidadPacientes() {
        int contador = 0;
        NodoPaciente actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public Paciente[] transformarAArreglo() {
        int tamaño = obtenerCantidadPacientes();
        Paciente[] arreglo = new Paciente[tamaño];
        
        NodoPaciente actual = frente;
        int indice = 0;
        while (actual != null) {
            arreglo[indice] = actual.paciente;
            indice++;
            actual = actual.siguiente;
        }
        return arreglo; 
    }
    
    public NodoPaciente getFrente() {
    return this.frente; 
 
}
}
