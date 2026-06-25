package modulo.vista;

import modulo.vistas.VentanaLogin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author paula
 */
public class Main {
 public static void main(String[] args) {
        /* Configuración del Look and Feel para mantener consistencia visual */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Despliegue síncrono en el hilo de despacho de eventos de Swing (EDT) */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instanciamos la ventana de Login y la hacemos visible
                new VentanaLogin().setVisible(true);
            }
        });
    }
}
