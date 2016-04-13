package com.smhumayun.datascience.algorithms.parallelism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Humayun on 4/13/2016.
 */
public class MultiCoreParallelism {

    public static int randomNumbersToGenerate = 40000000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int logicalProcessorsCount = Runtime.getRuntime().availableProcessors();
        System.out.println("--------------------------------------------------------");
        System.out.println("Available Logical Processors : " + logicalProcessorsCount);
        System.out.println("Random Numbers To Generate = " + randomNumbersToGenerate);
        System.out.println("--------------------------------------------------------");
        for(int i = 1; i <= 30; i++) {
            System.out.print(i + " ");
            generateRandomNumbersWithParallelism(1);
            generateRandomNumbersWithParallelism(logicalProcessorsCount);
            System.out.println();
        }
        System.out.println("--------------------------------------------------------");
    }

    public static void generateRandomNumbersWithParallelism (int parallelismFactor) throws ExecutionException, InterruptedException {
        System.out.print(parallelismFactor + " ");
        int randomNumbersPortionToGenerate = randomNumbersToGenerate/parallelismFactor;
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>(parallelismFactor);
        ExecutorService executorService = Executors.newFixedThreadPool(parallelismFactor);
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < parallelismFactor; i++) {
            futures.add(executorService.submit(new RandomNumberGeneratorCallable(randomNumbersPortionToGenerate)));
        }
        for(int i = 0; i < parallelismFactor; i++) {
            futures.get(i).get();
        }
        long endTime = System.currentTimeMillis();
        System.out.print((endTime - startTime) + " ");
        executorService.shutdown();
    }

}
