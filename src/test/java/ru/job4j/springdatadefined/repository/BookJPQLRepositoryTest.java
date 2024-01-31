package ru.job4j.springdatadefined.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.springdatadefined.model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookJPQLRepositoryTest {
    @Autowired
    BookJPQLRepository bookJPQLRepository;

    @Test
    public void whenFindLevelInRange() throws ParseException {
        bookJPQLRepository.deleteAll();
        var book = new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"));
        bookJPQLRepository.save(book);
        assertThat(
                bookJPQLRepository.findByLevelInRange(0, 3)
                        .size())
                .isEqualTo(1);
    }

    @Test
    public void whenByTitle() throws ParseException {
        bookJPQLRepository.deleteAll();
        var book = new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"));
        bookJPQLRepository.save(book);
        assertThat(
                bookJPQLRepository.findByTitle("Spring Data")
                        .get().getTitle())
                .isEqualTo("Spring Data");
    }

    @Test
    public void whenUpdateTitle() throws ParseException {
        bookJPQLRepository.deleteAll();
       var book = bookJPQLRepository.save(
                new Book("Spring Data", "Spring Data Description", 3, true,
                        new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"))
        );
       var updated = bookJPQLRepository.updateTitle("Spring Data JPA", book.getId());
        assertThat(updated).isEqualTo(1);
        assertThat(
                bookJPQLRepository.findById(book.getId()).get().getTitle()
        ).isEqualTo("Spring Data JPA");
    }

    @Test
    public void whenUpdateDesc() throws ParseException {
        bookJPQLRepository.deleteAll();
        var book = bookJPQLRepository.save(
                new Book("Spring Data", "Spring Data Description", 3, true,
                        new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"))
        );
        var updated = bookJPQLRepository.updateDesc("Spring Data JPA Description", book.getId());
        assertThat(updated).isEqualTo(1);
        assertThat(
                bookJPQLRepository.findById(book.getId()).get().getDescription()
        ).isEqualTo("Spring Data JPA Description");
    }
}