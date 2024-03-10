/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;

/**
 *
 * @author ruube
 */
public class AbreBlocNotas {
    public static void main(String[] args) {
        ProcessBuilder procesoBlocDeNotas = new ProcessBuilder("notepad.exe");

        try {
            Process proceso = procesoBlocDeNotas.start();
            
            System.out.println("¿Bloc de notas está en ejecución?: " + proceso.isAlive());
            
            Thread.sleep(20000);
            
            System.out.println("Después de la pausa, ¿Bloc de notas está en ejecución?: " + proceso.isAlive());
            
            proceso.destroy();
            
            System.out.println("Después de destruir, ¿Bloc de notas está en ejecución?: " + proceso.isAlive());
            
            proceso.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
