package com.info.jobapp.job;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.status(HttpStatus.OK)
                                .body(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
         return ResponseEntity.status(HttpStatus.CREATED)
                                .body(jobService.createJob(job));
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<Boolean> updateJob(@PathVariable Long jobId, @RequestBody Job job){
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobService.updateJob(jobId, job));
    }
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Boolean> deleteJob(@PathVariable Long jobId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobService.deleteJobById(jobId));
    }

    @PostMapping("/all")
    public ResponseEntity<List<Job>> PostAllJobs(@RequestBody List<Job> jobs){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.PostAllJobs(jobs));
    }

}