/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruube
 */
public class ProgramacionHilos extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hola Mundo desde el hilo " + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ProgramacionHilos hilo = new ProgramacionHilos();
            hilo.start();
        }
    }
}
