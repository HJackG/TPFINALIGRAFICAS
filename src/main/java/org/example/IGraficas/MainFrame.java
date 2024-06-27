package org.example.IGraficas;

import org.example.menu.MenuMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final MenuMain menuMain;

    public MainFrame(MenuMain menuMain) {
        this.menuMain = menuMain;
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de gestión de biblioteca");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);


        JButton loginButton = new JButton("Loguearse como cliente");
        loginButton.setBounds(200, 90,300 , 40);
        panel.add(loginButton);

        JButton adminLoginButton = new JButton("Loguearse como administrador");
        adminLoginButton.setBounds(200, 150, 300, 40);
        panel.add(adminLoginButton);

        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(200, 210, 300, 40);
        panel.add(registerButton);

        JButton exitButton = new JButton("Salir");
        exitButton.setBounds(200, 270, 300, 40);
        panel.add(exitButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                new LoginFrame(menuMain, false ).setVisible(true);
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                new LoginFrame(menuMain, true).setVisible(true);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                new RegisterFrame(menuMain).setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                menuMain.finalizarPrograma();
                System.exit(0);
            }
        });
    }

    private ImageIcon loadImage(String path) {
        try {
            // Leer la imagen desde el archivo
            File imgFile = new File(path);
            Image image = ImageIO.read(imgFile);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
