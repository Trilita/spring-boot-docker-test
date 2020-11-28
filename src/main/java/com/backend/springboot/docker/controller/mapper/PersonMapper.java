package com.backend.springboot.docker.controller.mapper;

import com.backend.springboot.docker.controller.request.PersonRequest;
import com.backend.springboot.docker.model.Job;
import com.backend.springboot.docker.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person transformRequestToModel(PersonRequest request) {
        Person person = new Person();
        Job job = new Job();
        person.setId(request.getId());
        person.setText(request.getName());
        person.setDate(request.getDate() != null ? request.getDate() : null);
        person.setJob(job);
        job.setId(request.getJob() != 0 ? request.getJob() : null);
        return person;
    }
}
