package org.helius.scheduling.infrastructure;

import static java.util.Arrays.asList;
import static org.helius.scheduling.infrastructure.CollectionUtil.getElementRandomly;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilTest {

    @Test
    public void test_getElementRandomly() {
        // setup
        List<Integer> numbers = asList(1, 2, 3, 4, 5);

        // execute
        Set<Integer> allNumbersFromList = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            numbers.stream().forEach(number -> allNumbersFromList.add(getElementRandomly(numbers)));
        }
        List<Integer> allNumbersFromListSorted = new ArrayList<>(allNumbersFromList);
        Collections.sort(allNumbersFromListSorted);

        // verify
        assertEquals(numbers, allNumbersFromListSorted);
    }

}
