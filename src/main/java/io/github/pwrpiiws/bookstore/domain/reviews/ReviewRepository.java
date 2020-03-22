package io.github.pwrpiiws.bookstore.domain.reviews;

import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewRepository {

    Flux<Review> findReviewsForBook(UUID bookId);

    Mono<Review> findById(UUID reviewId);

    Mono<Review> save(Mono<Review> review);
}
