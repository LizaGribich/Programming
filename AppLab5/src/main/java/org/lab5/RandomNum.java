package org.lab5;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNum {
    public int createRundomNum(List<Integer> a) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 999 + 1);
        while (a.contains(randomNum)) {
            randomNum = ThreadLocalRandom.current().nextInt(1, 999 + 1);
        }
        return randomNum;
    }
}