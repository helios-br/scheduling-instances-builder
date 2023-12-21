package org.helius.scheduling.instance;

import static java.util.Arrays.asList;
import static org.helius.scheduling.instance.Job.generateAllProcessingTimeOptionsForSelection;
import static org.helius.scheduling.instance.Job.getRandomProcessingTime;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class JobTest {

    @Test
    public void test_generateAllProcessingTimeOptionsForSelection() {
        // setup
        List<Integer> expectedOptions = asList(2, 2, 4, 4, 10, 10, 10, 16, 16, 20);

        // execute
        List<Integer> allOptions = generateAllProcessingTimeOptionsForSelection();

        // verify
        Assert.assertEquals(expectedOptions, allOptions);
    }

    @Test
    public void test_getRandomProcessingTime() {
        // setup
        List<Integer> processingTimes = asList(2, 4, 10, 16, 20);

        // verify
        for (int i = 0; i < 1000; i++) {
            Assert.assertTrue(processingTimes.contains(getRandomProcessingTime()));
        }

    }

}
