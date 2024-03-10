/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
/**
 *
 * @author ruube
 */

public class ClienteFTP {

    public static void main(String[] args) throws SocketException, IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String clave = scanner.nextLine();

        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es"; 
        System.out.println("Conectar a: " + servFTP);
        cliente.connect(servFTP);

        int respuesta = cliente.getReplyCode();

        System.out.println("Respuesta: " + respuesta);
        System.out.println("Texto Respuesta: " + cliente.getReplyString());

        boolean loginCorrecto = cliente.login(usuario, clave);

        if (loginCorrecto) {
            System.out.println("Directorio Actual: " + cliente.printWorkingDirectory());

            FTPFile[] files = cliente.listFiles();

            System.out.println("Archivos en el directorio raíz de RedIris:");

            for (FTPFile file : files) {
                if (file.isFile()) {
                    System.out.println("\t" + file.getName());
                }
            }

            System.out.print("Ingrese el nombre del archivo que desea descargar: ");
            String nombreArchivo = scanner.nextLine();

            descargarArchivo(cliente, nombreArchivo);

        } else {
            System.out.println("Error de autenticación. Por favor, revise sus credenciales.");
        }

        cliente.disconnect();
        System.out.println("Fin.");
    }

    private static void descargarArchivo(FTPClient cliente, String nombreArchivo) throws IOException {
        System.out.print("Ingrese la carpeta local donde desea guardar el archivo: ");
        Scanner scanner = new Scanner(System.in);
        String carpetaLocal = scanner.nextLine();

        System.out.print("Ingrese el nombre para el archivo descargado: ");
        String nombreLocal = scanner.nextLine();

        try (FileOutputStream fos = new FileOutputStream(carpetaLocal + "/" + nombreLocal)) {
            boolean descargaExitosa = cliente.retrieveFile(nombreArchivo, fos);
            if (descargaExitosa) {
                System.out.println("Descarga exitosa. El archivo se guardó en: " + carpetaLocal + "/" + nombreLocal);
            } else {
                System.out.println("Error al descargar el archivo.");
            }
        }
    }
}

