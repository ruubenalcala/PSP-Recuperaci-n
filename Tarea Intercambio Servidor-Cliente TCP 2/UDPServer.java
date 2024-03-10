/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author ruube
 */
public class UDPServer {
    private static final int ECHOMAX = 255;

    public static void main(String[] args) {
        byte[] sendData = "Bienvenido!".getBytes();
        DatagramSocket datagramSocket = null;

        try {
            datagramSocket = new DatagramSocket(8889);

            DatagramPacket receivePacket = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
            DatagramPacket sendPacket;

            while (true) {
                datagramSocket.receive(receivePacket);
                System.out.println("Recibiendo del Cliente en " + receivePacket.getSocketAddress());
                System.out.println("El Cliente dice: " + new String(receivePacket.getData()));

                sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getSocketAddress());
                datagramSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}

