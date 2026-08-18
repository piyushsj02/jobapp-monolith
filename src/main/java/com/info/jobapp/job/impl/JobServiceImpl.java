package com.info.jobapp.job.impl;

import com.info.jobapp.company.Company;
import com.info.jobapp.company.CompanyRepository;
import com.info.jobapp.job.Job;
import com.info.jobapp.job.JobRepository;
import com.info.jobapp.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;


    @Override
    public List<Job> findAll() {
        return  jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        if (job.getCompany() != null && job.getCompany().getId() != null) {
            Company company = companyRepository.findById(job.getCompany().getId()).orElse(null);
            if (company != null) {
                job.setCompany(company);
            }
        }
        return jobRepository.save(job);
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Job existingJob = jobRepository.findById(job.getId()).orElse(null);
        if(existingJob == null){
            return false;
        }
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setMinSalary(job.getMinSalary());
        existingJob.setMaxSalary(job.getMaxSalary());
        existingJob.setLocation(job.getLocation());
        jobRepository.save(existingJob);
        return true;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Job> PostAllJobs(List<Job> jobs) {
        return jobRepository.saveAll(jobs);
    }
}