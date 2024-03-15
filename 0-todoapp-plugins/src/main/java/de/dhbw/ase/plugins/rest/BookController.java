package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.todoapp.adapter.book.BookResource;
import de.dhbw.ase.todoapp.adapter.book.BookToBookResourceMapper;
import de.dhbw.ase.todoapp.application.book.BookApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    private final BookApplicationService bookApplicationService;

    private final BookToBookResourceMapper bookToBookResourceMapper;

    @Autowired
    public BookController(final BookApplicationService bookApplicationService, final BookToBookResourceMapper bookToBookResourceMapper) {
        this.bookApplicationService = bookApplicationService;
        this.bookToBookResourceMapper = bookToBookResourceMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookResource> getBooks() {
        return this.bookApplicationService.findAllBooks().stream()
                .map(bookToBookResourceMapper)
                .collect(Collectors.toList());
    }
}
