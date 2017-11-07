package ru.ineb.pub.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableReactiveMongoRepositories("ru.ineb.pub.backend.repository")
@EnableWebFlux
public class AppConfig {}
