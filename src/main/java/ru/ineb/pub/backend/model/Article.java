package ru.ineb.pub.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

/**
 * Created by Dmitry.Erohin dim777@ya.ru on 07.03.2017.
 * Copyright (C) 2017 - present by <a href="https://www.ineb.ru/">Ineb Inc</a>.
 * Please see distribution for license.
 */
@Data
@Document
public class Article {
    @Id
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Indexed(unique = true)
    @NotBlank
    @Size(max = 300)
    private String alias;

    @Indexed(unique = true)
    @NotBlank
    private String category;

    private String fulltext;

    private String created;

    private Boolean publish;

    private String createdBy;

    private Byte[] image;

    private Lang language;

    private FeaturedAttributes featuredAttributes;

    public Article(){}

    public Article(Long id, @NotBlank @Size(max = 200) String title, @NotBlank @Size(max = 300) String alias, @NotBlank String category, String fulltext, String created, Boolean publish, String createdBy, Byte[] image, Lang language, FeaturedAttributes featuredAttributes) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.category = category;
        this.fulltext = fulltext;
        this.created = created;
        this.publish = publish;
        this.createdBy = createdBy;
        this.image = image;
        this.language = language;
        this.featuredAttributes = featuredAttributes;
    }
}


