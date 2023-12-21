package org.helius.scheduling.instance;

import static java.lang.String.format;
import static org.helius.scheduling.instance.Job.generateAllProcessingTimeOptionsForSelection;

public class InstanceValidator {

    public static void validateChiangInstance(Instance instance) {
        int totalFamilies = 0;
        int totalJobs = 0;

        for (Family family : instance.getFamilies()) {
            totalFamilies++;

            int totalJobsPerFamily = 0;

            for (Job job : family.getJobs()) {
                totalJobs++;
                totalJobsPerFamily++;

                if (job.processingTime != family.processingTime) {
                    throw new RuntimeException("job.processingTime != family.processingTime");
                }

                if (job.dueDate < 0) {
                    throw new RuntimeException("job.dueDate < 0");
                }

                if (job.releaseDate < 0) {
                    throw new RuntimeException("job.releaseDate < 0");
                }

                if (job.dueDate < 0) {
                    throw new RuntimeException("job.dueDate < 0");
                }

                if (job.processingTime <= 0) {
                    throw new RuntimeException("job.processingTime <= 0");
                }

                if (job.releaseDate > job.dueDate) {
                    throw new RuntimeException(format("job.releaseDate (%s) >= job.dueDate (%s)", job.releaseDate, job.dueDate));
                }

                if (job.weight < 0) {
                    throw new RuntimeException("job.weight < 0");
                }

                if (job.weight > 1) {
                    throw new RuntimeException("job.weight >= 1");
                }

                if (job.familyId != family.id) {
                    throw new RuntimeException("job.familyId != f");
                }

                if (!generateAllProcessingTimeOptionsForSelection().contains(job.processingTime)) {
                    throw new RuntimeException("!generateAllProcessingTimeOptionsForSelection().contains(job.processingTime)");
                }
            }

            if (totalJobsPerFamily != instance.numberJobsPerFamily) {
                throw new RuntimeException("totalJobsPerFamily != instance.numberJobsPerFamily");
            }
        }

        if (totalFamilies != instance.numberFamilies) {
            throw new RuntimeException("totalFamilies != instance.numberFamilies");
        }

        if (totalJobs != instance.numberJobs) {
            throw new RuntimeException("totalJobs != instance.numberJobs");
        }
    }
}
