package com.backend.springboot.docker.service;

import com.backend.springboot.docker.controller.mapper.PersonMapper;
import com.backend.springboot.docker.controller.request.PersonRequest;
import com.backend.springboot.docker.model.Job;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    JobService jobService;

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> listAll() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @Override
    public List<Person> listAllByJobId (Integer jobId) throws NotFoundException {
        Job optJob = jobService.getById(jobId);
        List<Person> employees;
        employees = personRepository.findAllByJob(jobId);
        if (employees == null) {
            throw new NotFoundException("No employee has work with id" + jobId);
        }
        return employees;
    }

    @Override
    public Person getById(Integer id) throws NotFoundException {
        Optional<Person> optPerson = personRepository.findById(id);
        if (optPerson.isPresent()) {
            return optPerson.get();
        } else {
            throw new NotFoundException("Person not found with id " + id);
        }
    }

    @Override
    public Person add(PersonRequest personRequest) {
        Person person = personMapper.transformRequestToModel(personRequest);
        return personRepository.save(person);
    }

    @Override
    public Person update(PersonRequest personRequest, Integer id) throws NotFoundException{
        getById(id);
        return add(personRequest);
    }

    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}
