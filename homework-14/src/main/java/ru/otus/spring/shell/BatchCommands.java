package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    private final JobLauncher jobLauncher;

    private final Job migrateMongoToPg;


    @SuppressWarnings("unused")
    @ShellMethod(value = "startMigrationJob", key = "start")
    public void startMigrationJob() {
        try {
            JobExecution execution = jobLauncher.run(migrateMongoToPg, new JobParameters());
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            throw new RuntimeException(e);
        }
    }
}