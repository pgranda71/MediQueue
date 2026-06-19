package modulo.estructuras;

import modulo.modelo.NodoPaciente;
import modulo.modelo.Paciente;

public class ListaHistorialAtendidos {

    private NodoPaciente cabeza;
    private int totalAtendidos;

    public ListaHistorialAtendidos() {
        this.cabeza = null;
        this.totalAtendidos = 0;
    }

    public void registrarAtencion(Paciente paciente) {
        NodoPaciente nuevoNodo = new NodoPaciente(paciente);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        totalAtendidos++;
    }

    public Paciente[] obtenerHistorialComoArreglo() {
        Paciente[] arreglo = new Paciente[totalAtendidos];
        NodoPaciente actual = cabeza;
        int indice = 0;
        while (actual != null) {
            arreglo[indice] = actual.paciente;
            indice++;
            actual = actual.siguiente;
        }
        return arreglo;
    }

    public int obtenerTotal() {
        return totalAtendidos;
    }
}