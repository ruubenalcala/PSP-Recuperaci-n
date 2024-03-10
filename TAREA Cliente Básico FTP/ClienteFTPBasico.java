/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import javax.swing.event.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author ruube
 */

import javax.swing.*;
import org.apache.commons.net.ftp.*;
import java.awt.*;

public class ClienteFTPBasico extends JFrame {
    private JTextField servidorField;
    private JTextField usuarioField;
    private JPasswordField passwordField;
    private JButton conectarButton;
    private JButton botonCargar;
    private JButton botonDescargar;
    private JButton botonBorrar;
    private JButton botonCreaDir;
    private JButton botonDelDir;
    private JButton botonSalir;
    private JTextArea logArea;

    private FTPClient cliente;

    public ClienteFTPBasico() {
        super("Cliente FTP Básico");
        initComponents();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Panel de conexión
        JPanel panelConexion = new JPanel(new GridLayout(4, 2));
        panelConexion.setBorder(BorderFactory.createTitledBorder("Conexión FTP"));

        servidorField = new JTextField();
        usuarioField = new JTextField();
        passwordField = new JPasswordField();
        conectarButton = new JButton("Conectar");

        panelConexion.add(new JLabel("Servidor FTP:"));
        panelConexion.add(servidorField);
        panelConexion.add(new JLabel("Usuario:"));
        panelConexion.add(usuarioField);
        panelConexion.add(new JLabel("Contraseña:"));
        panelConexion.add(passwordField);
        panelConexion.add(new JLabel()); // Espacio en blanco
        panelConexion.add(conectarButton);

        contentPane.add(panelConexion, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 1));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botonCargar = new JButton("Subir fichero");
        botonDescargar = new JButton("Descargar fichero");
        botonBorrar = new JButton("Eliminar fichero");
        botonCreaDir = new JButton("Crear carpeta");
        botonDelDir = new JButton("Eliminar carpeta");
        botonSalir = new JButton("Salir");

        botonCargar.setEnabled(false);
        botonDescargar.setEnabled(false);
        botonBorrar.setEnabled(false);
        botonCreaDir.setEnabled(false);
        botonDelDir.setEnabled(false);

        panelBotones.add(botonCargar);
        panelBotones.add(botonDescargar);
        panelBotones.add(botonBorrar);
        panelBotones.add(botonCreaDir);
        panelBotones.add(botonDelDir);

        contentPane.add(panelBotones, BorderLayout.WEST);

        // Área de registro
        logArea = new JTextArea();
        logArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(logArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupListeners() {
        conectarButton.addActionListener(e -> conectarFTP());

        botonSalir.addActionListener(e -> {
            if (cliente != null && cliente.isConnected()) {
                try {
                    cliente.logout();
                    cliente.disconnect();
                } catch (Exception ex) {
                    log("Error al desconectar del servidor FTP: " + ex.getMessage());
                }
            }
            dispose();
        });
    }

    private void conectarFTP() {
        String servidor = servidorField.getText().trim();
        String usuario = usuarioField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (servidor.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
            log("Por favor, introduzca el servidor FTP, usuario y contraseña.");
            return;
        }

        cliente = new FTPClient();

        try {
            cliente.connect(servidor);
            boolean success = cliente.login(usuario, password);
            if (success) {
                log("Conexión exitosa al servidor FTP.");
                habilitarBotones();
            } else {
                log("Error de autenticación. Por favor, revise sus credenciales.");
            }
        } catch (Exception e) {
            log("Error al conectar al servidor FTP: " + e.getMessage());
        }
    }

    private void habilitarBotones() {
        botonCargar.setEnabled(true);
        botonDescargar.setEnabled(true);
        botonBorrar.setEnabled(true);
        botonCreaDir.setEnabled(true);
        botonDelDir.setEnabled(true);
    }

    private void log(String mensaje) {
        logArea.append(mensaje + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteFTPBasico::new);
    }
}
