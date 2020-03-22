package io.github.pwrpiiws.bookstore.app.rest;

import java.util.UUID;

import io.github.pwrpiiws.bookstore.domain.books.Book;
import io.github.pwrpiiws.bookstore.domain.books.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class BookHandler {

    private final BookRepository bookRepository;

    public Mono<ServerResponse> findAllBooks(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.findAllBooks(), Book.class);
    }

    public Mono<ServerResponse> findBookById(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.findById(UUID.fromString(serverRequest.pathVariable("bookId"))), Book.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.save(serverRequest.bodyToMono(Book.class)), Book.class);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.delete(
                        bookRepository.findById(UUID.fromString(serverRequest.pathVariable("bookId")))
                ), Book.class);
    }
}
