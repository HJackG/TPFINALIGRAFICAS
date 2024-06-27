package org.example.IGraficas.IGraficasAdmin;

import org.example.controller.BookController;
import org.example.model.entity.Book;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListBooksFrame extends JFrame {
    private final BookController bookController = new BookController();

    public ListBooksFrame( ) {
        initUI();
    }

    private void initUI() {
        setTitle("Lista de Libros");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true); // Envolver líneas largas
        textArea.setWrapStyleWord(true); // Envolver al final de palabras completas
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        // Add the back button
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the current window
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        java.util.List<Book> books = bookController.getListaLibros();
        for (Book book : books) {
            textArea.append(book.toString() + "\n");
        }
    }

}


