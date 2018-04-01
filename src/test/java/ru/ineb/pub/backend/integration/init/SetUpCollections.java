package ru.ineb.pub.backend.integration.init;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.DockerPort;
import com.palantir.docker.compose.connection.waiting.HealthCheck;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import com.palantir.docker.compose.connection.waiting.SuccessOrFailure;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ineb.pub.backend.integration.IntegrationTest;
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
@org.junit.experimental.categories.Category(IntegrationTest.class)
public class SetUpCollections {
    @Autowired
    private ReactiveMongoOperations operations;
    @Autowired private ArticleRepository articleRepository;
    @Autowired private CategoryRepository categoryRepository;

    @ClassRule
    public static DockerComposeRule dockerPostgres = DockerComposeRule.builder()
            .file("src/test/resources/docker/docker-compose-mongodb.yml")
            //.waitingForService("mongod", HealthChecks.toHaveAllPortsOpen())
            .waitingForHostNetworkedPort(27017, toBeOpen())
            .build();

    private static HealthCheck<DockerPort> toBeOpen() {
        return port -> SuccessOrFailure.fromBoolean(port.isListeningNow(), "" + "" + port + " was not open");
    }

    @Test
    public void init(){

    }
}
