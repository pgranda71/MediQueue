package modulo.estructuras;

import modulo.modelo.NodoPaciente;
import modulo.modelo.Paciente;

public class GestorAlgoritmos {

    // Busqueda binaria por cedula sobre un arreglo ordenado por cedula
    public Paciente buscarPacienteBinario(Paciente[] arreglo, String cedula, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }
        int medio = (inicio + fin) / 2;
        int comparacion = arreglo[medio].getCedula().compareTo(cedula);

        if (comparacion == 0) {
            return arreglo[medio];
        } else if (comparacion > 0) {
            return buscarPacienteBinario(arreglo, cedula, inicio, medio - 1);
        } else {
            return buscarPacienteBinario(arreglo, cedula, medio + 1, fin);
        }
    }

    // Merge sort recursivo por cedula (para ordenar antes de busqueda binaria)
    public void ordenarHistorialRecursivo(Paciente[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            ordenarHistorialRecursivo(arreglo, inicio, medio);
            ordenarHistorialRecursivo(arreglo, medio + 1, fin);
            combinar(arreglo, inicio, medio, fin);
        }
    }

    public void combinar(Paciente[] arreglo, int inicio, int medio, int fin) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;

        Paciente[] izquierda = new Paciente[n1];
        Paciente[] derecha = new Paciente[n2];

        for (int i = 0; i < n1; i++) izquierda[i] = arreglo[inicio + i];
        for (int j = 0; j < n2; j++) derecha[j] = arreglo[medio + 1 + j];

        int i = 0, j = 0, k = inicio;
        while (i < n1 && j < n2) {
            if (izquierda[i].getCedula().compareTo(derecha[j].getCedula()) <= 0) {
                arreglo[k] = izquierda[i];
                i++;
            } else {
                arreglo[k] = derecha[j];
                j++;
            }
            k++;
        }
        while (i < n1) { arreglo[k] = izquierda[i]; i++; k++; }
        while (j < n2) { arreglo[k] = derecha[j]; j++; k++; }
    }

    // Estadistica recursiva: cuenta pacientes con triaje <= nivelMaximo
    public int calcularEstadisticaTriajeRecursiva(NodoPaciente nodo, int nivelMaximo) {
        if (nodo == null) {
            return 0;
        }
        int conteo = (nodo.paciente.getNivelTriaje() <= nivelMaximo) ? 1 : 0;
        return conteo + calcularEstadisticaTriajeRecursiva(nodo.siguiente, nivelMaximo);
    }
}