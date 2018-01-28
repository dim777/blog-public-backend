package ru.ineb.pub.backend.services;

import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.model.wrapper.ArticlesWrapper;

import java.util.List;

public interface ArticleServices {
    ArticlesWrapper getArticlesLimit(int size);
}
