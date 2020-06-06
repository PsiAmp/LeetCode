package snake;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SnakeTheGame extends JPanel {
    private static final int rectangleSize = 10;
    private GameBoard board;
    private Snake snake;
    private KeyManager keyManager;

    public SnakeTheGame() {
        board = new GameBoard(40, 40);
        snake = new Snake(board.getWidth(), board.getHeight());
        board.initFood(snake.getBody());
        keyManager = new KeyManager();

        setPreferredSize(new Dimension(board.getWidth() * rectangleSize, board.getHeight() * rectangleSize));
        setSize(board.getWidth() * rectangleSize, board.getHeight() * rectangleSize);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0xc0d41b));
        g.fillRect(0, 0, getWidth(), getHeight());

        int i = 0;
        for (Coordinate snakeElement : snake.getBody()) {
            g.setColor(new Color(47, 59,13, 255 - 255*i/snake.getBody().size()/2));
            g.drawRect(snakeElement.x * rectangleSize, snakeElement.y * rectangleSize, rectangleSize, rectangleSize);
            i++;
        }

        g.setColor(new Color(0x2f3b0d));
        Coordinate food = board.getFood();
        int fX = food.x * rectangleSize;
        int fY = food.y * rectangleSize;
        g.drawLine(fX, fY, fX + rectangleSize, fY + rectangleSize);
        g.drawLine(fX + rectangleSize, fY, fX, fY + rectangleSize);
    }

    public void update() {
        snake.setSnakeDirection(keyManager.getDirection());

        // Eat food
        if (snake.getBody().contains(board.getFood())) {
            snake.grow();
            board.setFood(null);
        }

        snake.update();
        Coordinate snakeHead = snake.getBody().peek();

        // Reached the end of the world
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x > board.getWidth() - 1 || snakeHead.y > board.getHeight() - 1) {
            snake = new Snake(board.getWidth(), board.getHeight());
            board.initFood(snake.getBody());
        }

        // Check self collision
        for (Coordinate snakeElement : snake.getBody()) {
            if (snakeElement != snakeHead) {
                if (snakeElement.equals(snakeHead)) {
                    snake = new Snake(board.getWidth(), board.getHeight());
                    board.initFood(snake.getBody());
                    break;
                }
            }
        }

        if (board.getFood() == null) {
            board.initFood(snake.getBody());
        }
    }

    public static void main(String[] args) {
        SnakeTheGame snakeTheGame = new SnakeTheGame();

        JFrame frame = new JFrame("Snake");
        frame.setLayout(new BorderLayout());
        frame.add(snakeTheGame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(snakeTheGame.getKeyManager());

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            snakeTheGame.update();
            snakeTheGame.repaint();
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
}
