package com.thefemtech.demo.divasapi.repository;

import com.thefemtech.demo.divasapi.model.Divas;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivasRepository extends ReactiveMongoRepository<Divas, String> {

}
