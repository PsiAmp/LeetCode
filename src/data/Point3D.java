package data;

public class Point3D extends Point2D {

    private int z;

    public Point3D() {
        this.setX(1);
        z = 0;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        if (isCorrect(z))
            return;

        this.z = z;
    }
}
