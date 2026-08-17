package com.info.jobapp.model;

import java.util.List;

public interface JobService {

    List<Job> getJobs();
    Job addJob(Job job);
    Job getJobById(int id);


}