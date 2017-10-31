package ru.ineb.pub.backend.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ArticleController {

    @RequestMapping("/article")
    public HttpEntity<Article> getArticleById(
            @RequestParam(value = "id", required = false, defaultValue = "-1")
                    Long id) {

        if(id < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Article>(greeting, HttpStatus.OK);
    }

    @RequestMapping("/article")
    public HttpEntity<Article> getArticlesByAuthor(Principal user) {

        if(id < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        /*
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        */

        return new ResponseEntity<Article>(greeting, HttpStatus.OK);
    }
}
