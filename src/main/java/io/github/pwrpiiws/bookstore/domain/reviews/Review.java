package io.github.pwrpiiws.bookstore.domain.reviews;

import java.util.UUID;

import lombok.Data;

@Data
public class Review {
    private final UUID id;
    private final UUID book;
    private final String author;
    private final int rate;
    private final String title;
    private final String content;
}
