/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
/**
 *
 * @author ruube
 */
public class frecuencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Ingresa alguna palabra o frase:");
        String texto = scanner.nextLine().toLowerCase(); 
        
        
        HashMap<Character, Integer> frecuenciaVocales = calcularFrecuenciaVocales(texto);
        
        
        System.out.println("Frecuencia de vocales:");
        for (char vocal : "aeiou".toCharArray()) {
            int frecuencia = frecuenciaVocales.getOrDefault(vocal, 0);
            System.out.print(frecuencia + " ");
        }

        
        guardarEnFichero(frecuenciaVocales);
    }

    private static HashMap<Character, Integer> calcularFrecuenciaVocales(String texto) {
        HashMap<Character, Integer> frecuenciaVocales = new HashMap<>();

        for (char letra : texto.toCharArray()) {
            if ("aeiou".indexOf(letra) != -1) {
                frecuenciaVocales.put(letra, frecuenciaVocales.getOrDefault(letra, 0) + 1);
            }
        }

        return frecuenciaVocales;
    }

    private static void guardarEnFichero(HashMap<Character, Integer> frecuenciaVocales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado_frecuencia.txt"))) {
            for (char vocal : "aeiou".toCharArray()) {
                int frecuencia = frecuenciaVocales.getOrDefault(vocal, 0);
                writer.write(vocal + ": " + frecuencia + "\n");
            }
            System.out.println("Resultado de frecuencia guardado en 'resultado_frecuencia.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
