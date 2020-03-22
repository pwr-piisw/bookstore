package io.github.pwrpiiws.bookstore.app.config;

import io.github.pwrpiiws.bookstore.app.store.BookRepositoryInmem;
import io.github.pwrpiiws.bookstore.app.store.ReviewRepositoryInmem;
import io.github.pwrpiiws.bookstore.domain.books.BookRepository;
import io.github.pwrpiiws.bookstore.domain.reviews.ReviewManager;
import io.github.pwrpiiws.bookstore.domain.reviews.ReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public BookRepository createBookRepository() {
        return new BookRepositoryInmem();
    }

    @Bean
    public ReviewRepository createReviewRepository() {
        return new ReviewRepositoryInmem();
    }

    @Bean
    public ReviewManager createReviewManager(ReviewRepository reviewRepository, BookRepository bookRepository) {
        return new ReviewManager(reviewRepository, bookRepository);
    }
}
