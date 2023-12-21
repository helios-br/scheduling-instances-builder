package org.helius.scheduling.instance;

import java.util.ArrayList;
import java.util.List;

public class Instance {

    public final int id;
    public final int maxBatchSize;
    public final int numberFamilies;
    public final int numberJobs;
    public final int numberJobsPerFamily;
    public final int numberMachines;
    public final double alpha;
    public final double beta;

    private List<Family> families = new ArrayList<>();

    public Instance(
            int id,
            int numberMachines,
            int numberFamilies,
            int numberJobs,
            int maxBatchSize,
            double alpha,
            double beta) {
        this.id = id;
        this.maxBatchSize = maxBatchSize;
        this.numberFamilies = numberFamilies;
        this.numberJobs = numberJobs;
        this.numberMachines = numberMachines;
        this.alpha = alpha;
        this.beta = beta;
        this.numberJobsPerFamily = numberJobs / numberFamilies;
    }

    public void addFamily(Family family) {
        families.add(family);
    }

    public List<Family> getFamilies() {
        return families;
    }

    public Family getFamily(int index) {
        return families.get(index);
    }
}
