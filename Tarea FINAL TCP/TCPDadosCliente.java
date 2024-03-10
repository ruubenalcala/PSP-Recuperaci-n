/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ruube
 */
public class TCPDadosCliente {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int puerto = 6001;

        try {
            Socket cliente = new Socket(host, puerto);
            Scanner scanner = new Scanner(System.in);

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            for (int i = 1; i <= 3; i++) {
                System.out.println("Ronda " + i);
                System.out.print("Adivina la suma de dos dados (2-12): ");
                int apuesta = scanner.nextInt();

                flujoSalida.writeInt(apuesta);

                int resultado = flujoEntrada.readInt();
                System.out.println("El servidor dice que la suma es: " + resultado);
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
