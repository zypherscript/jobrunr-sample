package com.example.jobrunrsample;

import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {

  @Job(retries = 2)
  public void executeJob(String variable) {

    System.out.println(variable);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    } finally {
      System.out.println("job has finished");
    }
  }
}
