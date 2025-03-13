package com.example.batch.common.job.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MySimpleJobConfig {

    @Bean
    public Job mySimpleJob(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        log.info(">>>>> mySimpleJob");
        return new JobBuilder("mySimpleJob", jobRepository)
                .start(simpleTaskStep(null, transactionManager, jobRepository))
                .next(simpleTaskStep(null, transactionManager, jobRepository))
                .build();
    }

    @Bean
    @JobScope
    public Step simpleTaskStep(
            @Value("#{jobParameters[version]}") String version,
            PlatformTransactionManager transactionManager,
            JobRepository jobRepository
    ) {
        log.info(">>>>> taskStep version: {}", version);

        return new StepBuilder("simpleTaskStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is a simple step");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
