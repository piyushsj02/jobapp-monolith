package com.info.jobapp.controller;

import com.info.jobapp.model.Job;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @GetMapping
    public List<Job> getAllJobs(){
        List<Job> jobs = List.of();
        return jobs;
    }

    @PostMapping
    public Job createJob(@RequestBody Job job){
        return job;
    }
}