package utils;

public class StopWatch {

    private long timeMillis;

    public StopWatch() {

    }

    public void start() {
        timeMillis = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - timeMillis;
    }

    public long stopAndPrint(String msg) {
        long stopTime = stop();

        if (msg != null)
            System.out.print("[" + msg + "] ");
        System.out.println("Time passed: " + stopTime + " ms");

        return stopTime;
    }
}
