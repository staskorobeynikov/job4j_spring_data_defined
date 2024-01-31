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
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void whenFindByTitle() throws ParseException {
        bookRepository.deleteAll();
        var book = new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"));
        bookRepository.save(book);
        assertThat(
                bookRepository.findByTitle("Spring Data")
                        .size())
                .isEqualTo(1);
    }

    @Test
    public void whenFindByTitleAndLevel() throws ParseException {
        bookRepository.deleteAll();
        var book = new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"));
        bookRepository.save(book);
        assertThat(
                bookRepository.findByTitleAndLevel("Spring Data", 3)
                        .size())
                .isEqualTo(1);
    }

    @Test
    public void whenFindLevelInRange() throws ParseException {
        bookRepository.deleteAll();
        var book = new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11"));
        bookRepository.save(book);
        assertThat(
                bookRepository.findByLevelGreaterThanEqualAndLevelLessThanEqual(0, 3)
                        .size())
                .isEqualTo(1);
    }

    @Test
    public void whenOrderBy() throws ParseException {
        bookRepository.deleteAll();
        bookRepository.save(new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11")));
        bookRepository.save(new Book("Spring Data", "Spring Data Description", 1, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-11")));
        assertThat(
                bookRepository.findByOrderByLevelDesc()
                        .stream().map(Book::getLevel)
                        .toList())
                .isEqualTo(List.of(3, 1));
    }

    @Test
    public void whenPage() throws ParseException {
        bookRepository.deleteAll();
        bookRepository.save(new Book("Spring Data", "Spring Data Description", 3, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11")));
        bookRepository.save(new Book("Spring Data", "Spring Data Description", 1, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-11")));
        bookRepository.save(new Book("Spring Data", "Spring Data Description", 1, true,
                new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-11")));
        assertThat(
                bookRepository.findAll(Pageable.ofSize(1))
                        .getTotalPages())
                .isEqualTo(3);
    }
}