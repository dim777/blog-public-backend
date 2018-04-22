package ru.ineb.pub.backend.handlers;

import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toFormData;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class FormHandler {
    public Mono<ServerResponse> handleArticleCreate(ServerRequest request) {
        return request.body(toFormData())
                .map(MultiValueMap::toSingleValueMap)
                .filter(formData -> "dim777".equals(formData.get("createdBy")))
                .flatMap(formData -> ok().body(Mono.just("welcome back!"), String.class))
                .switchIfEmpty(ServerResponse.badRequest()
                        .build());
    }
}
