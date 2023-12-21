package org.helius.scheduling.infrastructure;

import java.util.List;
import java.util.Random;

public class CollectionUtil {

    public static <T> T getElementRandomly(List<T> list) {
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

    public static <T> boolean hasValues(List<T> list) {
        return list != null && list.size() > 0;
    }
}
