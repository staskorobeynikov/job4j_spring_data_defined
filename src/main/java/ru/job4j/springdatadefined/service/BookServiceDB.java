package ru.job4j.springdatadefined.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.springdatadefined.model.Book;
import ru.job4j.springdatadefined.repository.BookRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceDB implements BookService {
    private final BookRepository bookRepository;

    public void create(List<Book> books) {
        books.forEach(bookRepository::save);
    }

    public void createWithAlert(List<Book> books) {
        create(books);
        //код, который отправит уведомления после сохранения книг
    }
}
