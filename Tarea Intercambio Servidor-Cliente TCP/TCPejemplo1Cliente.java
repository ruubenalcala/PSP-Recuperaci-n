/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author ruube
 */
public class TCPejemplo1Cliente {
    public static void main(String[] args) throws Exception {
        String Host = "127.0.0.1";
        int Puerto = 6000;//puerto remoto

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        // CREO FLUJO DE ENTRADA DESDE EL TECLADO
        Scanner scanner = new Scanner(System.in);

        String mensaje;
        do {
            System.out.print("Introduce un mensaje (o * para salir): ");
            mensaje = scanner.nextLine();

            // ENVÍO EL MENSAJE AL SERVIDOR
            flujoSalida.writeUTF(mensaje);

            // CREO FLUJO DE ENTRADA DESDE EL SERVIDOR
            DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

            // RECIBO EL MENSAJE EN MAYÚSCULAS DEL SERVIDOR
            System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

        } while (!mensaje.equals("*"));

        // CERRAR STREAMS Y SOCKETS
        flujoSalida.close();
        Cliente.close();
        scanner.close();
    }// main
}
