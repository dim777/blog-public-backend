package ru.ineb.pub.backend.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.ineb.pub.backend.model.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, Long> {

}
