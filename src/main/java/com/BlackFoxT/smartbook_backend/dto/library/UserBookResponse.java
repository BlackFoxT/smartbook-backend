package com.BlackFoxT.smartbook_backend.dto.library;

import com.BlackFoxT.smartbook_backend.model.enums.ReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserBookResponse {

    private String isbn;
    private String title;
    private String author;

    private ReadingStatus status;
    private Integer rating;
    private LocalDateTime addedAt;
}
