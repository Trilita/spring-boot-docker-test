package com.backend.springboot.docker.service;

import com.backend.springboot.docker.controller.request.PersonRequest;
import com.backend.springboot.docker.model.Person;
import javassist.NotFoundException;

import java.util.List;

public interface PersonService {

    List<Person> listAll();

    List<Person> listAllByJobId(Integer jobId) throws NotFoundException;

    Person getById(Integer id) throws NotFoundException;

    Person update(PersonRequest personRequest, Integer id) throws NotFoundException;

    Person add(PersonRequest personRequest);

    void delete(Integer id);
}
