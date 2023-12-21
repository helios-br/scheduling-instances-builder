package org.helius.scheduling.infrastructure;

import static org.helius.scheduling.infrastructure.NumberUtil.getRandomDoubleInRange;
import static org.helius.scheduling.infrastructure.NumberUtil.getRandomIntegerInRange;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberUtilTest {

    @Test
    public void test_getRandomDoubleInRange() {
        // verify
        for (int i = 0; i < 1000; i++) {
            double randomValue = getRandomDoubleInRange(0, 1);
            assertTrue(randomValue >= 0 && randomValue <= 1);
        }
    }

    @Test
    public void test_getRandomIntegerInRange() {
        // verify
        for (int i = 0; i < 1000; i++) {
            int randomValue = getRandomIntegerInRange(0, 1);
            assertTrue(randomValue >= 0 && randomValue <= 1);
        }
    }

    @Test
    public void test_doubleToString() {
        // verify
        assertEquals("2.35", NumberUtil.doubleToString(2.345));
        assertEquals("0.00", NumberUtil.doubleToString(0.0));
        assertEquals("-5.67", NumberUtil.doubleToString(-5.666));
        assertEquals("0.01", NumberUtil.doubleToString(0.0101));
    }

}
