package io.github.pwrpiiws.bookstore.domain.books;

import java.util.UUID;

import lombok.Data;

@Data
public class Book {
    private final UUID id;
    private final String title;
    private final String author;
}
