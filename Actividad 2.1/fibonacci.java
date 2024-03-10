/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruube
 */
public class fibonacci extends Thread {
    private final int N;

    public fibonacci(int n) {
        this.N = n;
    }

    @Override
    public void run() {
        int primero = 1, segundo = 1;

        System.out.println("Sucesión de Fibonacci para los primeros " + N + " números:");

        for (int i = 0; i < N; i++) {
            System.out.print(primero + " ");

            int siguiente = primero + segundo;
            primero = segundo;
            segundo = siguiente;
        }
    }

    public static void main(String[] args) {
        fibonacci fibonacci = new fibonacci(10);
        fibonacci.start();
    }
}
