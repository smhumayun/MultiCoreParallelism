package com.smhumayun.datascience.algorithms.parallelism;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Humayun on 4/13/2016.
 */
public class RandomNumberGeneratorCallable implements Callable<Integer> {

    private int numbersToGenerate;

    public RandomNumberGeneratorCallable(int numbersToGenerate) {
        this.numbersToGenerate = numbersToGenerate;
    }

    public Integer call() throws Exception {
        Random random = new Random();
        for(int i = 0; i < numbersToGenerate; i++) {
            random.nextInt();
        }
        return 0;
    }

}
