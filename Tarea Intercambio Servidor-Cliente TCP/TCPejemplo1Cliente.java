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
        int Puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket Cliente = new Socket(Host, Puerto);
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        String mensaje;
        do {
            System.out.print("Introduce un mensaje (o * para salir): ");
            mensaje = scanner.nextLine();
            flujoSalida.writeUTF(mensaje);
            DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
            System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

        } while (!mensaje.equals("*"));
        flujoSalida.close();
        Cliente.close();
        scanner.close();
    }
}
