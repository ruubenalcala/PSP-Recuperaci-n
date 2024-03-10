/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
/**
 *
 * @author ruube
 */
public class generador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
            int numInstancias = 10;

        for (int i = 0; i < numInstancias; i++) {
            System.out.println("Instancia " + (i + 1));
            System.out.println("Ingresa la cantidad de cadenas a generar:");
            
                int numCadenas = scanner.nextInt();
                cadenas.main(new String[]{String.valueOf(numCadenas)});
                
            System.out.println("--------------");
        }

        scanner.close();
    }
}
