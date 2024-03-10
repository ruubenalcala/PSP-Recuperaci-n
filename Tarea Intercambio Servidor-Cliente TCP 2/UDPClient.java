/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 *
 * @author ruube
 */
public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            datagramSocket = new DatagramSocket();

            while (true) {
                System.out.print("Introduce un mensaje (o '*' para salir): ");
                String message = reader.readLine();

                if (message.equals("*")) {
                    break;
                }

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        InetAddress.getLocalHost(), 8889);

                datagramSocket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                datagramSocket.receive(receivePacket);
                System.out.println("Respuesta del servidor: " + new String(receivePacket.getData()));
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

