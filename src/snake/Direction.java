package snake;

public class Direction {

    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    public static boolean isOpposite(int d1, int d2) {
        return Math.abs(d1 - d2) == 2;
    }
}
