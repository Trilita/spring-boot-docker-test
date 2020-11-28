package com.backend.springboot.docker.repository;

import com.backend.springboot.docker.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT person FROM Person person WHERE person.job = :id")
    List<Person> findAllByJob(@Param("id") Integer id);

}
