package org.helius.scheduling.instance;

import java.util.ArrayList;
import java.util.List;

public class Family {

    public final int id;
    public final int processingTime;

    private List<Job> jobs = new ArrayList<>();

    public Family(int id, int processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }

    public Job createJob(int releaseDate, int dueDate, double weight) {
        Job job = new Job(id, processingTime, releaseDate, dueDate, weight);
        jobs.add(job);
        return job;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Job getJob(int index) {
        return jobs.get(index);
    }
}
