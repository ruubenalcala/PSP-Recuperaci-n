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
public class TCPClienteAdivinaNumero {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int puerto = 2000;

        try {
            Socket cliente = new Socket(host, puerto);
            System.out.println("Cliente conectado al servidor en la dirección: " + host + ":" + puerto);

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            boolean adivinado = false;

            while (!adivinado) {
                System.out.print("Introduce un número para adivinar el número secreto: ");
                int numeroUsuario = scanner.nextInt();
                flujoSalida.writeInt(numeroUsuario);

                String respuesta = flujoEntrada.readUTF();
                System.out.println(respuesta);

                if (respuesta.contains("¡Felicidades!")) {
                    adivinado = true;
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

