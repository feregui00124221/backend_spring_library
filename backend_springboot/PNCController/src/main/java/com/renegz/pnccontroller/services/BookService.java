package com.renegz.pnccontroller.services;

import com.renegz.pnccontroller.domain.dtos.SaveBookDTO;
import com.renegz.pnccontroller.domain.entities.Book;

import java.util.List;

public interface BookService {
    void save(SaveBookDTO info);
    List<Book> findAll();
    Book findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
