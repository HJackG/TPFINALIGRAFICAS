package org.example.IGraficas.IGraficasCliente;

import org.example.IGraficas.MainFrame;
import org.example.controller.BookController;
import org.example.controller.ClienteController;
import org.example.menu.MenuMain;
import org.example.model.entity.Book;
import org.example.model.entity.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClienteFrame extends JFrame {
    private Cliente cliente;
    private BookController bookController = new BookController();
    private ClienteController clienteController = new ClienteController();
    private int currentIndex = 0;
    private JTextArea bookDetailsArea;
    private MainFrame mainFrame;

    public ClienteFrame(Cliente cliente, MenuMain menuMain) {
        this.cliente = cliente;
        this.mainFrame = new MainFrame(menuMain);
        initUI();
    }

    private void initUI() {
        setTitle("Panel de cliente");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        bookDetailsArea = new JTextArea();
        bookDetailsArea.setEditable(false);
        bookDetailsArea.setLineWrap(true);
        bookDetailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(bookDetailsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(bookDetailsArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 5));


        JButton prevButton = new JButton("Anterior");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousBook();
            }
        });
        buttonPanel.add(prevButton);

        JButton nextButton = new JButton("Siguiente");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextBook();
            }
        });
        buttonPanel.add(nextButton);

        JButton searchBook = new JButton("Buscar libro");
        searchBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook(bookDetailsArea);
            }
        });
        buttonPanel.add(searchBook);

        JButton addToFavButton = new JButton("Añadir a fav.");
        addToFavButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToFavorites();
            }
        });
        buttonPanel.add(addToFavButton);

        JButton buyButton = new JButton("Comprar");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyBook();
            }
        });
        buttonPanel.add(buyButton);

        JButton perfilButton = new JButton("Perfil");
        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPerfil();
            }
        });
        buttonPanel.add(perfilButton);

        JButton terminateButton = new JButton("Darse de baja");
        terminateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                darDeBajaCliente(cliente);
                if (!cliente.isUserActive()) {
                    JOptionPane.showMessageDialog(panel, "Cliente dado de baja.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cerrar la ventana después de dar de baja al cliente
                    mainFrame.setVisible(true);
                }
            }
        });
        buttonPanel.add(terminateButton);

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                mainFrame.setVisible(true);
            }
        });
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        showCurrentBook();
    }

    private void showCurrentBook() {
        List<Book> libros = bookController.getListaLibros();
        if (!libros.isEmpty()) {
            Book currentBook = libros.get(currentIndex);
            bookDetailsArea.setText(currentBook.toString());
        } else {
            bookDetailsArea.setText("No hay libros disponibles.");
        }
    }

    private void showPreviousBook() {
        List<Book> libros = bookController.getListaLibros();
        if (!libros.isEmpty()) {
            currentIndex = (currentIndex - 1 + libros.size()) % libros.size();
            showCurrentBook();
        }
    }

    private void searchBook(JTextArea textArea) {
        String busqueda = JOptionPane.showInputDialog(this, "Ingrese el nombre del libro:");
        if (busqueda == null || busqueda.trim().isEmpty()) {
            return; // User cancelled or entered empty search
        }

        List<Book> librosEncontrados = new ArrayList<>();
        List<Book> resultados = bookController.getListaLibros();

        for (Book libro : resultados) {
            if (libro.getNameBook().toLowerCase().contains(busqueda.toLowerCase()) ||
                    libro.getSynopsis().toLowerCase().contains(busqueda.toLowerCase())) {
                librosEncontrados.add(libro);
            }
        }

        displaySearchResults(librosEncontrados, textArea);
    }

    private void displaySearchResults(List<Book> librosEncontrados, JTextArea textArea) {
        if (librosEncontrados.isEmpty()) {
            textArea.setText("No se encontraron libros.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Book libro : librosEncontrados) {
                sb.append(libro.toString()).append("\n");
            }
            textArea.setText(sb.toString());
        }
    }

    private void showNextBook() {
        List<Book> libros = bookController.getListaLibros();
        if (!libros.isEmpty()) {
            currentIndex = (currentIndex + 1) % libros.size();
            showCurrentBook();
        }
    }

    private void addToFavorites() {
        List<Book> libros = bookController.getListaLibros();
        if (!libros.isEmpty()) {
            Book currentBook = libros.get(currentIndex);
            clienteController.addBookFav(cliente, currentBook);
            JOptionPane.showMessageDialog(this, "Libro añadido a favoritos.");
        }
    }

    private void buyBook() {
        List<Book> libros = bookController.getListaLibros();
        if (!libros.isEmpty()) {
            Book currentBook = libros.get(currentIndex);
            clienteController.tryAddBook(cliente, currentBook);
            JOptionPane.showMessageDialog(this, "Libro comprado.");
        }
    }

private void darDeBajaCliente(Cliente cliente) {
    cliente.setUserActive(false);
    clienteController.updateUser(cliente); // Suponiendo que este método actualiza el cliente en el repositorio
}

    private void showPerfil() {
        // Mostrar detalles del perfil del cliente en una nueva ventana o cuadro de diálogo
        JOptionPane.showMessageDialog(this, cliente.toString(), "Perfil de Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exit() {
        dispose();
    }

}

