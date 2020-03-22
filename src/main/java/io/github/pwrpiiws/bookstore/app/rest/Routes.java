package io.github.pwrpiiws.bookstore.app.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(final BookHandler bookHandler) {
        return RouterFunctions.route()
                .GET("books", bookHandler::findAllBooks)
                .GET("books/{bookId}", bookHandler::findBookById)
                .POST("books", bookHandler::save)
                .DELETE("books/{bookId}", bookHandler::delete)
                .build();
    }
}
