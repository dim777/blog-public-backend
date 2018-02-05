package ru.ineb.pub.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Category {
    @Id
    private Long id;
    @NotBlank
    private Integer priority;
    @NotBlank
    private String name;

    private String description;

    public Category(){}

    public Category(Long id, @NotBlank Integer priority, @NotBlank String name, String description) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.description = description;
    }
}
