package io.github.pwrpiiws.bookstore.domain.reviews;

import java.util.Objects;

import io.github.pwrpiiws.bookstore.domain.books.BookRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ReviewManager {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public Mono<Review> saveReview(Mono<Review> input) {
        Objects.requireNonNull(input);
        return input
                .filterWhen(review -> bookRepository.findById(review.getBook()).map(Objects::nonNull))
                .flatMap(review -> reviewRepository.save(Mono.just(review)));
    }
}
