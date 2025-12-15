package com.BlackFoxT.smartbook_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String isbn;
    private String title;
    private String author;
    private String genre;
    private int pages;
}
