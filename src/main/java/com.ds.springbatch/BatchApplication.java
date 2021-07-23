package com.ds.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages={
        "com.ds.springbatch"
})
@EnableBatchProcessing
public class BatchApplication {
    private static final Logger log = LoggerFactory.getLogger(BatchApplication.class);
    public static void main(String... args) {
        log.info("Application launched with args {}", Arrays.toString(args));

        ConfigurableApplicationContext context = SpringApplication.run(BatchApplication.class, args);
        System.exit(SpringApplication.exit(context));
    }

    @PostConstruct
    public void init() {
        log.info("BatchApplication launched");
    }
}