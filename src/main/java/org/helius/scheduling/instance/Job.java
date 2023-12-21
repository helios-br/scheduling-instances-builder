package org.helius.scheduling.instance;

import static java.util.Arrays.asList;
import static java.util.Collections.nCopies;
import static org.helius.scheduling.infrastructure.CollectionUtil.getElementRandomly;

import java.util.ArrayList;
import java.util.List;

public class Job {

    public final int familyId;
    public final int processingTime;
    public final int releaseDate;
    public final int dueDate;
    public final double weight;

    public Job(int familyId, int processingTime, int releaseDate, int dueDate, double weight) {
        this.familyId = familyId;
        this.releaseDate = releaseDate;
        this.dueDate = dueDate;
        this.weight = weight;
        this.processingTime = processingTime;
    }

    public static int getRandomProcessingTime() {
        return getElementRandomly(generateAllProcessingTimeOptionsForSelection());
    }

    public static List<Integer> generateAllProcessingTimeOptionsForSelection() {
        List<Integer> allOptions = new ArrayList<>();
        List<Integer> processingTimes = asList(2, 4, 10, 16, 20);
        List<Integer> probabilities = asList(2, 2, 3, 2, 1);

        for (int i = 0; i < processingTimes.size(); i++) {
            int processingTime = processingTimes.get(i);
            int probability = probabilities.get(i);
            allOptions.addAll(nCopies(probability, processingTime));
        }

        return allOptions;
    }
}
