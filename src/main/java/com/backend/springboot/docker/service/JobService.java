package com.backend.springboot.docker.service;

import com.backend.springboot.docker.model.Job;
import javassist.NotFoundException;

import java.util.List;

public interface JobService {

    List<Job> listAll();

    Job getById(Integer id) throws NotFoundException;

    Job saveOrUpdate(Job product);

    void delete(Integer id);

}
