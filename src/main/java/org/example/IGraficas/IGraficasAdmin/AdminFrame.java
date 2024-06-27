package org.example.IGraficas.IGraficasAdmin;

import org.example.IGraficas.MainFrame;
import org.example.controller.AdminController;
import org.example.menu.MenuMain;
import org.example.model.entity.Admin;
import org.example.model.entity.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class AdminFrame extends JFrame {
    private final MainFrame mainFrame;
    private final AdminController adminController;

    public AdminFrame(MenuMain menuMain, AdminController adminController, Admin admin) {
        mainFrame = new MainFrame(menuMain);
        this.adminController = adminController;
        initUI();
    }

    private void initUI() {
        setTitle("Admin panel");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);


        JButton addButton = new JButton("Agregar libro");
        addButton.setBounds(200, 60, 300, 40);
        panel.add(addButton);

        JButton editButton = new JButton("Editar libro");
        editButton.setBounds(200, 120, 300, 40);
        panel.add(editButton);

        JButton listButton = new JButton("Listar libro");
        listButton.setBounds(200, 180, 300, 40);
        panel.add(listButton);

        JButton searchButton = new JButton("Buscar libro");
        searchButton.setBounds(200, 240, 300, 40);
        panel.add(searchButton);

        JButton deleteButton = new JButton("Deshabilitar/Habilitar libro");
        deleteButton.setBounds(200, 300, 300, 40);
        panel.add(deleteButton);

        JButton exitButton = new JButton("Salir");
        exitButton.setBounds(200, 370, 300, 40);
        panel.add(exitButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateBookFrame().setVisible(true);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idLibroStr = JOptionPane.showInputDialog(null, "Ingrese el ID del libro a editar:", "Editar Libro", JOptionPane.PLAIN_MESSAGE);

                if (idLibroStr != null && !idLibroStr.isEmpty()) {
                    try {
                        Integer idLibro = Integer.parseInt(idLibroStr);

                        Book book = adminController.searchLibro(idLibro,true);

                        if (book != null) {
                            // Aquí puedes abrir una ventana de edición (JDialog) y pasar el libro encontrado
                            // Por ejemplo, suponiendo que tienes una clase EditBookDialog que extiende JDialog
                            EditBookDialog editDialog = new EditBookDialog(null, book);
                            editDialog.setVisible(true);

                            // Puedes agregar lógica adicional después de que se cierre la ventana de edición
                            // Por ejemplo, actualizar la lista de libros en la interfaz principal si es necesario
                        } else {
                            JOptionPane.showMessageDialog(null, "Libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de ID de libro no válido. Ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListBooksFrame().setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idLibroStr = JOptionPane.showInputDialog(null, "Ingrese el ID del libro a buscar:", "Buscar Libro", JOptionPane.PLAIN_MESSAGE);

                if (idLibroStr != null && !idLibroStr.isEmpty()) {
                    try {
                        Integer idLibro = Integer.parseInt(idLibroStr);

                        Book libro = adminController.searchLibro(idLibro,false);

                        if (libro != null) {
                            // Mostrar los detalles del libro en una ventana o área de texto
                            mostrarDetallesLibro(libro);
                        } else {
                            JOptionPane.showMessageDialog(null, "Libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de ID de libro no válido. Ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idLibroStr = JOptionPane.showInputDialog(null, "Ingrese el ID del libro a dar de baja:", "Dar de Baja Libro", JOptionPane.PLAIN_MESSAGE);

                if (idLibroStr != null && !idLibroStr.isEmpty()) {
                    try {
                        Integer idLibro = Integer.parseInt(idLibroStr);

                        boolean result = adminController.terminateBook(idLibro);

                        if (result) {
                            JOptionPane.showMessageDialog(null, "Libro habilitado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Libro deshabilitado correctamente." + idLibro, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de ID de libro no válido. Ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "No se encontró el libro");
                    }
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainFrame.setVisible(true);
            }
        });

    }

    // Método para mostrar los detalles del libro en una ventana de diálogo
    private void mostrarDetallesLibro(Book libro) {
        JOptionPane.showMessageDialog(null, libro.toString(), "Detalles del Libro", JOptionPane.INFORMATION_MESSAGE);
    }

}
