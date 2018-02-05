package ru.ineb.pub.backend.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.ineb.pub.backend.handlers.CategoryHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CategoryRouter {
    @Bean
    public RouterFunction<ServerResponse> routeCategory(CategoryHandler categoryHandler){
        return RouterFunctions
                .route(GET("/categories").and(accept(APPLICATION_JSON_UTF8)), categoryHandler::fetchCategories);
    }
}
