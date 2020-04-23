package snake;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {



    public final Coordinate[] MOVEMENT = {
            new Coordinate(-1, 0), // Left
            new Coordinate(0, -1), // Up
            new Coordinate(1, 0),  // Right
            new Coordinate(0, 1)   // Down
    };

    private Deque<Coordinate> body = new LinkedList<>();
    private int snakeDirection;
    private boolean grow = false;

    public Snake(int boardWidth, int boardHeight) {
        Coordinate startingCoordinate = new Coordinate(boardWidth / 2, boardHeight / 2);
        body.add(startingCoordinate);
        snakeDirection = Direction.UP;
    }

    public void update() {
        Coordinate currentHead = body.peek();
        Coordinate newHead = new Coordinate(currentHead.x + MOVEMENT[snakeDirection].x, currentHead.y + MOVEMENT[snakeDirection].y);
        body.push(newHead);

        if (!grow) {
            body.removeLast();
        }

        if (snakeDirection == Direction.UP) {

        } else if (snakeDirection == Direction.RIGHT) {

        } else if (snakeDirection == Direction.DOWN) {

        } else if (snakeDirection == Direction.LEFT) {

        }

        grow = false;
    }

    public Deque<Coordinate> getBody() {
        return body;
    }

    public int getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(int direction) {
        // Snake can't go in the opposite direction if it's size > 1
        if (body.size() > 1 && Direction.isOpposite(this.snakeDirection, direction)) return;
        this.snakeDirection = direction;
    }

    public void grow() {
        this.grow = true;
    }

}
