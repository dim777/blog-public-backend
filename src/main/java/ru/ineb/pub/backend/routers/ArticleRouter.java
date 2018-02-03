package ru.ineb.pub.backend.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import ru.ineb.pub.backend.handlers.ArticleHandler;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.repository.ArticleRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by rb052775 on 07.11.2017.
 */
@Configuration
public class ArticleRouter {

    @Bean
    public RouterFunction<ServerResponse> routeArtcile(ArticleHandler articleHandler){
        /*RouterFunction<ServerResponse> restfulRouter =
                RouterFunctions
                        //.route(GET("/"), serverRequest -> ok().body(Flux.fromIterable(actors), Actor.class))
                        .route(POST("/"), serverRequest -> serverRequest.bodyToFlux(Article.class)
                                .doOnNext(articleRepository::save)
                                .then(ok().build()));*/

         return RouterFunctions
                 .route(GET("/article").and(accept(APPLICATION_JSON)), articleHandler::fetchArticle)
                 .andRoute(GET("/articles").and(accept(APPLICATION_JSON)), articleHandler::fetchArticles)
                 .andRoute(GET("/articles").and(accept(APPLICATION_STREAM_JSON)), articleHandler::streamArticles)
                 .andRoute(GET("/articles/featured").and(accept(APPLICATION_JSON_UTF8)), articleHandler::fetchFeaturedArticles)
                 .andRoute(POST("/article").and(accept(APPLICATION_JSON_UTF8)), articleHandler::createArticle)
                 .andRoute(POST("/article/json"), articleHandler::createArticle);
         //.andNest(path("/article/json"), restfulRouter);
    }
}
