/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruube
 */
public class ContadorVocales {

    private static int cuentaTotalDeVocales = 0;

    static class ContadorVocal extends Thread {
        private String texto;
        private char vocal;

        public ContadorVocal(String texto, char vocal) {
            this.texto = texto;
            this.vocal = vocal;
        }

        @Override
        public void run() {
            int contadorVocal = contarVocalEnTexto(texto, vocal);
            System.out.println("Contador de " + vocal + ": " + contadorVocal);
            
            synchronized (ContadorVocales.class) {
                cuentaTotalDeVocales += contadorVocal;
            }
        }

        private int contarVocalEnTexto(String texto, char vocal) {
            int contador = 0;
            for (char letra : texto.toCharArray()) {
                if (Character.toLowerCase(letra) == Character.toLowerCase(vocal)) {
                    contador++;
                }
            }
            return contador;
        }
    }

    public static void main(String[] args) {
        String texto = "Este es un ejemplo de texto con vocales.";

        ContadorVocal contadorVocalA = new ContadorVocal(texto, 'a');
        ContadorVocal contadorVocalE = new ContadorVocal(texto, 'e');
        ContadorVocal contadorVocalI = new ContadorVocal(texto, 'i');
        ContadorVocal contadorVocalO = new ContadorVocal(texto, 'o');
        ContadorVocal contadorVocalU = new ContadorVocal(texto, 'u');

        contadorVocalA.start();
        contadorVocalE.start();
        contadorVocalI.start();
        contadorVocalO.start();
        contadorVocalU.start();

        try {
            contadorVocalA.join();
            contadorVocalE.join();
            contadorVocalI.join();
            contadorVocalO.join();
            contadorVocalU.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cuenta total de vocales: " + cuentaTotalDeVocales);
    }
}

