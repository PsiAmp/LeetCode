package problems;

import java.util.*;

public class P207_Course_Schedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjList = new ArrayList[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> adj = adjList[prerequisites[i][0]];
            if (adj == null) {
                adj = new ArrayList<>();
                adjList[prerequisites[i][0]] = adj;
            }

            adj.add(prerequisites[i][1]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] returned = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                boolean hasLoop = dfs(adjList, i, visited, returned);
                if (hasLoop)
                    return false;
            }
        }

        return true;
    }

    private static boolean dfs(List<Integer>[] adjList, int v, boolean[] visited, boolean[] s) {
        visited[v] = true;

        List<Integer> vx = adjList[v];
        if (vx != null) {
            for (int vs : vx) {
                if (!visited[vs]) {
                    boolean hasLoop = dfs(adjList, vs, visited, s);
                    if (hasLoop)
                        return true;
                } else if (!s[vs]) {
                    return true;
                }
            }
        }

        s[v] = true;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0,1}}));
    }
}
