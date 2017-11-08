package ru.ineb.pub.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Arrays;

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

    private String title;

    @Indexed(unique = true)
    private String alias;

    private String fulltext;

    private String created;

    private Boolean publish;

    private String createdBy;

    private Byte[] image;

    private Lang language;

    public Article id(Long id){
        this.id = id;
        return this;
    }

    public Article title(String title){
        this.title = title;
        return this;
    }
    public Article alias(String alias){
        this.alias = alias;
        return this;
    }
    public Article fulltext(String fulltext){
        this.fulltext = fulltext;
        return this;
    }
    public Article created(String created){
        this.created = created;
        return this;
    }
    public Article publish(Boolean publish){
        this.publish = publish;
        return this;
    }
    public Article createdBy(String createdBy){
        this.createdBy = createdBy;
        return this;
    }
    public Article image(Byte[] image){
        this.image = image;
        return this;
    }
    public Article language(Lang language){
        this.language = language;
        return this;
    }

}
