package sandbox;

import max.Figure;
import utils.IndexMinPQ;
import utils.PriorityQueueWithDecreaseKey;
import utils.TreeNode;
import utils.Utils;
import java.util.*;

public class Sandbox {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TODO parameter validation check

        // 1. Data structure that holds nodes sorted by x-position and by value
        // Map of x-position key to Priority Queue of node values
        Map<Integer, Queue<Integer>> positionToValues = new HashMap<>();


        // 2. Tree traversal to fill the data structure
        // DFS passing Node, x coordinate and data structure
        int minX = verticalTraversalDFS(root, 0, positionToValues);

        // 3. Converting data structure to result
        List<List<Integer>> result = new ArrayList<>();

        int x = minX;

        while (positionToValues.containsKey(x)) {
            Queue<Integer> q = positionToValues.get(x);

            List<Integer> l = new ArrayList<>();
            while (!q.isEmpty()) {
                l.add(q.poll());
            }
            result.add(l);

            x++;
        }

        return result;
    }

    private int verticalTraversalDFS(TreeNode root, int x, Map<Integer, Queue<Integer>> positionToValues) {
        if (root == null)
            return x + 1;

        Queue<Integer> q = null;
        if (positionToValues.containsKey(x)) {
            q = positionToValues.get(x);
        } else {
            q = new PriorityQueue<Integer>();
            positionToValues.put(x, q);
        }
        q.add(root.val);

        int x1 = verticalTraversalDFS(root.left, x - 1, positionToValues);
        int x2 = verticalTraversalDFS(root.right, x + 1, positionToValues);

        return Math.min(x1, x2);
    }

    public static int[] distribution(int div, int n) {
        int[] a = new int[div];

        while (n > 0)
            a[(n-- / div) % div]++;

        return a;
    }

    public static int findBestApartment(Map<String, Boolean>[] blocks, String[] requirements) {

        // DIST 2
        // BLOCKS_A 0 0 1 0 1 0 0 1

        // RESULT_A 2 1 0 1 0 1 1 0
        // RESULT_B 4 3 2 1 0 1 1 0
        // RESULT_C 2 1 0 1 0 1 1 0

        int n = blocks.length;
        int[] distances = new int[n];

        for (String req : requirements) {
            int[] currentReqDistances = new int[n];
            int currentDist = n;

            // TODO combine both for-loops into function???
            for (int i = 0; i < blocks.length; i++) {
                if (blocks[i].get(req))
                    currentDist = 0;

                currentReqDistances[i] = currentDist;
                currentDist++;
            }

            for (int i = n - 1; i >= 0; i--) {
                if (blocks[i].get(req))
                    currentDist = 0;

                currentReqDistances[i] = Math.min(currentDist, currentReqDistances[i]);
                currentDist++;
            }

            for (int i = 0; i < distances.length; i++) {
                distances[i] = Math.max(distances[i], currentReqDistances[i]);
            }
        }

        int result = n;

        for (int distance : distances) {
            result = Math.min(result, distance);
        }

        return result;
    }

    public static List<List<Integer>> allSequences() {
        TreeNode node = Utils.createTree(new int[]{2, 1, 3});

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        List<TreeNode> nodes = new ArrayList<>();



        for (List<Integer> integers : result) {
            ArrayList<Integer> clone = new ArrayList<>(integers);
        }

        return result;
    }

    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? a[0] - b[0] : a[1] - b[1]);

        int count = 0;
        int prevH = -1;
        int prevW = -1;

        for (int[] env : envelopes) {
            if (prevH < env[0] && prevW < env[1]) {
                prevH = env[0];
                prevW = env[1];
                count++;
            }
        }

        return count;
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);

            if(i < 0)
                i = -(i + 1);

            dp[i] = x;

            if(i == len)
                len++;
        }

        return len;
    }

    static class Task {
        int index;
        int enqueueTime;
        int processingTime;

        public Task(int index, int enqueueTime, int processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
    }

    // Time complexity: O(N * logN)
    // Space complexity: O(N)
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] result = new int[n];

        Task[] ts = new Task[n];

        // Init tasks
        for (int i = 0; i < n; i++)
            ts[i] = new Task(i, tasks[i][0], tasks[i][1]);

        // Sort tasks by enqueue time
        Arrays.sort(ts, (a, b) -> a.enqueueTime - b.enqueueTime);

        // Create a PQ that prioritizes tasks with smallest processing time, or if processing time is equal by task index
        Queue<Task> q = new PriorityQueue<>((a, b) -> a.processingTime != b.processingTime ? a.processingTime - b.processingTime : a.index - b.index);

        int time = ts[0].enqueueTime;
        // Index of highest available task
        int i = 0;
        // Index of processed task
        int j = 0;

        while (j < n) {
            // Adding tasks to PQ that can be processed at current time
            // In case PQ is empty, CPU is idle and we can add next task
            for (; i < n && (ts[i].enqueueTime <= time || q.isEmpty()); i++)
                q.add(ts[i]);

            // Polling next task to process
            Task task = q.poll();

            // Adding its index to results
            result[j] = task.index;
            j++;

            // "Processing" task by incrementing time by processingTime
            time += task.processingTime;
        }

        return result;
    }

    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        int[] pathLengths = new int[paths.length + 1];
        int currLen = 0;

        int max = 0;

        for (String path : paths) {
            int tabs = path.lastIndexOf("\t") + 1;

            pathLengths[tabs] = path.length() - tabs;
            if (tabs > 0)
                pathLengths[tabs] += pathLengths[tabs - 1] + 1;

            if (path.contains("."))
                max = Math.max(max, pathLengths[tabs]);
        }

        return max;
    }

    public int maximumPopulation(int[][] logs) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> logs[a][1] - logs[b][1]);
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);

        int max = 0;
        int minYear = 0;

        for (int i = 0; i < logs.length; i++) {
            pq.add(i);
            while (!pq.isEmpty() && logs[i][0] >= logs[pq.peek()][1])
                pq.poll();

            if (max < pq.size()) {
                max = pq.size();
                minYear = logs[i][0];
            }
        }

        return minYear;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int max = 0;

        for (int i = nums2.length - 1; i >= 0 ; i--) {
            int indx = bin(nums2[i], Math.min(nums1.length - 1, i), nums1);

            if (nums1[indx] < nums2[i])
                max = Math.max(max, i - indx);
            else if (nums1[indx] == nums2[i]) {
                while (indx > 0 && nums1[indx - 1] == nums2[i])
                    indx--;

                max = Math.max(max, i - indx);
            }
        }

        return max;
    }

    public int bin(int v, int r, int[] a) {
        int l = 0;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (a[mid] < v)
                 l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

    boolean[] visited;
    List<Integer>[] adj;
    Map<Integer, Map<Character, Integer>> memo;
    int maxColors = 1;

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        // Init adjacency list
        adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build adjacency list
        // Mark if vertex is child in boolean array
        Set<Integer> roots = new HashSet<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            roots.add(edge[0]);
        }

        for (int[] edge : edges) {
            roots.remove(edge[1]);
        }

        // Init visited
        visited = new boolean[n];
        memo = new HashMap<>();

        if (roots.isEmpty() && edges.length > 0)
            return -1;

        // Run DFS for all roots
        for (Integer root : roots) {
            if (dfs2(colors.toCharArray(), root))
                return -1;
        }

        return maxColors;
    }

    public boolean dfs2(char[] c, int v) {
        if (visited[v])
            return true;

        if (memo.containsKey(v))
            return false;

        visited[v] = true;

        Map<Character, Integer> charCounter = new HashMap<>();

        for (Integer w : adj[v]) {
            if (dfs2(c, w))
                return true;

            Map<Character, Integer> childCharCounter = memo.get(w);
            for (Character key : childCharCounter.keySet()) {
                int count = Math.max(charCounter.getOrDefault(key, 0), childCharCounter.get(key));
                charCounter.put(key, count);
            }
        }

        int count = charCounter.getOrDefault(c[v], 0) + 1;
        charCounter.put(c[v], count);
        maxColors = Math.max(count, maxColors);
        memo.put(v, charCounter);

        visited[v] = false;

        return false;
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.put(i, new HashSet<>());
        }

        for (int[] pair : pairs) {
            int a = pair[0];
            int b = pair[1];

            m.put(a, createConflict(b, preferences[a]));
            m.put(b, createConflict(a, preferences[b]));
        }

        int res = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> conflicts = m.get(i);

            for (Integer conflict : conflicts)
                if (m.get(conflict).contains(i)) {
                    res++;
                    break;
                }
        }

        return res;
    }

    public Set<Integer> createConflict(int pair, int[] preference) {
        Set<Integer> conflict = new HashSet<>();

        int i = preference.length - 1;
        while (i >= 0 && preference[i] != pair)
            i--;

        i--;

        while (i >= 0) {
            conflict.add(preference[i]);
            i--;
        }

        return conflict;
    }

    public int minCostConnectPoints(int[][] points) {
        int res = 0;
        int n = points.length;

        int[] dist = new int[n];
        for (int i = 0; i < dist.length; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[0] = 0;
        boolean[] visited = new boolean[n];
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(n);
        pq.add(0, 0);

        while (!pq.isEmpty()) {
            int v = pq.poll();
            visited[v] = true;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int distance = Math.abs(points[v][0] - points[i][0]) + Math.abs(points[v][1] - points[i][1]);

                    if (distance < dist[i]) {
                        dist[i] = distance;
                        if (pq.contains(i))
                            pq.decreaseKey(i, distance);
                        else
                            pq.add(i, distance);
                    }
                }
            }
        }

        for (int d : dist)
            res += d;

        return res;
    }

    public boolean isTransformable(String s, String t) {
        int n = s.length();

        LinkedList<Integer>[] pos = new LinkedList[10];
        for (int i = 0; i < pos.length; i++)
            pos[i] = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            pos[num].push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int targetNum = t.charAt(i) - '0';

            if (pos[targetNum].isEmpty())
                return false;

            int sourceNumPos = pos[targetNum].pop();
            for (int j = targetNum + 1; j < 10; j++)
                if (!pos[j].isEmpty() && pos[j].peek() > sourceNumPos)
                    return false;
        }

        return true;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        // aab
        // aac
        // aaacab

        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i < s1.length(); i++)
            dp[i + 1][0] = dp[i][0] && s1.charAt(i) == s3.charAt(i);

        for (int i = 0; i < s2.length(); i++)
            dp[0][i + 1] = dp[0][i] && s2.charAt(i) == s3.charAt(i);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                char s3Char = s3.charAt(i + j - 1);

                dp[i][j] = (dp[i-1][j] && (s1.charAt(i - 1) == s3Char))
                        || (dp[i][j-1] && (s2.charAt(j - 1) == s3Char));
            }
        }

        return dp[s1.length()][s2.length()];
    }

    static class MyCalendar {

        class Booking {
            int start;
            int end;

            public Booking(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        List<Booking> bookings;

        public MyCalendar() {
            bookings = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            int i = getInsertIndex(start);
            System.out.println(i);

            if (i - 1 < bookings.size() && i - 1 >= 0 && bookings.get(i - 1).end > start)
                return false;

            bookings.add(i, new Booking(start, end));
            return true;
        }

        private int getInsertIndex(int start) {
            int l = 0;
            int r = bookings.size();

            while (l < r) {
                int mid = l + (r - l) / 2;

                Booking b = bookings.get(mid);

                if (b.start <= start)
                    l = mid + 1;
                else
                    r = mid;
            }

            return l;
        }
    }



    public int countVowelPermutation(int n) {
        int[][] m = new int[][]{
                {1, 2, 3, 4, 5},
                {2},
                {1, 3},
                {1, 2, 4, 5},
                {3, 5},
                {1}
        };

        int[][] dp = new int[6][n + 1];

        return countVowelPermutationHelper(m, dp, 0, n);
    }

    public int countVowelPermutationHelper(int[][] m, int[][] dp, int vow, int n) {
        if (n == 1)
            return m[vow].length;

        if (dp[vow][n] != 0)
            return dp[vow][n];

        int count = 0;
        for (int v : m[vow]) {
            count = (count + countVowelPermutationHelper(m, dp, v, n - 1)) % ((int)10e8 + 7);
        }

        dp[vow][n] = count;

        return count;
    }

    public int kthSmallest(int[][] matrix, int k) {
        // max_diag == min(w, h)

        int n = matrix.length;

        int d = 1;
        int td = 2 * n - 1;

        while (d < td) {
            if (d <= n) {
                if (k - d > 0)
                    k -= d;
                else
                    break;
            } else {
                if (k - (2 * n - d) > 0)
                    k -= (2 * n - d);
                else
                    break;
            }

            d++;
        }

        int i = Math.min(d - 1, n - 1);
        int j = Math.max(d - n, 0);

        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(matrix[i][j]);
        i--;
        j++;

        while (i >= 0 && j < n) {
            if (pq.size() < k) {
                pq.add(matrix[i][j]);
            } else if (matrix[i][j] < pq.peek()) {
                pq.poll();
                pq.add(matrix[i][j]);
            }
            i--;
            j++;
        }

        return pq.peek();
    }

    public TreeNode constructBinaryTreeFromString(String s) {
        return constructBinaryTreeFromString(s, new int[]{0});
    }

    public TreeNode constructBinaryTreeFromString(String s, int[] i) {
        int n = s.length();

        if (i[0] >= n)
            return null;

        boolean isNegative = false;
        if (s.charAt(i[0]) == '-') {
            isNegative = true;
            i[0]++;
        }

        int num = 0;

        while (i[0] < n && Character.isDigit(s.charAt(i[0]))) {
            num = num * 10 + (s.charAt(i[0]) - '0');
            i[0]++;
        }

        if (isNegative)
            num = -num;

        TreeNode node = new TreeNode(num);

        if (i[0] < n && s.charAt(i[0]) == '(') {
            i[0]++;
            node.left = constructBinaryTreeFromString(s, i);
        }

        if (i[0] < n && s.charAt(i[0]) == '(') {
            i[0]++;
            node.right = constructBinaryTreeFromString(s, i);
        }

        if (i[0] < n && s.charAt(i[0]) == ')')
            i[0]++;

        return node;
    }

    public static void main(String[] args) {
        Sandbox s = new Sandbox();
        TreeNode treeNode = s.constructBinaryTreeFromString("-4(2(-333)(1))(6(5))");

        s.kthSmallest(new int[][]{{1,2},{1,3}}, 3);

        System.out.println(s.countVowelPermutation(100));
        s.isInterleave("aabcc", "dbbca", "aadbbcbcac");

        MyCalendar c = new MyCalendar();
        c.book(10,20);
        c.book(15,25);
        c.book(20,30);
    }



}
