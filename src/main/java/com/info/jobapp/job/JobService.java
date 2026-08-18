package com.info.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job findById(Long id);
    Job createJob(Job job);
    boolean updateJob(Long id,Job job);
    boolean deleteJobById(Long id);

    List<Job> PostAllJobs(List<Job> jobs);
}