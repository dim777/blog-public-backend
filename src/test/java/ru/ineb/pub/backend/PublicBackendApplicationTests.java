package ru.ineb.pub.backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.model.FeaturedAttributes;
import ru.ineb.pub.backend.model.Lang;
import ru.ineb.pub.backend.repository.ArticleRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PublicBackendApplicationTests {

	@Autowired WebTestClient webTestClient;

	@Autowired ReactiveMongoOperations operations;
	@Autowired ArticleRepository articleRepository;

	@Before
	public void setUp() {

		operations.collectionExists(Article.class) //
				.flatMap(exists -> exists ? operations.dropCollection(Article.class) : Mono.just(exists)) //
				.flatMap(o -> operations.createCollection(Article.class, CollectionOptions.empty().size(1024 * 1024).maxDocuments( 100).capped())) //
				.then() //
				.block();

		articleRepository
				.saveAll(Flux.just(
						new Article(1L, "aaa_aaa aaa", "talias-1", "category1", "aaa_aaa aaa aaa_aaa aaa", "1 Apr 1990", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file1.png", 0)), //
						new Article(2L, "bbb_bbb bbb", "talias-2", "category2", "bbb_bbb bbb bbb_bbb bbb", "20 May 1996", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file2.png", 1)), //
						new Article(3L, "ccc_ccc ccc", "talias-3", "category3", "ccc_ccc ccc ccc_ccc ccc", "19 Jun 2010", true, "dim777", null, Lang.EN, null))) //
				.then() //
				.block();

	}

	@Test
	public void givenRouter_whenGetArticles_thenGotArticlesList() {
		webTestClient
				// We then create a GET request to test an endpoint
				.get().uri("/articles?size=20")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Article.class)
				.hasSize(3)
				.consumeWith(allArticles -> assertThat(allArticles)
						.satisfies(article -> assertThat(article.getResponseBody().size()).isPositive()));

	}

	@Test
	public void givenNewArticleForm_whenDataIsValid_thenSuccess(){
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>(8);
		formData.add("title", "Title 1");
		formData.add("alias", "title-1");
		formData.add("fulltext", "Fulltext fulltext");
		formData.add("created", "01-01-2017");
		formData.add("publish", "true");
		formData.add("createdBy", "dim777");
		formData.add("image", "");
		formData.add("language", "RU");

		webTestClient.post().uri("/article")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(BodyInserters.fromFormData(formData))
				.exchange()
				.expectStatus()
				.isOk();
	}

	/**
	 * Test RESTful API
	 */
	@Test
	public void givenNewArticle_whenDataIsValid_thenSuccess(){
		Article article = new Article();
		article.setId(4L);
		article.setTitle("Title 2");
		article.setAlias("title-2");
		article.setFulltext("Fulltext fulltext");
		article.setCreated("01-02-2017");
		article.setPublish(true);
		article.setCreatedBy("dim777");
		article.setImage(null);
		article.setLanguage(Lang.EN);

		webTestClient.post()
				.uri("/article/json")
				.body(fromObject(article))
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	public void givenArticleAlias_thenGetArticle() {
	    Article expected = new Article(1L, "aaa_aaa aaa", "talias-1", "category1", "aaa_aaa aaa aaa_aaa aaa", "1 Apr 1990", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file1.png", 0));

		webTestClient
				// We then create a GET request to test an endpoint
				.get().uri("/article?alias=talias-1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(Article.class)
                .isEqualTo(expected);
	}

    @Test
    public void givenFeatureArticles_then_receiveArticles() throws Exception {

        List<Article> expected = Arrays.asList(
                new Article(1L, "aaa_aaa aaa", "talias-1", "category1", "aaa_aaa aaa aaa_aaa aaa", "1 Apr 1990", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file1.png", 0)), //
                new Article(2L, "bbb_bbb bbb", "talias-2", "category2", "bbb_bbb bbb bbb_bbb bbb", "20 May 1996", true, "dim777", null, Lang.EN, new FeaturedAttributes("assert/img/file2.png", 1))
        );

        webTestClient.get().uri("/articles/featured?size=20")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Article.class)
                .isEqualTo(expected);


    }
}
