package com.klb.controller;

import com.klb.entity.Book;
import com.klb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

import static javax.swing.text.StyleConstants.ModelAttribute;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //!!!!!!!!!!!!!!!!!! add s
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooksPage(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("bookList", books);//1. dodajemy do modelu liste ksiazek

        return "books"; //2. przekierowujemy na odpowiednia strone (books.jsp)

    }

    //przekierowuje na strone do edycji nowej ksiazki
    @RequestMapping(value = "/book/create", method = RequestMethod.GET)
    public String getCreateBookForm(Model model) {
        model.addAttribute("book", new Book());

        return "book-create";
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public String postCreateBook(@ModelAttribute @Valid Book book, BindingResult br) {
        if(br.hasErrors()) {
            return "book-create";
        }

        bookService.save(book);

        return "redirect:/books";
    }

    //użyć @PathVariable
    @RequestMapping(value = "/book/delete/{id}", method = RequestMethod.GET)
    public String postDeleteBook(@PathVariable Long id) {
        bookService.delete(id);

        return "redirect:/books";
    }

    //metoda przekierowuje na strone do edycji ksiazki

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);

        return "book-create";  //przekierowanie na strone rowniez do edycji
    }


}
