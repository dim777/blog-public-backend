package ru.ineb.pub.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.ineb.pub.backend.init.SetUpCollections;
import ru.ineb.pub.backend.model.Category;

import java.util.Arrays;
import java.util.List;

public class CategoriesEndpointsTests extends SetUpCollections {
    @Autowired private WebTestClient webTestClient;

    @Test
    public void givenCategoriesEndpoint_then_receiveCategories() throws Exception {
        List<Category> expected = Arrays.asList(
                new Category(1L, 0, "category1", "Sample category1"),
                new Category(2L, 1, "category2", "Sample category2"),
                new Category(3L, 2, "category3", "Sample category3")
        );

        webTestClient.get().uri("/categories?size=20")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Category.class)
                .isEqualTo(expected);
    }
}
