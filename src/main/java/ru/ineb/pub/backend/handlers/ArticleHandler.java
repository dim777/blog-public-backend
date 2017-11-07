package ru.ineb.pub.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.repository.ArticleRepository;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toFormData;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ArticleHandler {

    @Autowired
    private ArticleRepository articleRepository;

    public Mono<ServerResponse> streamArticles(ServerRequest request) {
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(articleRepository.findAll(), Article.class);
    }

    public Mono<ServerResponse> fetchArticles(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        return ok()
                .contentType(APPLICATION_JSON)
                .body(articleRepository.findAll().take(size), Article.class);
    }

    public Mono<ServerResponse> createArticle(ServerRequest request) {
        Mono<Article> articleMono = request.bodyToMono(Article.class);
        return ServerResponse.ok().build(articleRepository.save(articleMono));
    }

    public Mono<ServerResponse> createArticleFromJsonObject(ServerRequest request) {

        Mono<Article> article = request.bodyToMono(Article.class);
        article.
        return ServerResponse.ok().build(articleRepository.saveArticle(article));
    }
}
