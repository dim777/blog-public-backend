package ru.ineb.pub.backend.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.model.wrapper.ArticlesWrapper;
import ru.ineb.pub.backend.repository.ArticleRepository;
import ru.ineb.pub.backend.services.ArticleServices;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ArticleHandler {
    private static final Logger log = LoggerFactory.getLogger(ArticleHandler.class);
    static Integer i;

    @Autowired private ArticleRepository articleRepository;

    @Autowired private ArticleServices articleServices;

    public Mono<ServerResponse> streamArticles(ServerRequest request) {
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(articleRepository.findAll(), Article.class);
    }

    /*public Mono<ServerResponse> fetchArticles(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        return ok()
                .contentType(APPLICATION_JSON)
                .body(articleRepository.findAll().take(size), Article.class);
    }*/

    public Mono<ServerResponse> fetchArticles(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        return ok()
                .contentType(APPLICATION_JSON)
                .body(Mono.just(articleServices.getArticlesLimit(size)), ArticlesWrapper.class);
    }


    public Mono<ServerResponse> createArticle(ServerRequest request) {
        Flux<Article> article = request.bodyToFlux(Article.class);
        articleRepository.insert(article).subscribe();
        return ServerResponse.ok().build();
    }
}
