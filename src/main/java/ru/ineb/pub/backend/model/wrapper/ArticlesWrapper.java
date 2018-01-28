package ru.ineb.pub.backend.model.wrapper;

import lombok.Data;
import reactor.core.publisher.Flux;
import ru.ineb.pub.backend.model.Article;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticlesWrapper implements Serializable {
    private Flux<Article> articles;
}
