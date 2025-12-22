package com.BlackFoxT.smartbook_backend.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminDashboardResponse {

    private long totalUsers;    private long totalBooks;
    private long totalUserBooks;

    private long wantToReadCount;
    private long readingCount;
    private long finishedCount;

    private double averageRating;
    private long ratedBooksCount;
}
