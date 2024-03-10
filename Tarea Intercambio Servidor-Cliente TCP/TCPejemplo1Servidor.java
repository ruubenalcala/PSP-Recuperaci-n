/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author ruube
 */
public class TCPejemplo1Servidor {
    public static void main(String[] arg) throws IOException {
        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);

        System.out.println("Esperando al cliente.....");
        Socket clienteConectado = servidor.accept();
        InputStream entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);
        DataOutputStream flujoDeSalida = new DataOutputStream(clienteConectado.getOutputStream());

        String mensajeCliente;
        do {
            mensajeCliente = flujoEntrada.readUTF();
            System.out.println("Recibiendo del CLIENTE: \n \t" + mensajeCliente);
            flujoDeSalida.writeUTF(mensajeCliente.toUpperCase());
        } while (!mensajeCliente.equals("*"));
        entrada.close();
        flujoEntrada.close();
        flujoDeSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}
