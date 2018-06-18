/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cortofinalpoo;

import Vista.*;
import javax.swing.JFrame;

/**
 *
 * @author LN710Q
 */
public class CortoFinalPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Ventana");
        ventana.setContentPane(new Consulta());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setVisible(true);
    }
    
}
