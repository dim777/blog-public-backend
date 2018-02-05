package ru.ineb.pub.backend.init;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.model.Category;
import ru.ineb.pub.backend.model.FeaturedAttributes;
import ru.ineb.pub.backend.model.Lang;
import ru.ineb.pub.backend.repository.ArticleRepository;
import ru.ineb.pub.backend.repository.CategoryRepository;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SetUpCollections {
    @Autowired
    private ReactiveMongoOperations operations;
    @Autowired private ArticleRepository articleRepository;
    @Autowired private CategoryRepository categoryRepository;


    @Before
    public void setUp() {
        operations.collectionExists(Category.class)
                .flatMap(exists -> exists ? operations.dropCollection(Category.class) : Mono.just(exists))
                .flatMap(o -> operations.createCollection(Category.class, CollectionOptions.empty().size(1024 * 1024).maxDocuments(100).capped()))
                .then()
                .block();

        Category category1 = new Category(1L, 0, "category1", "Sample category1");
        Category category2 = new Category(2L, 1, "category2", "Sample category2");
        Category category3 = new Category(3L, 2, "category3", "Sample category3");

        categoryRepository
                .saveAll(Flux.just(
                        category1,
                        category2,
                        category3
                ))
                .then()
                .block();

        operations.collectionExists(Article.class)
                .flatMap(exists -> exists ? operations.dropCollection(Article.class) : Mono.just(exists))
                .flatMap(o -> operations.createCollection(Article.class, CollectionOptions.empty().size(1024 * 1024).maxDocuments(100).capped()))
                .then()
                .block();

        articleRepository
                .saveAll(Flux.just(
                        new Article(1L, "aaa_aaa aaa", "talias-1", Arrays.asList(categoryRepository.findById(1L).block()), "aaa_aaa aaa aaa_aaa aaa", "1 Apr 1990", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file1.png", 0)), //
                        new Article(2L, "bbb_bbb bbb", "talias-2", Arrays.asList(categoryRepository.findById(1L).block(), categoryRepository.findById(2L).block()), "bbb_bbb bbb bbb_bbb bbb", "20 May 1996", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file2.png", 1)), //
                        new Article(3L, "ccc_ccc ccc", "talias-3", Arrays.asList(categoryRepository.findById(3L).block()), "ccc_ccc ccc ccc_ccc ccc", "19 Jun 2010", true, "dim777", null, Lang.EN, null))) //
                .then()
                .block();

    }
}
