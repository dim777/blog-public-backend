package ru.ineb.pub.backend.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;

/**
 * Created by Dmitry.Erohin dim777@ya.ru on 28.03.2017.
 * Copyright (C) 2017 - present by <a href="https://www.ineb.ru/">Ineb Inc</a>.
 * Please see distribution for license.
 */
public interface ArticleRepository extends ReactiveMongoRepository<Article, Long> {
    Mono<Article> findByAlias(String alias);
    Flux<Article> findByFeaturedAttributesIsNotNull();
}
