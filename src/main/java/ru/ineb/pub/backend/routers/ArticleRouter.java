package ru.ineb.pub.backend.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.ineb.pub.backend.handlers.ArticleHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Created by rb052775 on 07.11.2017.
 */
@Configuration
public class ArticleRouter {
    @Bean
    public RouterFunction<ServerResponse> routeArtcile(ArticleHandler articleHandler) {
        return RouterFunctions
                .route(GET("/articles").and(accept(APPLICATION_JSON)), articleHandler::fetchArticles)
                .andRoute(GET("/articles").and(accept(APPLICATION_STREAM_JSON)), articleHandler::streamArticles)
                .andRoute(POST("/article").and(accept(APPLICATION_JSON_UTF8)), articleHandler::createArticle)
                .andRoute(POST("/article/json").and(accept(APPLICATION_JSON_UTF8)), articleHandler::createArticleFromJsonObject);
    }
}
