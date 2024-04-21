package com.renegz.pnccontroller.services.implementations;

import com.renegz.pnccontroller.domain.dtos.SaveBookDTO;
import com.renegz.pnccontroller.domain.entities.Book;
import com.renegz.pnccontroller.services.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Ahora ya lo puede ocupas el entityManager para poder inyectarlo
public class BookServiceImpl implements BookService {

    public static final List<Book> books = new ArrayList<>();
    static {
        books.add(new Book("xxxxxxxxx-x", "El quijote"));
        books.add(new Book("xxxxxxxxx-y", "C for baby"));
        books.add(new Book("xxxxxxxxx-b", "ICPC Mastering"));
        books.add(new Book("xxxxxxxxx-c", "DUNA"));
        books.add(new Book("xxxxxxxxx-d", "PHP"));
        books.add(new Book("xxxxxxxxx-e", "MySQL"));
        books.add(new Book("xxxxxxxxx-f", "Harry Potter"));
        books.add(new Book("xxxxxxxxx-g", ":wq, El Salvador"));
        books.add(new Book("xxxxxxxxx-h", "Linux > todo"));
        books.add(new Book("xxxxxxxxx-i", "Dynamic Programming"));
    }

    @Override
    public void save(SaveBookDTO info) {
        Book book = this.findByIsbn(info.getISBN());

        if(book == null){
            book = new Book();
            books.add(book);
        }

        book.setISBN(info.getISBN());
        book.setTitle(info.getTitle());
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getISBN().equals(isbn))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.removeIf(b -> b.getISBN().equals(isbn));
    }
}
