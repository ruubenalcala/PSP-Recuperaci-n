/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
/**
 *
 * @author ruube
 */
public class TCPServidorAdivinaNumero {
    public static void main(String[] args) {
        int numeroPuerto = 2000;
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(numeroPuerto);
            System.out.println("Servidor esperando en el puerto " + numeroPuerto + "....");

            Socket clienteConectado = servidor.accept();
            System.out.println("Cliente conectado desde la dirección: " + clienteConectado.getInetAddress());

            DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            int numeroSecreto = generarNumeroSecreto();
            System.out.println("Número secreto generado por el servidor: " + numeroSecreto);

            int intentos = 0;
            boolean adivinado = false;

            while (!adivinado) {
                int numeroCliente = flujoEntrada.readInt();
                intentos++;

                if (numeroCliente == numeroSecreto) {
                    flujoSalida.writeUTF("¡Felicidades! Has adivinado el número secreto. Total de intentos: " + intentos);
                    adivinado = true;
                } else if (numeroCliente < numeroSecreto) {
                    flujoSalida.writeUTF("El número secreto es mayor. Inténtalo de nuevo.");
                } else {
                    flujoSalida.writeUTF("El número secreto es menor. Inténtalo de nuevo.");
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int generarNumeroSecreto() {
        Random rand = new Random();
        return rand.nextInt(101);
    }
}