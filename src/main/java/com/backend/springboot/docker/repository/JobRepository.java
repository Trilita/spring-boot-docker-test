package com.backend.springboot.docker.repository;

import com.backend.springboot.docker.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Integer> {

}
