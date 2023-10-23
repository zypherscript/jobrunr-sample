package com.example.jobrunrsample;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobrunrSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(JobrunrSampleApplication.class, args);
  }

  @Bean
  public StorageProvider storageProvider(JobMapper jobMapper) {
    InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
    storageProvider.setJobMapper(jobMapper);
    return storageProvider;
  }

  @Bean
  CommandLineRunner runner(JobScheduler jobScheduler, SampleJobService sampleJobService) {
    return args -> {
      jobScheduler.enqueue(() -> sampleJobService.executeJob("test job"));

      jobScheduler.scheduleRecurrently(Cron.every5minutes(),
          () -> sampleJobService.executeJob("test recently job"));
    };
  }

}
