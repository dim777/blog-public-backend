package ru.ineb.pub.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dmitry.Erohin dim777@ya.ru on 28.03.2017.
 * Copyright (C) 2017 - present by <a href="https://www.ineb.ru/">Ineb Inc</a>.
 * Please see distribution for license.
 */
public interface ArticleRepository extends ReactiveMongoRepository<Article, Long> {
    Mono<Article> findById(Long id);
    List<Article> getArticlesByUserId(Long userId);
}
