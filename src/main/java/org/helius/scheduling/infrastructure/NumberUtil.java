package org.helius.scheduling.infrastructure;

import static java.lang.Math.round;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class NumberUtil {

    public static double getRandomDoubleInRange(double firstValue, double secondValue) {
        Random random = new Random();
        return random.nextDouble() * (secondValue - firstValue) + firstValue;
    }

    public static double roundDouble(double value) {
        return round(value * 100.0) / 100.0;
    }

    public static int getRandomIntegerInRange(int firstValue, int secondValue) {
        Random random = new Random();
        return random.nextInt((secondValue - firstValue) + 1) + firstValue;
    }

    public static String doubleToString(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        return decimalFormat.format(number);
    }
}
