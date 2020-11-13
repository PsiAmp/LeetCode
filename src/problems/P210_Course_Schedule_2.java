package problems;

import java.util.*;

public class P210_Course_Schedule_2 {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjList = new ArrayList[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> l = adjList[prerequisites[i][0]];
            if (l == null) {
                l = new ArrayList<>();
                adjList[prerequisites[i][0]] = l;
            }

            l.add(prerequisites[i][1]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] returned = new boolean[numCourses];
        List<Integer> s = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i])
                if (dfs(adjList, i, visited, returned, s))
                    return new int[0];
        }

        int[] result = new int[s.size()];
        int i = 0;
        for (Integer v : s) {
            result[i++] = v;
        }
        return result;
    }

    private static boolean dfs(List<Integer>[] adjList, int v, boolean[] visited, boolean[] returned, List<Integer> s) {
        visited[v] = true;

        List<Integer> l = adjList[v];
        if (l != null) {
            for (int vx : l) {
                if (!visited[vx]) {
                    if (dfs(adjList, vx, visited, returned, s))
                        return true;
                } else if (!returned[vx]){
                    return true;
                }
            }
        }

        returned[v] = true;
        s.add(v);

        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}, {0, 1}})));
    }
}
