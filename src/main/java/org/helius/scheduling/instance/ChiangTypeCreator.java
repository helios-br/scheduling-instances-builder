package org.helius.scheduling.instance;

import static java.lang.System.out;
import static org.helius.scheduling.infrastructure.CollectionUtil.hasValues;
import static org.helius.scheduling.infrastructure.NumberUtil.getRandomDoubleInRange;
import static org.helius.scheduling.infrastructure.NumberUtil.getRandomIntegerInRange;
import static org.helius.scheduling.instance.InstanceValidator.validateChiangInstance;
import static org.helius.scheduling.instance.Job.getRandomProcessingTime;

import java.util.ArrayList;
import java.util.List;

public class ChiangTypeCreator {

    public static List<Instance> createChiangInstances(
            int numInstancesPerCategory,
            List<Integer> numberFamiles,
            List<Integer> numberMachines,
            List<Integer> numberJobs,
            List<Integer> maxBatchSizes,
            List<Double> alphas,
            List<Double> betas,
            List<Integer> processingTimeFamilies) {
        List<Instance> instances = new ArrayList<>();

        out.println("@ Creating new instances...");

        // create all instances

        for (Integer numFamilies : numberFamiles) {
            for (Integer numMachines : numberMachines) {
                for (Integer maxBatchSize : maxBatchSizes) {
                    for (Double alpha : alphas) {
                        for (Double beta : betas) {
                            for (Integer numJobs : numberJobs) {
                                instances.addAll(
                                        createInstancesCategory(
                                                numInstancesPerCategory,
                                                numMachines,
                                                numFamilies,
                                                numJobs,
                                                maxBatchSize,
                                                alpha,
                                                beta,
                                                processingTimeFamilies));
                            }
                        }
                    }
                }
            }
        }

        out.println("# All instances created!");

        return instances;
    }

    private static List<Instance> createInstancesCategory(
            int numInstancesPerCategory,
            int numMachines,
            int numFamilies,
            int numJobs,
            int maxBatchSize,
            double alpha,
            double beta,
            List<Integer> defaultProcessingTimeFamilies) {
        List<Instance> instances = new ArrayList<>();

        for (int i = 0, instanceId = 1; i < numInstancesPerCategory; i++, instanceId++) {
            Instance instance = new Instance(instanceId, numMachines, numFamilies, numJobs, maxBatchSize, alpha, beta);

            // Create processing time for all jobs

            List<Integer> processingTimeFamilies = new ArrayList<>();
            double sumProcessingTimes = 0;

            for (int f = 0; f < numFamilies; f++) {
                int processingTime =
                        hasValues(defaultProcessingTimeFamilies) ? defaultProcessingTimeFamilies.get(f)
                                : getRandomProcessingTime();

                processingTimeFamilies.add(processingTime);
                sumProcessingTimes += instance.numberJobsPerFamily * processingTime;
            }

            // Create release date for all jobs

            double Bm = maxBatchSize * numMachines;
            double sumByBm = sumProcessingTimes / Bm;
            double releaseDateMaxRange = alpha * sumByBm;

            List<Integer> releaseDates = new ArrayList<>();
            for (int j = 0; j < numJobs; j++) {
                releaseDates.add(getRandomIntegerInRange(0, (int) releaseDateMaxRange));
            }

            // Create due date for all jobs

            double dueDateMaxRange = beta * sumByBm;

            List<Integer> dueDates = new ArrayList<>();
            for (int j = 0; j < numJobs; j++) {
                int dueDate = releaseDates.get(j) + getRandomIntegerInRange(0, (int) dueDateMaxRange);
                dueDates.add(dueDate);
            }

            // Create families and jobs

            int currentJobIndex = 0;

            for (int f = 0; f < numFamilies; f++) {
                Family family = new Family(f, processingTimeFamilies.get(f));
                for (int j = 0; j < instance.numberJobsPerFamily; j++) {
                    family.createJob(
                            releaseDates.get(currentJobIndex),
                            dueDates.get(currentJobIndex),
                            getRandomDoubleInRange(0.00, 1));
                    currentJobIndex++;
                }
                instance.addFamily(family);
            }

            if (currentJobIndex != numJobs) {
                throw new RuntimeException("Current job index not expected: " + currentJobIndex);
            }

            validateChiangInstance(instance);
            instances.add(instance);
        }

        return instances;
    }
}
