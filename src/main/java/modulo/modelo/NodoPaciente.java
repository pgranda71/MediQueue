/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.modelo;

/**
 *
 * @author paula
 */
public class NodoPaciente {

    public Paciente paciente;
    public NodoPaciente siguiente;

    public NodoPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.siguiente = null;
    }
}

