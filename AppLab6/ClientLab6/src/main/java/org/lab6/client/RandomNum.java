package org.lab6.client;

import org.lab6.client.models.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNum {
    public int createRandomNum(MapWrapper<Integer, Vehicle> hashMap) {
        List<Integer> a = new ArrayList<>();
        for (Integer key : hashMap.keySet()) {
            a.add(hashMap.get(key).getId());
        }

        int randomNum = ThreadLocalRandom.current().nextInt(Collections.max(a), Collections.max(a)+100);
        return randomNum;
    }
}