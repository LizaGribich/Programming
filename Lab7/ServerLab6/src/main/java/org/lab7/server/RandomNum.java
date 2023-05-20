package org.lab7.server;

import org.lab7.server.models.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNum {
    public int createRandomNum(MapWrapper<Integer, Vehicle> hashMap) {
        List<Integer> a = new ArrayList<>();
        for (Integer key : hashMap.keySet()) {
            a.add(hashMap.get(key).getId());
        }
        int randomNum;
        try {
            randomNum = ThreadLocalRandom.current().nextInt(Collections.max(a), Collections.max(a)+100);
        } catch (NoSuchElementException e) {
            randomNum = ThreadLocalRandom.current().nextInt(1, 100);
        }

        return randomNum;
    }
}