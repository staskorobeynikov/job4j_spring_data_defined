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
            SELECT book FROM Book AS book
            WHERE book.level >= ?1 AND book.level <= ?2
            """)
    List<Book> findByLevelInRange(int startAt, int finishAt);

    @Query("""
            SELECT book FROM Book AS book
            WHERE book.title >= :title
            """)
    Optional<Book> findByTitle(@Param("title") String title);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Book book SET book.title = :title
            WHERE book.id = :id
            """)
    int updateTitle(@Param("title") String title, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            UPDATE book SET description = :description
            WHERE id = :id
            """, nativeQuery = true)
    int updateDesc(@Param("description") String description, @Param("id") Long id);
}
