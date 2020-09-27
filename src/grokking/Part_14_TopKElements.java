package grokking;

import java.util.*;

public class Part_14_TopKElements {

    static class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distFromOrigin() {
            // ignoring sqrt
            return (x * x) + (y * y);
        }
    }

    /**
     * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
     */
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {
            if (pq.size() < k)
                pq.add(num);
            else if (pq.peek() < num) {
                pq.poll();
                pq.add(num);
            }
        }

        return new ArrayList<>(pq);
    }

    /**
     * Given an unsorted array of numbers, find Kth smallest number in it.
     * Time complexity: O(k*log(k) + (n-k)*log(k))
     * Space complexity: O(k)
     */
    public static int findKthSmallestNumber(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>(k, Comparator.reverseOrder());

        for (int i = 0; i < k; i++) {
            q.add(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] < q.peek()) {
                q.poll();
                q.add(nums[i]);
            }
        }

        return q.peek();
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap();

        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> q = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return m.get(o1) - m.get(o2);
            }
        });

        Set<Integer> keySet = m.keySet();
        for (Integer key : keySet) {
            if (q.size() < k)
                q.add(key);
            else if (m.get(q.peek()) < m.get(key)) {
                q.poll();
                q.add(key);
            }
        }

        int[] result = new int[q.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = q.poll();
        }
        return result;
    }

    /**
     * Given an array of points in the a 2D2D2D plane, find ‘K’ closest points to the origin.
     */
    public static List<Point> findClosestPoints(Point[] points, int k) {
        ArrayList<Point> result = new ArrayList<>();
        Queue<Point> q = new PriorityQueue<>(k, (a, b) -> b.distFromOrigin() - a.distFromOrigin());

        for (Point point : points) {
            if (q.size() < k)
                q.add(point);
            else if (q.peek().distFromOrigin() > point.distFromOrigin()) {
                q.poll();
                q.add(point);
            }
        }

        return new ArrayList<>(q);
    }

    /**
     * Given ‘N’ ropes with different lengths, we need to connect these ropes into one big rope with minimum cost.
     * The cost of connecting two ropes is equal to the sum of their lengths.
     */
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        Queue<Integer> q = new PriorityQueue<>(ropeLengths.length);
        for (int rope : ropeLengths) {
            q.add(rope);
        }

        int len = 0;
        while (q.size() > 1) {
            int l = q.poll() + q.poll();
            q.add(l);
            len += l;
        }

        return len;
    }

    /**
     * Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.
     */
    public static int[] findTopKFrequentNumbers(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] b = new List[nums.length];
        for (Integer key : frequencyMap.keySet()) {
            Integer freq = frequencyMap.get(key);
            if (b[freq] == null)
                b[freq] = new ArrayList<>();
            b[freq].add(key);
        }

        List<Integer> topNumbers = new ArrayList<>(k);
        for (int i = b.length - 1; i >= 0; i--) {
            if (b[i] != null) {
                for (Integer key : b[i]) {
                    topNumbers.add(key);
                    if (topNumbers.size() == k)
                        return topNumbers.stream().mapToInt(ii -> ii).toArray();
                }
            }
        }
        return topNumbers.stream().mapToInt(ii -> ii).toArray();
    }

    /**
     * Given a string, sort it based on the decreasing frequency of its characters.
     */
    public static String sortCharacterByFrequency(String str) {
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            m.put(str.charAt(i), m.getOrDefault(str.charAt(i), 0) + 1);
        }

        PriorityQueue<Character> q = new PriorityQueue<>((a, b) -> m.get(b) - m.get(a));
        for (Character character : m.keySet()) {
            q.add(character);
        }

        StringBuilder s = new StringBuilder();
        while (!q.isEmpty()) {
            Character c = q.poll();
            for (int i = 0; i < m.get(c); i++)
                s.append(c);
        }

        return s.toString();
    }

    public static String sortCharacterByFrequencyWithCharmap(String str) {
        int[] charMap = new int[128];
        for (int i = 0; i < str.length(); i++) {
            charMap[str.charAt(i)]++;
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> charMap[b] - charMap[a]);
        for (int i = 0; i < charMap.length; i++) {
            if (charMap[i] != 0)
                pq.add((char) i);
        }

        char[] s = new char[str.length()];
        int i = 0;
        while (!pq.isEmpty()) {
            Character ch = pq.poll();
            while (charMap[ch] > 0) {
                s[i] = ch;
                charMap[ch]--;
                i++;
            }
        }

        return String.valueOf(s);
    }

    /**
     * Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array.
     * Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.
     */
    public static List<Integer> findClosestElements(int[] arr, int k, Integer x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Math.abs(arr[a] - x) - Math.abs(arr[b] - x) == 0 ? b : Math.abs(arr[b] - x) - Math.abs(arr[a] - x));

        for (int i = 0; i < arr.length; i++) {
            pq.add(i);
            if (pq.size() > k)
                pq.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty())
            result.add(arr[pq.poll()]);

        Collections.sort(result);
        return result;
    }

    public static List<Integer> findClosestElementsWithBinarySearch(int[] arr, int k, int x) {
        int idx = binarySearch(arr, x);

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer x1, Integer x2) {
                int dx1 = Math.abs(arr[x1] - x);
                int dx2 = Math.abs(arr[x2] - x);

                if (dx1 == dx2)
                    return x2 - x1;

                return dx2 - dx1;
            }
        });

        int start = Math.max(idx - k, 0);
        int end = Math.min(idx + k, arr.length - 1);

        for (int i = start; i <= end; i++) {
            pq.add(i);
            if (pq.size() > k)
                pq.poll();
        }

        List<Integer> result = new ArrayList<>(pq.size());
        while (!pq.isEmpty())
            result.add(arr[pq.poll()]);

        Collections.sort(result);

        return result;
    }

    public static List<Integer> findClosestElementsWithBinarySearch2(int[] arr, int k, int x) {
        int l = binarySearch(arr, x) - 1;
        int r = l + 1;

        while (r - l <= k) {
            if (r >= arr.length || (l >= 0 && x - arr[l] <= arr[r] - x))
                l--;
            else
                r++;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = l + 1; i < r; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static List<Integer> findClosestElementsCleanBinary(int[] arr, int k, int x) {
        int l = 0;
        int r = arr.length - k;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (x - arr[mid] > arr[mid + k] - x)
                l = mid + 1;
            else
                r = mid;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = l; i < l + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static int binarySearch(int[] arr, int x) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x)
                return mid;
            if (x < arr[mid])
                r = mid - 1;
            else
                l = mid + 1;
        }

        return l;
    }

    /**
     * Given an array of numbers and a number ‘K’, we need to remove ‘K’ numbers from the array such that we are left with maximum distinct numbers.
     */
    public static int findMaximumDistinctElements(int[] nums, int k) {
        int distinctAmount = 0;

        // Frequency map:
        // Key - number from array num
        // Value - its frequency
        Map<Integer, Integer> m = new HashMap<>();

        for (int num : nums)
            m.put(num, m.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Integer key : m.keySet()) {
            int freq = m.get(key);
            if (freq == 1)
                distinctAmount++;
            else
                pq.add(freq);
        }

        while (!pq.isEmpty() && k > 0) {
            int freq = pq.poll();
            if (freq - 1 < k) {
                k -= freq - 1;
                distinctAmount += freq - 1;
            } else {
                k = 0;
            }
        }

        return Math.max(distinctAmount - k, 0);
    }

    /**
     * Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.
     *
     * @return
     */
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : nums) {
            pq.add(num);
            if (pq.size() >= k2) {
                pq.poll();
            }
        }

        int sum = 0;

        for (int i = 0; i < k2 - k1 - 1; i++) {
            sum += pq.poll();
        }

        return sum;
    }

    /**
     * Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.
     */
    public static String rearrangeString(String str) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : str.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(m.entrySet());

        StringBuilder sb = new StringBuilder(str.length());
        Map.Entry<Character, Integer> prev = null;
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> current = pq.poll();
            if (prev != null && prev.getValue() > 0)
                pq.add(prev);

            sb.append(current.getKey());
            current.setValue(current.getValue() - 1);
            prev = current;
        }

        return sb.length() == str.length() ? sb.toString() : "";
    }

    public static String rearrangeString2(String S) {
        int[] frequencyMap = new int[122-64];
        for (char c : S.toCharArray())
            frequencyMap[c-'A']++;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> frequencyMap[b] - frequencyMap[a]);

        for (int i = 0; i < frequencyMap.length; i++) {
            if (frequencyMap[i] > 0)
                pq.add(i);
        }

        StringBuilder sb = new StringBuilder(S.length());
        int previousIndex = -1;
        while (!pq.isEmpty()) {
            int charIndex = pq.poll();

            if (previousIndex != -1 && frequencyMap[previousIndex] > 0)
                pq.add(previousIndex);

            char c = (char) ('A' + charIndex);
            sb.append(c);
            frequencyMap[charIndex]--;
            previousIndex = charIndex;
        }

        return sb.length() == S.length() ? sb.toString() : "";
    }

    /**
     * Given a string and a number ‘K’, find if the string can be rearranged such that
     * the same characters are at least ‘K’ distance apart from each other.
     */
    public static String reorganizeString(String S, int k) {
        int[] frequencyMap = new int[122-64];
        for (char c : S.toCharArray())
            frequencyMap[c-'A']++;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> frequencyMap[b] - frequencyMap[a]);

        for (int i = 0; i < frequencyMap.length; i++) {
            if (frequencyMap[i] > 0)
                pq.add(i);
        }

        StringBuilder sb = new StringBuilder(S.length());
        Queue<Integer> q = new LinkedList<>();
        while (!pq.isEmpty()) {
            int charIndex = pq.poll();
            sb.append((char) ('A' + charIndex));
            frequencyMap[charIndex]--;
            q.add(charIndex);

            if (q.size() == k) {
                Integer prev = q.poll();
                if (frequencyMap[prev] > 0)
                    pq.add(prev);
            }
        }

        return sb.length() == S.length() ? sb.toString() : "";
    }

    /**
     * You are given a list of tasks that need to be run, in any order, on a server.
     * Each task will take one CPU interval to execute but once a task has finished, it has a cooling period during which it can’t be run again.
     * If the cooling period for all tasks is ‘K’ intervals, find the minimum number of CPU intervals that the server needs to finish all tasks.
     * If at any time the server can’t execute any task then it must stay idle.
     */
    public static int scheduleTasks(char[] tasks, int n) {
        int[] freqMap = new int[26];
        for (char t : tasks)
            freqMap[t - 'a']++;

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> freqMap[b] - freqMap[a]);
        for (int i = 0; i < freqMap.length; i++) {
            if (freqMap[i] > 0)
                maxPQ.add(i);
        }

        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        while (!maxPQ.isEmpty()) {
            Integer taskIndex = maxPQ.poll();
            freqMap[taskIndex]--;
            time++;
            if (freqMap[taskIndex] > 0)
                q.add(taskIndex);

            if (q.size() > n) {
                Integer t = q.poll();
                if (freqMap[t] > 0)
                    maxPQ.add(t);
            }
        }

        while (!q.isEmpty()) {
            time += n + 1 - q.size();
            Integer t = q.poll();
            freqMap[t]--;
            if (freqMap[t] > 0)
                q.add(t);
        }

        return time;
    }

    static class Element {
        int value;
        int order;
        int frequency;

        public Element(int value, int order, int frequency) {
            this.value = value;
            this.order = order;
            this.frequency = frequency;
        }
    }

    static class FrequencyStack {
        PriorityQueue<Element> maxPQ;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int order = 0;

        public FrequencyStack() {
             maxPQ = new PriorityQueue<>(new Comparator<Element>() {
                 @Override
                 public int compare(Element a, Element b) {
                     if (a.frequency == b.frequency)
                         return b.order - a.order;
                     return b.frequency - a.frequency;
                 }
             });
        }

        public void push(int num) {
            int freq = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, freq);
            maxPQ.add(new Element(num, order, freq));
            order++;
        }

        public int pop() {
            Element element = maxPQ.poll();
            if (element.frequency > 1)
                frequencyMap.put(element.value, element.frequency-1);
            else
                frequencyMap.remove(element.value);

            return element.value;
        }

    }

    static class FrequencyStack2 {

        private Map<Integer, Integer> keyToFrequencyMap;
        private Map<Integer, Stack<Integer>> frequencyToKeysMap;
        private int maxFrequency;

        public FrequencyStack2() {
            keyToFrequencyMap = new HashMap<>();
            frequencyToKeysMap = new HashMap<>();
            maxFrequency = 0;
        }

        public void push(int num) {
            int frequency = keyToFrequencyMap.getOrDefault(num, 0) + 1;
            keyToFrequencyMap.put(num, frequency);
            if (!frequencyToKeysMap.containsKey(frequency))
                frequencyToKeysMap.put(frequency, new Stack<>());
            frequencyToKeysMap.get(frequency).add(num);
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        public int pop() {
            int num = frequencyToKeysMap.get(maxFrequency).pop();
            keyToFrequencyMap.put(num, maxFrequency-1);
            if (frequencyToKeysMap.get(maxFrequency).isEmpty())
                maxFrequency--;
            return num;
        }
    }

    public static void main(String[] args) {
        FrequencyStack2 frequencyStack = new FrequencyStack2();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}
