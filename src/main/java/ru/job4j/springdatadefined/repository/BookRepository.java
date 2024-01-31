package ru.job4j.springdatadefined.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.springdatadefined.model.Book;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAll();
  
  Optional<Book> findById(long id);
  
  List<Book> findByLevel(int level);
  
  List<Book> findByPublished(boolean isPublished);
  
  List<Book> findByLevelIs(int level);
  
  List<Book> findByLevelEquals(int level);
  
  List<Book> findByLevelNot(int level);
  
  List<Book> findByLevelIsNot(int level);
  
  List<Book> findByLevelAndPublished(int level, boolean isPublished);
  
  List<Book> findByTitleOrDescription(String title, String description);
  
  List<Book> findByTitleLike(String title);
  
  List<Book> findByTitleStartingWith(String title);
  
  List<Book> findByTitleEndingWith(String title);
  
  List<Book> findByTitleContaining(String title);
  
  List<Book> findByTitleContainingIgnoreCase(String title);
  
  List<Book> findByTitleContainingOrDescriptionContaining(String title, String description);
  
  List<Book> findByTitleContainingAndPublished(String title, boolean isPublished);
  
  List<Book> findByPublishedTrue();
  
  List<Book> findByPublishedFalse();
  
  List<Book> findByLevelGreaterThan(int level);
  
  List<Book> findByCreatedAtGreaterThanEqual(Date date);
  
  List<Book> findByCreatedAtAfter(Date date);
  
  List<Book> findByLevelBetween(int start, int end);

  List<Book> findByLevelBetweenAndPublished(int start, int end, boolean isPublished);
  
  List<Book> findByCreatedAtBetween(Date start, Date end);
  
  List<Book> findByOrderByLevel();
  
  List<Book> findByOrderByLevelDesc();
  
  List<Book> findByTitleContainingOrderByLevelDesc(String title);

  List<Book> findByPublishedOrderByCreatedAtDesc(boolean isPublished);
  
  List<Book> findByTitleContaining(String title, Sort sort);
  
  List<Book> findByPublished(boolean isPublished, Sort sort);
  
  List<Book> findTop3ByTitleContainingAndPublished(String title, boolean isPublished);
  
  Page<Book> findAll(Pageable pageable);
  
  Page<Book> findByTitleContaining(String title, Pageable pageable);

  @Transactional
  void deleteAllByCreatedAtBefore(Date date);

  List<Book> findByTitle(String title);

  List<Book> findByTitleAndLevel(String title, int level);

  List<Book> findByLevelGreaterThanEqualAndLevelLessThanEqual(int startAt, int finishAt);
}