package org.helius.scheduling.instance;

import static java.lang.String.format;
import static org.helius.scheduling.infrastructure.NumberUtil.doubleToString;

import java.util.ArrayList;
import java.util.List;

public class InstanceFormatter {

    /**
     * First line: [M] [JF] [B]
     * Second line: [F] followed by processing times of [F] families
     * 
     * From the fourth line, there are [F] groups of [JF] lines, separated by an empty line.
     * Each line in a group represents data of a job, in which the three values are arrival time, due date, and weight,
     * respectively.
     */
    public static String formatInstance(Instance instance) {
        StringBuilder builder = new StringBuilder();

        // First line
        builder.append(format("%d %d %d\n", instance.numberMachines, instance.numberJobsPerFamily, instance.maxBatchSize));

        // Second line
        List<Integer> values = new ArrayList<>();
        values.add(instance.numberFamilies);
        StringBuilder familyBuilder = new StringBuilder();
        familyBuilder.append("%-3d");
        for (int f = 0; f < instance.numberFamilies; f++) {
            Family family = instance.getFamily(f);
            values.add(family.processingTime);
            familyBuilder.append("%4d");
            familyBuilder.append(f == instance.numberFamilies - 1 ? "\n\n" : "");
        }
        builder.append(format(familyBuilder.toString(), values.toArray()));

        // Other lines
        for (int f = 0; f < instance.numberFamilies; f++) {
            Family family = instance.getFamily(f);

            for (int j = 0; j < family.getJobs().size(); j++) {
                Job job = family.getJob(j);
                builder.append(
                        format("%4s%5d %s",
                                job.releaseDate,
                                job.dueDate,
                                doubleToString(job.weight)));
                builder.append(j == family.getJobs().size() - 1 ? "" : "\n");
            }

            builder.append(f == instance.numberFamilies - 1 ? "" : "\n\n");
        }

        // out.println(builder.toString());

        return builder.toString();
    }
}
