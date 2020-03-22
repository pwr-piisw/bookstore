package io.github.pwrpiiws.bookstore.app.config;

import io.github.pwrpiiws.bookstore.app.store.BookRepositoryInmem;
import io.github.pwrpiiws.bookstore.domain.books.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Bean
    public BookRepository createRepository() {
        return new BookRepositoryInmem();
    }
}
