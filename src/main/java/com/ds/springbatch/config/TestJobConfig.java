package com.ds.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test-job-config")
public class TestJobConfig {
    private static final Logger log = LoggerFactory.getLogger(TestJobConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job testConfigurationJob(Step step1, Step step2, Step step3) {
        return jobBuilderFactory.get("test-job-config")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1(Tasklet step1Tasklet) {
        return stepBuilderFactory.get("step1")
                .tasklet(step1Tasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step step2(Tasklet step2Tasklet) {
        return stepBuilderFactory.get("step2")
                .tasklet(step2Tasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step step3(Tasklet step3Tasklet) {
        return stepBuilderFactory.get("step3")
                .tasklet(step3Tasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Tasklet step1Tasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("yo step 1");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Tasklet step2Tasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("yo step 2");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Tasklet step3Tasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("yo step 3");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
