package ru.ineb.pub.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.model.wrapper.ArticlesWrapper;
import ru.ineb.pub.backend.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleServicesImpl implements ArticleServices{
    @Autowired private ArticleRepository articleRepository;


    @Override
    public ArticlesWrapper getArticlesLimit(int size) {
        Flux<Article> articleFlux = articleRepository.findAll().take(size);
        ArticlesWrapper articlesWrapper = new ArticlesWrapper();
        articlesWrapper.setArticles(articleFlux);
        return articlesWrapper;
    }
}
