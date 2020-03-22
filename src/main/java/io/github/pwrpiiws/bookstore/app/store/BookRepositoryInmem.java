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
package io.github.pwrpiiws.bookstore.app.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.github.pwrpiiws.bookstore.domain.books.Book;
import io.github.pwrpiiws.bookstore.domain.books.BookRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookRepositoryInmem implements BookRepository {

    private final ConcurrentMap<UUID, Book> books;

    public BookRepositoryInmem() {
        books = new ConcurrentHashMap<>();
        List<Book> create = new ArrayList<>();
        create.add(new Book(BookIDs.FIASCO.getId(), "Fiasco", "Stanislaw Lem"));
        create.add(new Book(BookIDs.UBIK.getId(), "Ubik", "Phillip K. Dick"));
        create.add(new Book(BookIDs.A2001.getId(), "2001: A Space Odyssey", "Arthur C. Clarke"));
        create.forEach(book -> books.put(book.getId(), book));
    }

    @Override
    public Flux<Book> findAllBooks() {
        return Flux.fromStream(books.values().stream());
    }

    @Override
    public Mono<Book> findById(final UUID id) {
        Objects.requireNonNull(id);
        return Mono.justOrEmpty(books.get(id));
    }

    @Override
    public Mono<Book> save(final Mono<Book> book) {
        Objects.requireNonNull(book);
        return book.map(this::save);
    }

    @Override
    public Mono<Book> delete(final Mono<Book> book) {
        Objects.requireNonNull(book);
        return book.map(this::delete);
    }

    private Book save(final Book input) {
        if (input.getId() == null) {
            Book newBook = new Book(UUID.randomUUID(), input.getTitle(), input.getAuthor());
            books.put(newBook.getId(), newBook);
            return newBook;
        } else {
            return books.compute(input.getId(), (uuid, book) -> {
                if (book != null) {
                    return input;
                } else {
                    throw new IllegalArgumentException("Book with ID=" + input.getId() + " does not exist");
                }
            });
        }
    }

    private Book delete(final Book input) {
        if (input.getId() == null) {
            throw new IllegalArgumentException("Book with ID=" + input.getId() + " does not exist");
        }
        final Book removed = books.remove(input.getId());
        if (removed == null) {
            throw new IllegalArgumentException("Book with ID=" + input.getId() + " does not exist");
        }
        return removed;
    }
}
