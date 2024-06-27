package org.example.controller;

import org.example.model.entity.Book;
import org.example.exception.MisExcepciones;
import org.example.model.repository.BookRepository;
import org.example.view.BookView;

import java.util.List;


public class BookController {

    BookRepository bookRepository  = new BookRepository();
    BookView bookView = new BookView();

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookController(){

    }



    public void toListBooks() {
        List<Book> bookList = getListaLibros();
        if (bookList.isEmpty()) {
            throw new MisExcepciones("No existen libros");
        } else {
            BookView.viewBooks(bookList);
        }

    }

    public List<Book> getListaLibros() {
        return bookRepository.getListaLibros();
    }

    public void  terminateBook()//dar de baja logica
    {
        Integer idBuscar =  bookView.pedirEntero("Ingresar id del libro: ");

        Book book = bookRepository.searchBookId(idBuscar);

        book.setStatus();
    }

    public boolean  terminateBook(Integer Id)//dar de baja logica
    {
        Book book = bookRepository.searchBookId(Id,true);

        book.setStatus();

        if(book.getStatus())
        {
            return true;
        }
        return false;
    }

    public void addRating(Book book, double rating) {
        // Actualizar la suma total de puntuaciones
        double newSumRatings = book.getSumRatings() + rating;
        book.setSumRatings(newSumRatings);

        // Incrementar el total de puntuaciones recibidas
        int newTotalRatings = book.getTotalRatings() + 1;
        book.setTotalRatings(newTotalRatings);

        // Calcular y actualizar el promedio de puntuaciones
        double newRate = newSumRatings / newTotalRatings;
        book.setRate(newRate);
        bookRepository.saveLibros();
    }

    public Book searchBookId()
    {
        Integer idBuscar = bookView.pedirEntero("Ingresar id del libro: ");

        return bookRepository.searchBookId(idBuscar);
    }

    public Book searchBookId(Integer id)
    {
        return bookRepository.searchBookId(id);
    }


    public void editBook(){
        Book bookEdit = searchBookId();

        bookView.editarLibro(bookEdit);

    }

    public void addBook(Book book) {
        bookRepository.addList(book);
    }



    public void creatBook() {
        Book newBook = bookView.creatBook();
        bookRepository.addList(newBook);
    }

    public void viewBook(Book book) {

        BookView.viewBook(book);

    }

    public void viewBooks(List<Book> books) {

        BookView.viewBooks(books);

    }

    public boolean checkStock(Book Book) {
        return Book.getStock() > 0;
    }
}