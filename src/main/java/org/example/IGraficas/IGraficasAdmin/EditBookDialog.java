package org.example.IGraficas.IGraficasAdmin;

import org.example.model.entity.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookDialog extends JDialog {

    private JTextField textField;

    public EditBookDialog(JFrame parent, Book book) {
        super(parent, "Editar Libro", true); // Ventana modal sobre el padre
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Nombre del Libro:"));
        textField = new JTextField(book.getNameBook());
        panel.add(textField);

        panel.add(new JLabel("Autor:"));
        JTextField authorField = new JTextField(book.getAuthor());
        panel.add(authorField);

        panel.add(new JLabel("Editorial:"));
        JTextField publisherField = new JTextField(book.getPublisher());
        panel.add(publisherField);

        panel.add(new JLabel("Género:"));
        JTextField generoField = new JTextField(book.getGenero());
        panel.add(generoField);

        panel.add(new JLabel("Idioma:"));
        JTextField languageField = new JTextField(book.getLanguage());
        panel.add(languageField);

        panel.add(new JLabel("Sinopsis:"));
        JTextField synopsisField = new JTextField(book.getSynopsis());
        panel.add(synopsisField);

        panel.add(new JLabel("Stock:"));
        JTextField stockField = new JTextField(String.valueOf(book.getStock()));
        panel.add(stockField);

        panel.add(new JLabel("Vendidos:"));
        JTextField soldField = new JTextField(String.valueOf(book.getSold()));
        panel.add(soldField);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.setNameBook(textField.getText());
                book.setAuthor(authorField.getText());
                book.setPublisher(publisherField.getText());
                book.setGenero(generoField.getText());
                book.setLanguage(languageField.getText());
                book.setSynopsis(synopsisField.getText());
                book.setStock(Integer.parseInt(stockField.getText()));
                book.setSold(Integer.parseInt(soldField.getText()));
                dispose();  // Cerrar la ventana de edición
            }
        });

        panel.add(saveButton);

        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack(); // Ajustar el tamaño de la ventana según su contenido
        setLocationRelativeTo(parent);
    }
}

