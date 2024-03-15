package de.dhbw.ase.todoapp.domain.book;

import java.util.List;

public interface BookRepository {

    List<Book> findAllBooks();

    List<Book> findBooksWithIsbn(String isbn);

    Book save(Book book);
}
