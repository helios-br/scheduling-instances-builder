package org.helius.scheduling;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.helius.scheduling.infrastructure.FileUtil.saveToFile;
import static org.helius.scheduling.infrastructure.NumberUtil.doubleToString;
import static org.helius.scheduling.instance.ChiangTypeCreator.createChiangInstances;
import static org.helius.scheduling.instance.InstanceFormatter.formatInstance;

import java.util.Collections;
import java.util.List;

import org.helius.scheduling.instance.Instance;

public class Application {

    public static void main(String[] args) {
        int numInstancesPerCategory = 1;
        List<Integer> numberFamiles = asList(2, 4);
        List<Integer> numberMachines = asList(2, 3);
        List<Integer> numberJobs = asList(20, 40);
        List<Integer> maxBatchSize = asList(3, 4);
        List<Double> alphas = asList(0.25, 0.5, 0.75);
        List<Double> betas = asList(0.25, 0.5, 0.75);

        // List<Integer> numberFamiles = asList(12);
        // List<Integer> numberMachines = asList(4);
        // List<Integer> numberJobs = asList(180);
        // List<Integer> maxBatchSize = asList(4);
        // List<Double> alphas = asList(0.25);
        // List<Double> betas = asList(0.75);

        // Optional
        List<Integer> processingTimeFamilies = Collections.emptyList();
        // List<Integer> processingTimeFamilies = asList(20, 10, 10, 2, 10, 16, 4, 10, 4, 16, 4, 10);

        List<Instance> instances =
                createChiangInstances(
                        numInstancesPerCategory,
                        numberFamiles,
                        numberMachines,
                        numberJobs,
                        maxBatchSize,
                        alphas,
                        betas,
                        processingTimeFamilies);
        for (Instance instance : instances) {
            save(instance);
        }

    }

    private static void save(Instance instance) {
        String fileName =
                format(
                        "COR10-F%d-M%d-JF%d-B%d-%s-%s-%d.txt",
                        instance.numberFamilies,
                        instance.numberMachines,
                        instance.numberJobsPerFamily,
                        instance.maxBatchSize,
                        doubleToString(instance.alpha),
                        doubleToString(instance.beta),
                        instance.id);
        saveToFile(formatInstance(instance), fileName);
    }
}
