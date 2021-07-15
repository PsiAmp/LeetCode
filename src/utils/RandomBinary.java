package utils;

import java.util.Random;

public class RandomBinary {

    private Random random;
    private int count;
    private int randInt;
    private long randLong;

    public RandomBinary() {
        random = new Random();
        initRandInt();
        initRandLong();
    }

    private void initRandInt() {
        randInt = random.nextInt();
        count = 0;
    }

    private void initRandLong() {
        randLong = random.nextLong();
        count = 0;
    }

    public boolean getNextRand1() {
        if (count > 32)
            initRandInt();

        boolean rand = (randInt & 1) == 1;
        randInt >>= 1;
        count++;

        return rand;
    }

    // Tiny bit faster ~10%
    public boolean getNextRand2() {
        if (count > 31)
            initRandInt();

        count++;

        return ((randInt >>= 1) & 1) == 1;
    }

    public boolean getNextRand3() {
        if (count > 63)
            initRandInt();

        count++;

        return ((randLong >>= 1) & 1) == 1;
    }

    public static void main(String[] args) {
        int n = 1000000000;
        boolean[] b = new boolean[n];

        RandomBinary randomBinary = new RandomBinary();
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        for (int i = 0; i < b.length; i++) {
            b[i] = randomBinary.getNextRand1();
        }
        stopWatch.stopAndPrint("getNextRand1");

        b = new boolean[n];
        Random random = new Random();

//        stopWatch.start();
//        for (int i = 0; i < b.length; i++) {
//            b[i] = random.nextInt() % 2 == 0;
//        }
//        stopWatch.stopAndPrint("random.nextInt()");

        b = new boolean[n];
        randomBinary = new RandomBinary();
        stopWatch.start();
        for (int i = 0; i < b.length; i++) {
            b[i] = randomBinary.getNextRand2();
        }
        stopWatch.stopAndPrint("getNextRand2");

        b = new boolean[n];
        randomBinary = new RandomBinary();
        stopWatch.start();
        for (int i = 0; i < b.length; i++) {
            b[i] = randomBinary.getNextRand2();
        }
        stopWatch.stopAndPrint("getNextRand2");


        b = new boolean[n];
        randomBinary = new RandomBinary();
        stopWatch.start();
        for (int i = 0; i < b.length; i++) {
            b[i] = randomBinary.getNextRand1();
        }
        stopWatch.stopAndPrint("getNextRand1");

        b = new boolean[n];
        randomBinary = new RandomBinary();
        stopWatch.start();
        for (int i = 0; i < b.length; i++) {
            b[i] = randomBinary.getNextRand3();
        }
        stopWatch.stopAndPrint("getNextRand3");

        b = new boolean[n];
        stopWatch.start();
        for (int i = 0; i < n; i++)
            b[i] = Math.random() < 0.5;
        stopWatch.stopAndPrint("Math.random()");
    }
}
