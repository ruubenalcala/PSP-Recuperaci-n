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
public class TCPDadosServidor {
    public static void main(String[] args) {
        int numeroPuerto = 6001;

        try {
            ServerSocket servidor = new ServerSocket(numeroPuerto);
            System.out.println("Esperando al cliente.....");

            Socket clienteConectado = servidor.accept();
            DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            int rondasGanadasCliente = 0;
            int rondasGanadasServidor = 0;

            for (int i = 1; i <= 3; i++) {
                int sumaDados = lanzarDados();

                int apuestaCliente = flujoEntrada.readInt();
                System.out.println("El cliente apuesta: " + apuestaCliente);

                flujoSalida.writeInt(sumaDados);

                if (apuestaCliente == sumaDados) {
                    rondasGanadasCliente++;
                    System.out.println("El cliente ha acertado la ronda " + i);
                } else {
                    rondasGanadasServidor++;
                    System.out.println("El cliente ha fallado la ronda " + i);
                }
            }

            System.out.println("Rondas ganadas por el cliente: " + rondasGanadasCliente);
            System.out.println("Rondas ganadas por el servidor: " + rondasGanadasServidor);

            flujoEntrada.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int lanzarDados() {
        Random rand = new Random();
        int dado1 = rand.nextInt(6) + 1;
        int dado2 = rand.nextInt(6) + 1;

        return dado1 + dado2;
    }
}
