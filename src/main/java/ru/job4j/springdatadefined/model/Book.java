package ru.job4j.springdatadefined.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "tutorials")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private int level;
    private boolean published;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    public Book(String title, String description, int level, boolean published, Date createdAt) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.published = published;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Book [id=%d, title=%s, description=%s, level=%d, published=%s, createdAt=%s]".formatted(
                id, title, description, level, published, createdAt);
    }
}