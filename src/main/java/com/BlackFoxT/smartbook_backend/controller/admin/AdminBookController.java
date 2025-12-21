package com.BlackFoxT.smartbook_backend.controller.admin;

import com.BlackFoxT.smartbook_backend.dto.BookRequest;
import com.BlackFoxT.smartbook_backend.dto.BookResponse;
import com.BlackFoxT.smartbook_backend.mapper.BookMapper;
import com.BlackFoxT.smartbook_backend.service.AdminBookService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/books")
@PreAuthorize("hasRole('ADMIN')")
public class AdminBookController {

    private final AdminBookService adminBookService;
    private final BookMapper bookMapper;

    public AdminBookController(
            AdminBookService adminBookService,
            BookMapper bookMapper
    ) {
        this.adminBookService = adminBookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public BookResponse create(@Valid @RequestBody BookRequest request) {
        return bookMapper.toResponse(
                adminBookService.create(request)
        );
    }

    @PutMapping("/{isbn}")
    public BookResponse update(
            @PathVariable String isbn,
            @Valid @RequestBody BookRequest request
    ) {
        return bookMapper.toResponse(
                adminBookService.update(isbn, request)
        );
    }

    @DeleteMapping("/{isbn}")
    public void delete(@PathVariable String isbn) {
        adminBookService.delete(isbn);
    }
}
