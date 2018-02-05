package ru.ineb.pub.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Category;
import ru.ineb.pub.backend.repository.CategoryRepository;

import java.util.Comparator;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class CategoryHandler {
    @Autowired private CategoryRepository categoryRepository;

    public Mono<ServerResponse> fetchCategories(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));

        return ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(categoryRepository
                        .findAll()
                        .sort(Comparator.comparingInt(Category::getPriority))
                        .take(size), Category.class);
    }
}
