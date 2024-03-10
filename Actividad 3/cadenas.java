/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author ruube
 */
public class cadenas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese la cantidad de cadenas a generar:");
        int cantidadCadenas = scanner.nextInt();
        
        for (int i = 0; i < cantidadCadenas; i++) {
            String cadena = generarCadenaAleatoria();
            System.out.println("Cadena generada: " + cadena);

            try {
                ProcessBuilder processBuilder = new ProcessBuilder("java", "FrecuenciaVocales");
                Process process = processBuilder.start();
                process.getOutputStream().write(cadena.getBytes());
                process.getOutputStream().close();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generarCadenaAleatoria() {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        
        int longitud = random.nextInt(20) + 1;
        StringBuilder cadena = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            char caracter = alfabeto.charAt(random.nextInt(alfabeto.length()));
            cadena.append(caracter);
        }

        return cadena.toString();
    }
}
