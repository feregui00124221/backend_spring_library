package com.renegz.pnccontroller.controllers;

import com.renegz.pnccontroller.domain.dtos.GeneralResponse;
import com.renegz.pnccontroller.domain.dtos.SaveBookDTO;
import com.renegz.pnccontroller.domain.entities.Book;
import com.renegz.pnccontroller.services.BookService;
import com.renegz.pnccontroller.utils.ErrorsTool;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {
    private final ErrorsTool errorsTool;

    private final BookService bookService;

    public LibraryRestController(ErrorsTool errorsTool, BookService bookService) {
        this.errorsTool = errorsTool;
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> findAll(){
//        return new ResponseEntity<>(
//                new GeneralResponse("List of books!", bookService.findAll()),
//                HttpStatus.OK
//        );
        return GeneralResponse.getResponse(
                HttpStatus.OK,
                "List of books!",
                bookService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<GeneralResponse> saveBook(@RequestBody @Valid SaveBookDTO info) {
//        if (errors.hasErrors()) {
//            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, errorsTool.mapErrors(errors.getFieldErrors()));
//        }

        //TODO: Save Book
        bookService.save(info);

        return GeneralResponse.getResponse(HttpStatus.OK, "Libro guardado");
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<GeneralResponse> deleteByIsbn(@PathVariable String isbn){
        Book book = bookService.findByIsbn(isbn);
        if (book == null){
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND);
        }

        bookService.deleteByIsbn(isbn);

        return GeneralResponse.getResponse(HttpStatus.OK);
    }
}
