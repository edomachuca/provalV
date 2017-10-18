/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyvalpo;

import java.io.IOException;

/**
 *
 * @author Mazhuka
 */
public class ProyValpo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Analisis Valpo=new Analisis("EL PUERTO.xlsx");
        Valpo.Lectura();
        System.out.println("Lectura completada");
        Valpo.SepararDatos();
        System.out.println("Datos Separadas, preparando escritura de salida.");
        Valpo.CrearLibros();
        System.out.println("Terminado.! :D");
    }
    
}
