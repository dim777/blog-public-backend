package ru.ineb.pub.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by Dmitry.Erohin dim777@ya.ru on 07.03.2017.
 * Copyright (C) 2017 - present by <a href="https://www.ineb.ru/">Ineb Inc</a>.
 * Please see distribution for license.
 */
@Data
public class Article {
    @Id
    private Long id;
    private String title;
    private String alias;
    private String fulltext;
    private LocalDateTime created;
    private Boolean publish;
    private User createdBy;
    private Byte[] image;
    private Lang language;


}
