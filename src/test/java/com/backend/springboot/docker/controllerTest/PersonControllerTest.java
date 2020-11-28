package com.backend.springboot.docker.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.backend.springboot.docker.controller.StaffController;
import com.backend.springboot.docker.controller.mapper.PersonMapper;
import com.backend.springboot.docker.controller.request.PersonRequest;
import com.backend.springboot.docker.model.Job;
import com.backend.springboot.docker.model.Person;
import com.backend.springboot.docker.repository.PersonRepository;
import com.backend.springboot.docker.service.PersonService;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonControllerTest {

    private static final String PERSON_NAME = "Usuario 1";
    private static final String PERSON_NAME_2 = "Usuario 2";

    @InjectMocks
    private StaffController controller;

    @Mock
    private PersonService personService;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    private PersonRequest addExamplePersonRequest = new PersonRequest();
    private PersonRequest modifiedExamplePersonRequest = new PersonRequest();

    private Person addedPerson = new Person();
    private Person dbPerson = new Person();
    private Person dbPerson3 = new Person();
    private Person dbPerson4 = new Person();
    private Person modifiedPerson = new Person();

    private List<Person> personList;

    @Before
    public void setUp() {

        //Job
        Job job = new Job();
        job.setId(1);

        //DB person
        dbPerson.setText("PERSON 1");
        dbPerson.setId(1);
        dbPerson.setJob(job);

        //DB person 3
        dbPerson3.setText("PERSON 3");
        dbPerson3.setId(1);
        dbPerson3.setJob(job);

        //DB person 4
        dbPerson4.setText("PERSON 4");
        dbPerson4.setId(1);
        dbPerson4.setJob(job);

        // personRequest add
        addExamplePersonRequest.setDate(null);
        addExamplePersonRequest.setName(PERSON_NAME);
        addExamplePersonRequest.setJob(1);

        // personRequest modified
        modifiedExamplePersonRequest.setName(PERSON_NAME_2);

        // person object add
        addedPerson.setText(PERSON_NAME);
        addedPerson.setDate(null);

        addedPerson.setJob(job);

        // person object modified
        addedPerson.setText(PERSON_NAME_2);

        // list with person who have job one
        personList.add(dbPerson);
        personList.add(dbPerson3);
        personList.add(dbPerson4);
    }

    @Test
    public void addPerson() {
        Mockito.when(
            personService.add(addExamplePersonRequest)).thenReturn(addedPerson);
        Mockito.when(
            personMapper.transformRequestToModel(addExamplePersonRequest)).thenReturn(addedPerson);
        Mockito.when(
            personRepository.save(addedPerson)).thenReturn(addedPerson);

        final String name = controller
            .addPerson(addExamplePersonRequest);

       assertEquals(name, PERSON_NAME);
    }

    @Test
    public void modifyPerson() throws NotFoundException {
        Mockito.when(
            personService.update(modifiedExamplePersonRequest, 5)).thenReturn(modifiedPerson);
        Mockito.when(
            personMapper.transformRequestToModel(modifiedExamplePersonRequest)).thenReturn(modifiedPerson);
        Mockito.when(
            personService.getById(1)).thenReturn(dbPerson);
        Mockito.when(
            personRepository.save(modifiedPerson)).thenReturn(modifiedPerson);

        assertEquals(modifiedPerson.getText(), PERSON_NAME_2);
    }

    @Test
    public void getPersonsWithSpecificJob() throws NotFoundException {
        Mockito.when(
            personService.listAllByJobId(1)).thenReturn(personList);
        Mockito.when(
            personRepository.findAllByJob(1)).thenReturn(personList);

        assertNotNull(personList);
    }
}