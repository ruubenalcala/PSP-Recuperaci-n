/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruube
 */
public class MainThread {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Pedro", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Charo", new int[] { 1, 3, 5, 1, 1 });
        
        long initialTime = System.currentTimeMillis();

        TenderaThread tenderaThread1 = new TenderaThread("Patricia", cliente1, initialTime);
        TenderaThread tenderaThread2 = new TenderaThread("Juan", cliente2, initialTime);

        tenderaThread1.start();
        tenderaThread2.start();

        try {
            tenderaThread1.join();
            tenderaThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long totalTime = System.currentTimeMillis() - initialTime;
        System.out.println("Tiempo total de procesamiento: " + totalTime / 1000 + " segundos");
    }
}



