# spring batch sample

Sample spring batch job

```
mvn package
java -Dspring.batch.job.names=test-job-config -Dspring.profiles.active=test-job-config -jar ./target/sample-1.0.jar
```