package com.backend.springboot.docker.controller;

import com.backend.springboot.docker.controller.request.PersonRequest;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.service.JobService;
import com.backend.springboot.docker.service.PersonService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/staff"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class StaffController {

    @Autowired
    JobService jobService;

    @Autowired
    PersonService personService;

    /**
     * Add person to staff
     *
     * @return String with person id.
     */
    @PostMapping(value = "/person/add")
    public String addPerson(@Valid @RequestBody final PersonRequest request) {
        Person savedPerson = personService.add(request);

        return "Person with " + savedPerson.getId() + " id successfully added";
    }

    /**
     * Modify person
     *
     * @return String with person id
     */
    @PatchMapping(value = "/person/modify/{id}")
    public String modifyPerson(@Valid @RequestBody final PersonRequest request, @PathVariable final Integer id)
        throws NotFoundException {

        Person modifiedPerson = personService.getById(id);

        return "Person with " + modifiedPerson.getId() + " id successfully modified";
    }


    /**
     * Retrieve all employees with a specific job id.
     *
     * @return String list with the name of employees
     */
    @GetMapping(value = "/person/{jobId}")
    public List<String> addPerson(@PathVariable final Integer jobId) throws NotFoundException {
        List<Person> employees = personService.listAllByJobId(jobId);
        List<String> staffByJobId = new ArrayList<>();
        for (Person person: employees
             ) {
            staffByJobId.add(person.getJob().getJobName());
        }
        return staffByJobId;
    }
}
