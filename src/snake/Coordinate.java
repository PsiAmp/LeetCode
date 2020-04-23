package snake;

class Coordinate {
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Coordinate c = (Coordinate) obj;
        return x == c.x && y == c.y;
    }

    int x;
    int y;
}
