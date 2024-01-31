package ru.job4j.springdatadefined.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.springdatadefined.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookJPQLRepository extends JpaRepository<Book, Long> {
    @Query
    Optional<Book> findById(Long id);

    @Query("""
            select book from Book as book
            where book.level >= ?1 and book.level <= ?2
            """)
    List<Book> findByLevelInRange(int startAt, int finishAt);

    @Query("""
            select book from Book as book
            where book.title >= :title
            """)
    Optional<Book> findByTitle(@Param("title") String title);

    @Modifying(clearAutomatically = true)
    @Query("""
            update Book book set book.title = :title
            where book.id = :id
            """)
    int updateTitle(@Param("title") String title, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            UPDATE book SET description = :description
            where id = :id
            """, nativeQuery = true)
    int updateDesc(@Param("description") String description, @Param("id") Long id);
}
