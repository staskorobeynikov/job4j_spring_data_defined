package ru.job4j.springdatadefined.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.springdatadefined.model.Book;
import ru.job4j.springdatadefined.repository.BookRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceDBTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookServiceDB bookServiceDB;

    @Test
    public void whenMultiSave() throws ParseException {
        bookRepository.deleteAll();
        bookServiceDB.create(
                List.of(
                        new Book("Spring Data 1", "Spring Data Description", 3, true,
                                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11")),
                        new Book("Spring Data 2", "Spring Data Description", 3, true,
                                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"))
                )
        );
        assertThat(bookRepository.findAll().size())
                .isEqualTo(2);
    }

    @Test
    public void whenThrowsException() throws ParseException {
        bookRepository.deleteAll();
        try {
            bookServiceDB.create(
                    List.of(
                            new Book("Spring Data", "Spring Data Description", 3, true,
                                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11")),
                            new Book("Spring Data", "Spring Data Description", 3, true,
                                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"))
                    )
            );
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }
        assertThat(bookRepository.findAll().size())
                .isEqualTo(0);
    }

    @Test
    public void whenUnboundProxy() throws ParseException {
        bookRepository.deleteAll();
        try {
            bookServiceDB.createWithAlert(
                    List.of(
                            new Book("Spring Data", "Spring Data Description", 3, true,
                                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11")),
                            new Book("Spring Data", "Spring Data Description", 3, true,
                                    new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"))
                    )
            );
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }
        assertThat(bookRepository.findAll().size())
                .isEqualTo(0);
    }
}