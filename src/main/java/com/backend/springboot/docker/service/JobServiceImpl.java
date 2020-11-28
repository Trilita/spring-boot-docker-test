package com.backend.springboot.docker.service;

import com.backend.springboot.docker.model.Job;
import com.backend.springboot.docker.repository.JobRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> listAll() {
        List<Job> jobs = new ArrayList<>();
        jobRepository.findAll().forEach(jobs::add);
        return jobs;
    }

    @Override
    public Job getById (Integer id) throws NotFoundException{
        Optional<Job> optJob = jobRepository.findById(id);
        if (optJob.isPresent()) {
            return optJob.get();
        } else {
            throw new NotFoundException("Job not found with id " + id);
        }
    }

    @Override
    public Job saveOrUpdate(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void delete(Integer id) {
        jobRepository.deleteById(id);
    }
}
