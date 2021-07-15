package data;

public class Point2D {

    // Incapsulation
    // Polymorphism
    // Inheritance

    // public
    // protected
    // private

    private int x;
    private int y;

    public Point2D() {
        this.setX(0);
        this.setY(0);
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void myFunc() {
        x = 1;
        this.x = 1;
    }

    public void myFunc(int b) {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (isCorrect(x))
            return;

        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (isCorrect(y))
            return;

        this.y = y;
    }

    protected boolean isCorrect(int a) {
        return a >= 0;
    }
}
