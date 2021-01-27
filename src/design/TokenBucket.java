package design;

public class TokenBucket {

    private final long maxBucketSize;
    private final long refillRate;

    private long timeStamp;
    private double currentBucketSize;

    public TokenBucket(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;

        currentBucketSize = maxBucketSize;
        timeStamp = System.nanoTime();
    }

    public synchronized boolean allowRequest(long tokens) {
        refill();

        if (currentBucketSize >= tokens) {
            currentBucketSize -= tokens;
            return true;
        }

        return false;
    }

    private void refill() {
        long currentTime = System.nanoTime();
        double tokensToRefill = refillRate * (currentTime - timeStamp) / 1e9;
        currentBucketSize = Math.min(maxBucketSize, currentBucketSize + tokensToRefill);
        timeStamp = currentTime;
    }
}
