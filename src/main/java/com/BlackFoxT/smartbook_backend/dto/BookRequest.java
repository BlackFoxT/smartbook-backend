package com.BlackFoxT.smartbook_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String genre;

    @Min(1)
    private int pages;
}
