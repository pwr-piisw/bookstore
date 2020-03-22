/*
 * MIT License
 *
 * Copyright (c) 2020 Politechnika Wrocławska - Projektowanie i implementacja systemów webowych
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
