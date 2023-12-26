package ru.job4j.springdatadefined;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.job4j.springdatadefined.model.Book;
import ru.job4j.springdatadefined.repository.BookRepository;

@SpringBootApplication
@AllArgsConstructor
public class JpaSpringDataDefinedApp implements CommandLineRunner {
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaSpringDataDefinedApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-26");
        Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-19");

        bookRepository.save(new Book("Spring Data", "Spring Data Description", 3, true, date1));
        bookRepository.save(new Book("Java Spring Boot", "Spring Framework Description", 1, false, date1));
        bookRepository.save(new Book("Hibernate", "Hibernate ORM Description", 3, true, date2));
        bookRepository.save(new Book("Spring Boot", "Spring Boot Description", 2, false, date2));
        bookRepository.save(new Book("Spring JPA", "Spring Data JPA Description", 3, true, date3));
        bookRepository.save(new Book("Spring Batch", "Spring Batch Description", 4, true, date3));
        bookRepository.save(new Book("Spring Security", "Spring Security Description", 5, false, date3));

        List<Book> books;

        books = bookRepository.findAll();
        show(books, 1);
        Book book1 = bookRepository.save(new Book("Spring = Security", "Spring Security Description", 5, false, date3));
        System.out.println(bookRepository.findById(book1.getId()).get());

        books = bookRepository.findByPublished(true);
        show(books, 2);

        books = bookRepository.findByLevel(3);
        show(books, 3);

        books = bookRepository.findByLevelIs(3);
        show(books, 4);

        books = bookRepository.findByLevelEquals(3);
        show(books, 5);

        books = bookRepository.findByLevelNot(3);
        show(books, 6);

        books = bookRepository.findByLevelIsNot(3);
        show(books, 7);

        books = bookRepository.findByLevelAndPublished(3, true);
        show(books, 8);

        books = bookRepository.findByTitleOrDescription("Hibernate", "Spring Data Description");
        show(books, 9);

        books = bookRepository.findByTitleLike("Spring%");
        show(books, 10);

        books = bookRepository.findByTitleStartingWith("Spring");
        show(books, 11);

        books = bookRepository.findByTitleEndingWith("ot");
        show(books, 12);

        books = bookRepository.findByTitleContaining("at");
        show(books, 13);

        books = bookRepository.findByTitleContainingIgnoreCase("dat");
        show(books, 14);

        String text = "ot";
        books = bookRepository.findByTitleContainingOrDescriptionContaining(text, text);
        show(books, 15);

        books = bookRepository.findByTitleContainingAndPublished("ring", true);
        show(books, 16);

        books = bookRepository.findByPublishedTrue();
        show(books, 17);

        books = bookRepository.findByPublishedFalse();
        show(books, 18);

        books = bookRepository.findByLevelGreaterThan(3);
        show(books, 19);

        Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

        books = bookRepository.findByCreatedAtGreaterThanEqual(myDate);
        show(books, 20);

        books = bookRepository.findByCreatedAtAfter(myDate);
        show(books, 21);

        books = bookRepository.findByLevelBetween(3, 5);
        show(books, 22);

        books = bookRepository.findByLevelBetweenAndPublished(3, 5, true);
        show(books, 23);

        Date myDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11");
        Date myDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

        books = bookRepository.findByCreatedAtBetween(myDate1, myDate2);
        show(books, 24);

        books = bookRepository.findByOrderByLevel();
        show(books, 25);

        books = bookRepository.findByOrderByLevelDesc();
        show(books, 26);

        books = bookRepository.findByTitleContainingOrderByLevelDesc("at");
        show(books, 27);

        books = bookRepository.findByPublishedOrderByCreatedAtDesc(true);
        show(books, 28);

        books = bookRepository.findByTitleContaining("at", Sort.by("level").descending());
        show(books, 29);

        books = bookRepository.findByPublished(false, Sort.by("level").descending());
        show(books, 30);

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "level"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "title"));
        books = bookRepository.findByPublished(true, Sort.by(orders));
        show(books, 31);

        books = bookRepository.findTop3ByTitleContainingAndPublished("Spring Data", true);
        show(books, 32);

        int page = 0;
        int size = 3;

        Pageable pageable = PageRequest.of(page, size);
        books = bookRepository.findAll(pageable).getContent();
        show(books, 33);

        books = bookRepository.findByTitleContaining("ring", pageable).getContent();
        show(books, 34);


        pageable = PageRequest.of(page, size, Sort.by("level").descending());
        books = bookRepository.findAll(pageable).getContent();
        show(books, 35);

        pageable = PageRequest.of(page, size, Sort.by("level").descending());
        books = bookRepository.findByTitleContaining("at", pageable).getContent();
        show(books, 36);

        Date outdate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-1");
        bookRepository.deleteAllByCreatedAtBefore(outdate);
        books = bookRepository.findAll();
        show(books, 37);
    }

    private void show(List<Book> books, int number) {
        System.out.printf("----- %d begin----------%n", number);
        books.forEach(System.out::println);
        System.out.println("------------------------");
    }
}
