package ru.ineb.pub.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
/*    @Id
    @JsonIgnore
    private ObjectId _id;*/

    private String title;

    @Indexed(unique = true)
    private String alias;

    private String fulltext;

    private String created;

    private Boolean publish;

    private String createdBy;

    private Byte[] image;

    private Lang language;
}
