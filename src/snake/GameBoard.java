package snake;

import java.util.Deque;
import java.util.Random;

public class GameBoard {

    private int width;
    private int height;
    private int[][] board;
    private Coordinate food;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[width][height];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Coordinate getFood() {
        return food;
    }

    public void initFood(Deque<Coordinate> snakeBody) {
        food = generateFood(snakeBody);
    }

    public void eatFood(Deque<Coordinate> snakeBody) {
        food = generateFood(snakeBody);
    }

    private Coordinate generateFood(Deque<Coordinate> snakeBody) {
        int availabelSlots = width*height - snakeBody.size();
        Random r = new Random();
        int foodShift = r.nextInt(availabelSlots) + 1;
        Coordinate foodPosition = new Coordinate(0, 0);

        while (foodShift > 0) {
            if (!snakeBody.contains(foodPosition)) {
                foodShift--;
                // TODO check how to simplify
                if (foodShift == 0) break;
            }
            foodPosition.x++;
            if (foodPosition.x >= width) {
                foodPosition.x = 0;
                foodPosition.y++;
            }
        }
        return foodPosition;
    }

    public void setFood(Coordinate food) {
        this.food = food;
    }
}
