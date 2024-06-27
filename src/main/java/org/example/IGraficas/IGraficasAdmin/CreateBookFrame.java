package org.example.IGraficas.IGraficasAdmin;

import org.example.controller.BookController;
import org.example.model.entity.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class CreateBookFrame extends JFrame {
    private JTextField nombreLibroField;
    private JTextField autorField;
    private JTextField editorialField;
    private JTextField generoField;
    private JTextField idiomaField;
    private JTextArea sinopsisArea;
    private JTextField calificacionField;
    private JTextField stockField;
    private JTextField vendidosField;

    private BookController bookController = new BookController();

    public CreateBookFrame( ) {
        initUI();
    }

    private void initUI() {
        setTitle("Crear libro");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));

        // Labels and text fields for book details
        panel.add(new JLabel("Nombre del libro:"));
        nombreLibroField = new JTextField();
        panel.add(nombreLibroField);

        panel.add(new JLabel("Autor:"));
        autorField = new JTextField();
        panel.add(autorField);

        panel.add(new JLabel("Editorial:"));
        editorialField = new JTextField();
        panel.add(editorialField);

        panel.add(new JLabel("Género:"));
        generoField = new JTextField();
        panel.add(generoField);

        panel.add(new JLabel("Idioma:"));
        idiomaField = new JTextField();
        panel.add(idiomaField);

        panel.add(new JLabel("Sinopsis:"));
        sinopsisArea = new JTextArea();
        panel.add(new JScrollPane(sinopsisArea));

        panel.add(new JLabel("Calificación:"));
        calificacionField = new JTextField();
        panel.add(calificacionField);

        panel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        panel.add(stockField);

        panel.add(new JLabel("Vendidos:"));
        vendidosField = new JTextField();
        panel.add(vendidosField);

        JButton submitButton = new JButton("Crear libro");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBook();
            }
        });
        panel.add(submitButton);

        getContentPane().add(panel);
    }

    private void createBook() {
        try {
            String nombreLibro = nombreLibroField.getText();
            String autor = autorField.getText();
            String editorial = editorialField.getText();
            String genero = generoField.getText();
            String idioma = idiomaField.getText();
            String sinopsis = sinopsisArea.getText();
            double calificacion = Double.parseDouble(calificacionField.getText());
            int stock = Integer.parseInt(stockField.getText());
            int vendidos = Integer.parseInt(vendidosField.getText());

            Book newBook = new Book(nombreLibro, autor, editorial, genero, idioma, sinopsis, stock, vendidos);
            bookController.addBook(newBook); // Método que debes implementar en el controlador para agregar el libro
            JOptionPane.showMessageDialog(this, "Libro creado exitosamente.");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Entrada inválida. Por favor, ingresa el tipo de dato correcto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}



