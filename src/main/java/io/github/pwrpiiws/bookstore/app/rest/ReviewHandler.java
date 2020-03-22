package io.github.pwrpiiws.bookstore.app.rest;

import java.util.UUID;

import io.github.pwrpiiws.bookstore.domain.reviews.Review;
import io.github.pwrpiiws.bookstore.domain.reviews.ReviewManager;
import io.github.pwrpiiws.bookstore.domain.reviews.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class ReviewHandler {

    private final ReviewRepository reviewRepository;

    private final ReviewManager reviewManager;

    public Mono<ServerResponse> findAllReviewsForBook(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(reviewRepository.findReviewsForBook(UUID.fromString(serverRequest.pathVariable("bookId"))), Review.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(reviewRepository.findById(UUID.fromString(serverRequest.pathVariable("reviewId"))), Review.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(reviewManager.saveReview(serverRequest.bodyToMono(Review.class)), Review.class);
    }
}
