/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
/**
 *
 * @author ruube
 */

public class DescifradorAES {

    public static void main(String[] args) {
        byte[] datosCifrados = {
            (byte) 0xEC, (byte) 0xC4, (byte) 0xD5, (byte) 0x89,
            (byte) 0x02, (byte) 0xE3, (byte) 0xD5, (byte) 0xCC,
            (byte) 0x5E, (byte) 0xC6, (byte) 0xAF, (byte) 0x6C,
            (byte) 0x61, (byte) 0x8B, (byte) 0xC2, (byte) 0xA5
        };

        descifrarConContraseña(datosCifrados, "AES/ECB/PKCS5Padding");
        descifrarConContraseña(datosCifrados, "AES/CBC/PKCS5Padding");
    }

    private static void descifrarConContraseña(byte[] datosCifrados, String contraseña) {
    try {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] clave = sha.digest(contraseña.getBytes("UTF-8"));
        clave = Arrays.copyOf(clave, 16); // Clave de 128 bits

        Cipher cifrador = Cipher.getInstance("AES/ECB/NoPadding");
        cifrador.init(Cipher.DECRYPT_MODE, new SecretKeySpec(clave, "AES"));

        byte[] datosDescifrados = cifrador.doFinal(datosCifrados);
        System.out.println("Contraseña " + contraseña + " - Descifrado exitoso: " + new String(datosDescifrados, "UTF-8"));
    } catch (Exception e) {
        System.out.println("Contraseña " + contraseña + " - Descifrado fallido: " + e.getMessage());
    }
}

    
}



