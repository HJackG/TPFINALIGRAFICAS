package org.example.IGraficas;

import org.example.IGraficas.IGraficasAdmin.AdminFrame;
import org.example.IGraficas.IGraficasCliente.ClienteFrame;
import org.example.model.entity.Admin;
import org.example.model.entity.Cliente;
import org.example.menu.MenuMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginFrame extends JFrame {
    private final MenuMain menuMain;
    private final boolean isAdmin;
    private final MainFrame mainFrame;

    public LoginFrame(MenuMain menuMain, boolean isAdmin ) {
        this.menuMain = menuMain;
        this.isAdmin = isAdmin;
        this.mainFrame = new MainFrame(menuMain);
        initUI();
    }

    private void initUI() {
        setTitle(isAdmin ? "Loguearse administrador" : "Loguearse cliente");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(10, 20, 80, 25);
        panel.add(dniLabel);

        JTextField dniField = new JTextField(20);
        dniField.setBounds(100, 20, 165, 25);
        panel.add(dniField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Ingresar");
        loginButton.setBounds(10, 100, 80, 25);
        panel.add(loginButton);

        JButton closeButton = new JButton("Cerrar");
        closeButton.setBounds(120, 100, 80, 25);
        panel.add(closeButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String dni = dniField.getText();
                String password = new String(passwordField.getPassword());

                if (isAdmin) {
                    Optional<Admin> admin = menuMain.adminController.login(dni, password);
                    if (admin.isPresent()) {
                        new AdminFrame(menuMain,menuMain.adminController, admin.get()).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Admin no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Optional<Cliente> cliente = menuMain.clienteController.login(dni, password);
                    if (cliente.isPresent()) {
                        Cliente foundCliente = cliente.get();
                        if (foundCliente.isUserActive()) {
                            ClienteFrame clienteFrame = new ClienteFrame(foundCliente, menuMain);
                            clienteFrame.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(panel, "Cliente no activo", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana de registro

                // Vuelve a abrir la ventana principal
                mainFrame.setVisible(true);
            }
        });


        /*if (mainMenu != null) {
                    mainMenu.setVisible(true);
                }*/
    }
}

