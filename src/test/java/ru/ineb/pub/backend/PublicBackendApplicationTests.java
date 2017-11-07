package ru.ineb.pub.backend;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.model.Article;
import ru.ineb.pub.backend.model.Lang;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PublicBackendApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

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
				.hasSize(2)
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
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData(formData))
				.exchange()
				.expectStatus()
				.isOk();
	}

	@Test
	public void givenNewArticle_whenDataIsValid_thenSuccess(){
		Article article = new Article();
		article.setTitle("Title 1");
		article.setAlias("title-1");
		article.setFulltext("Fulltext fulltext");
		article.setCreated("01-01-2017");
		article.setPublish(true);
		article.setCreatedBy("dim777");
		article.setImage(null);
		article.setLanguage(Lang.EN);

		webTestClient.post()
				.uri("/article/json")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(article), Article.class)
				.exchange()
				.expectStatus().isOk();
		/*
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated Tweet");
		 */
	}
}
