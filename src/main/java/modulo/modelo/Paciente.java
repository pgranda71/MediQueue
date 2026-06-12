/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo.modelo;

/**
 *
 * @author paula
 */
public class Paciente {

    private String cedula;
    private String nombre;
    private int nivelTriaje;

    public Paciente(String cedula, String nombre, int nivelTriaje) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.nivelTriaje = nivelTriaje;
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public int getNivelTriaje() { return nivelTriaje; }
}

