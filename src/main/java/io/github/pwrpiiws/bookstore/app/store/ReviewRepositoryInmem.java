package io.github.pwrpiiws.bookstore.app.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.github.pwrpiiws.bookstore.domain.reviews.Review;
import io.github.pwrpiiws.bookstore.domain.reviews.ReviewRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReviewRepositoryInmem implements ReviewRepository {

    private final ConcurrentMap<UUID, Review> reviews = new ConcurrentHashMap<>();

    public ReviewRepositoryInmem() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(UUID.randomUUID(), BookIDs.FIASCO.getId(), "John Doe", 4, "Fantastic", "Some text1"));
        reviews.add(new Review(UUID.randomUUID(), BookIDs.FIASCO.getId(), "Abraham Lincoln", 5, "The best", "Some text2"));
        reviews.add(new Review(UUID.randomUUID(), BookIDs.FIASCO.getId(), "James Gosling", 2, "Boo", "Some text3"));

        reviews.forEach(review -> this.reviews.put(review.getId(), review));
    }

    @Override
    public Flux<Review> findReviewsForBook(UUID bookId) {
        Objects.requireNonNull(bookId);
        return Flux.fromStream(reviews.values().stream().filter(review -> review.getBook().equals(bookId)));
    }

    @Override
    public Mono<Review> findById(UUID reviewId) {
        Objects.requireNonNull(reviewId);
        return Mono.justOrEmpty(reviews.get(reviewId));
    }

    @Override
    public Mono<Review> save(Mono<Review> review) {
        Objects.requireNonNull(review);
        return review.map(this::save);
    }

    private Review save(final Review input) {
        if (input.getId() == null) {
            Review newReview = new Review(
                    UUID.randomUUID(),
                    input.getBook(),
                    input.getAuthor(),
                    input.getRate(),
                    input.getTitle(),
                    input.getContent());
            reviews.put(newReview.getId(), newReview);
            return newReview;
        } else {
            return reviews.compute(input.getId(), (uuid, review) -> {
                if (review != null) {
                    return input;
                } else {
                    throw new IllegalArgumentException("Review with ID=" + input.getId() + " does not exist");
                }
            });
        }
    }

}
