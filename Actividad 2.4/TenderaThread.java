/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruube
 */
public class TenderaThread extends Thread{
    private static String nombre;
    private static Cliente cliente;
    private static long timeStamp;

    public TenderaThread(String nombre, Cliente cliente, long timeStamp) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.timeStamp = timeStamp;
    }

    @Override
    public void run() {
        System.out.println("La Tendera " + this.nombre +
                " COMIENZA A COBRAR LA COMPRA DEL CLIENTE " + cliente.getNombre() +
                " EN: " + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");

        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Cobrando el producto " + (i + 1) + " ->en: " +
                    (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        }

        System.out.println("La Tendera " + this.nombre + " HA TERMINADO DE COBRAR " +
                cliente.getNombre() + " EN: " + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}


