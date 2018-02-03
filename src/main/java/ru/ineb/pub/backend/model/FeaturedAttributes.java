package ru.ineb.pub.backend.model;

import lombok.Data;

@Data
public class FeaturedAttributes {
    private String bgImage;
    private Integer priority;

    public FeaturedAttributes(){}

    public FeaturedAttributes(String bgImage, Integer priority){
        this.bgImage = bgImage;
        this.priority = priority;
    }
}
