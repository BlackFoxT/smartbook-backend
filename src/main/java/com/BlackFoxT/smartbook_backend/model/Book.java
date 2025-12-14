package com.BlackFoxT.smartbook_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "books",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "isbn")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String author;
    private String genre;
    private Integer pages;
}
