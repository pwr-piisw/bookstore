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
        create.add(new Book(UUID.randomUUID(), "Fiasco", "Stanislaw Lem"));
        create.add(new Book(UUID.randomUUID(), "Ubik", "Phillip K. Dick"));
        create.add(new Book(UUID.randomUUID(), "2001: A Space Odyssey", "Arthur C. Clarke"));
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
            if (books.containsKey(input.getId())) {
                return books.put(input.getId(), input);
            } else {
                throw new IllegalArgumentException("Book with ID=" + input.getId() + " does not exist");
            }
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
