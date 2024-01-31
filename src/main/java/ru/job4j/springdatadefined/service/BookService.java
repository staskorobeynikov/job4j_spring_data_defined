package ru.job4j.springdatadefined.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.springdatadefined.model.Book;
import java.util.List;

public interface BookService {
    @Transactional
    void create(List<Book> book);
}
