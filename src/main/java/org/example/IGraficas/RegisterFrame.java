package org.example.IGraficas;

import org.example.model.entity.Admin;
import org.example.model.entity.Cliente;
import org.example.model.entity.Config;
import org.example.menu.MenuMain;
import org.example.model.repository.ClienteRepository;
import org.example.model.repository.ConfigRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private final MenuMain menuMain;
    private static final Config config = ConfigRepository.loadConfig();
    private final MainFrame mainFrame;
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public RegisterFrame(MenuMain menuMain ) {
        this.menuMain = menuMain;
        mainFrame = new MainFrame(menuMain);
        initUI();
    }

    private void initUI() {
        setTitle("Registrar cliente");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        JLabel dniLabel = new JLabel("DNI:");
        JTextField dniField = new JTextField(20);
        panel.add(dniLabel);
        panel.add(dniField);

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField(20);
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel lastNameLabel = new JLabel("Apellido:");
        JTextField lastNameField = new JTextField(20);
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        JLabel ageLabel = new JLabel("Edad:");
        JTextField ageField = new JTextField(20);
        panel.add(ageLabel);
        panel.add(ageField);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        panel.add(emailLabel);
        panel.add(emailField);

        JLabel phoneLabel = new JLabel("Teléfono:");
        JTextField phoneField = new JTextField(20);
        panel.add(phoneLabel);
        panel.add(phoneField);

        JLabel addressLabel = new JLabel("Dirección:");
        JTextField addressField = new JTextField(20);
        panel.add(addressLabel);
        panel.add(addressField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(20);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton registerButton = new JButton("Registrar");

        panel.add(registerButton);

        JButton closeButton = new JButton("  Cerrar  ");
        panel.add(closeButton);



        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String dni = dniField.getText();
                    String name = nameField.getText();
                    String lastName = lastNameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String email = emailField.getText();
                    String phone = phoneField.getText();
                    String address = addressField.getText();
                    String password = new String(passwordField.getPassword());

                    Cliente newCliente = new Cliente(dni, name, lastName, age, email, phone, address, password, false);

                    if (clienteRepository.dniCheck(dni)) {
                        if (!clienteRepository.isActive(dni)) { // Verificar si el cliente está dado de baja
                            // Dar de alta al cliente
                            clienteRepository.setActive(dni, true); // Suponiendo un método setActive en clienteRepository
                            JOptionPane.showMessageDialog(panel, "Usuario dado de alta exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            mainFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(panel, "DNI ya registrado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (config.getAdminPassword().equalsIgnoreCase(password)) {
                            JTextField departmentField = new JTextField(20);
                            JTextField specialityField = new JTextField(20);
                            JTextField passwordField = new JTextField(20);

                            Object[] message = {
                                    "Contraseña correcta para admin. Ingrese datos adicionales:",
                                    "Contraseña:", passwordField,
                                    "Departamento:", departmentField,
                                    "Especialidad:", specialityField
                            };

                            int option = JOptionPane.showConfirmDialog(panel, message, "Registro de administrador", JOptionPane.OK_CANCEL_OPTION);

                            if (option == JOptionPane.OK_OPTION) {
                                String department = departmentField.getText();
                                String speciality = specialityField.getText();
                                password = passwordField.getText();
                                Admin newAdmin = new Admin(dni, name, lastName, age, email, phone, address, password, true, department, speciality);
                                menuMain.adminRepository.Register(newAdmin);
                                JOptionPane.showMessageDialog(panel, "Registro de Administrador exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            clienteRepository.Register(newCliente);
                            JOptionPane.showMessageDialog(panel, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                        dispose();
                        mainFrame.setVisible(true);

                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(panel, "Edad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "Error en el registro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    }
}



