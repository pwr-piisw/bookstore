package io.github.pwrpiiws.bookstore.domain.books;

import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {

    Flux<Book> findAllBooks();

    Mono<Book> findById(UUID id);

    Mono<Book> save(Mono<Book> book);

    Mono<Book> delete(Mono<Book> book);
}
