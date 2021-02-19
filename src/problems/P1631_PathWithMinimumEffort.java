package problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class P1631_PathWithMinimumEffort {

    class PointWithHeight {
        int row;
        int column;
        int h;

        public PointWithHeight(int row, int column, int h) {
            this.row = row;
            this.column = column;
            this.h = h;
        }
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Edge {
        Point from;
        Point to;
        int height;

        public Edge(Point from, Point to, int height) {
            this.from = from;
            this.to = to;
            this.height = height;
        }
    }

//    // UF version
//    public int minimumEffortPath(int[][] heights) {
//        // Create edges
//
//
//    }

    public int minimumEffortPath1(int[][] heights) {
        Queue<PointWithHeight> q = new PriorityQueue<>((a, b) -> a.h - b.h);
        q.add(new PointWithHeight(0,0, 0));

        int h = heights.length;
        int w = heights[0].length;
        boolean[][] visited = new boolean[h][w];

        int[][] dirs = new int[][]{{0,1}, {-1, 0}, {0, -1}, {1, 0}};

        while (!q.isEmpty()) {
            PointWithHeight p = q.poll();
            visited[p.row][p.column] = true;

            if (p.row == w - 1 && p.column == h - 1)
                return p.h;

            for (int[] dir : dirs) {
                int x = p.row - dir[0];
                int y = p.column - dir[1];

                if (x >= 0 && x < w && y >= 0 && y < h) {
                    if (!visited[x][y]) {
                        q.add(new PointWithHeight(x, y, heights[x][y] - heights[p.row][p.column]));
                    }
                }
            }
        }

        return 0;
    }

    public int minimumEffortPath2(int[][] heights) {
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.add(new int[]{0, 0, 0});

        int rows = heights.length;
        int columns = heights[0].length;

        int[][] hh = new int[rows][columns];
        for (int[] ints : hh)
            Arrays.fill(ints, Integer.MAX_VALUE);
        hh[0][0] = 0;

        int[][] dirs = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int h = p[0];
            int r = p[1];
            int c = p[2];

            if (r == rows - 1 && c == columns - 1)
                return h;

            for (int[] dir : dirs) {
                int row = r + dir[1];
                int column = c + dir[0];

                if (0 <= column && column < columns && 0 <= row && row < rows) {
                    int height = Math.abs(heights[row][column] - heights[r][c]);
                    int totalHeight = Math.max(height, h);

                    if (totalHeight < hh[row][column]) {
                        hh[row][column] = totalHeight;
                        q.add(new int[]{totalHeight, row, column});
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        P1631_PathWithMinimumEffort p = new P1631_PathWithMinimumEffort();
        System.out.println(p.minimumEffortPath2(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        System.out.println(p.minimumEffortPath2(new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}));
        System.out.println(p.minimumEffortPath2(new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}}));
    }

}
