package grokking;

import java.util.*;

public class Part_02_SlidingWIndow {

    public double[] intro(int[] a, int k) {
        double[] result = new double[a.length - k + 1];
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < k) {
            sum += a[right];
            if (right >= k) {
                result[left] = sum / k;
                sum -= a[left];
                left++;
            }
            right++;
        }

        return result;
    }

    /**
     * Given an array of positive numbers and a positive number ‘k’, find the maximum sum of any contiguous subarray of size ‘k’.
     */
    public int[] maxSumSubArray(int[] a, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int maxSum = 0;
        int maxL = 0;

        while (right < k) {
            sum += a[right];

            if (right >= k) {
                if (sum > maxSum) {
                    maxSum = sum;
                    maxL = left;
                }
                sum -= a[left];
                left++;
            }
            right++;
        }

        int[] result = new int[k];
        System.arraycopy(a, maxL, result, 0, k);

        return result;
    }

    public int smallestSubarrayWithAGivenSum(int[] a, int s) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < a.length; right++) {
            sum += a[right];

            while (sum <= s) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= a[left++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * Given a string, find the length of the longest substring in it with no more than K distinct characters.
     */
    public static int longestSubstringWithLessThanKDistinctCharacters(String s, int k) {
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> charSet = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);

            int rightCharCount = charSet.containsKey(c) ? charSet.get(c) : 0;
            charSet.put(c, rightCharCount + 1);

            if (charSet.size() > k) {
                Integer leftCharCount = charSet.get(s.charAt(left));
                leftCharCount--;
                if (leftCharCount == 0)
                    charSet.remove(s.charAt(left));
                else
                    charSet.put(s.charAt(left), leftCharCount);
                left++;
            }

            right++;
        }

        return right - left;
    }

    /**
     * Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.
     * You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
     * Write a function to return the maximum number of fruits in both the baskets.
     */
    public static int fruitsToBaskets(char[] a) {
        int maxFruits = 0;
        int left = 0;
        int right = 0;
        Set<Character> baskets = new HashSet<>();

        while (right < a.length) {
            baskets.add(a[right]);

            while (baskets.size() > 2) {
                baskets.remove(a[left]);
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
            right++;
        }

        return maxFruits;
    }

    public static int noRepeatString(String s) {
        int left = 0;
        int maxLength = 0;

        HashMap<Character, Integer> charMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (charMap.containsKey(rightChar)) {
                left = charMap.get(rightChar) + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            charMap.put(rightChar, right);
        }

        return maxLength;
    }

    /**
     * Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any letter,
     * find the length of the longest substring having the same letters after replacement.
     */
    public static int longestSubstringWithSameLettersAfterReplacement(String s, int k) {
        int left = 0;
        int maxChars = 0;
        int maxLength = 0;
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            maxChars = Math.max(maxChars, charCount.get(rightChar));

            if (right - left + 1 - maxChars > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * https://leetcode.com/problems/max-consecutive-ones-iii/
     */
    public static int longestSubarrayWithOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int[] counter = new int[2];

        while (right < A.length) {
            counter[A[right]]++;

            if (counter[0] > K) {
                counter[A[left]]--;
                left++;
            }

            right++;
        }

        return right - left;
    }

    /**
     * Given a string and a pattern, find out if the string contains any permutation of the pattern.
     */
    public static boolean challenge1(String s, String p) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            charCountMap.put(p.charAt(i), charCountMap.getOrDefault(p.charAt(i), 0) + 1);
        }


        int left = 0;
        int matched = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            if (charCountMap.containsKey(rightChar)) {
                Integer rightCharCount = charCountMap.get(rightChar) - 1;
                charCountMap.put(rightChar, rightCharCount);

                if (rightCharCount == 0) {
                    matched++;
                }
            }

            if (matched == charCountMap.size()) {
                return true;
            }

            if (right >= p.length() - 1) {
                char leftChar = s.charAt(left);

                if (charCountMap.containsKey(leftChar)) {
                    Integer leftCharCount = charCountMap.get(leftChar);

                    if (leftCharCount == 0) {
                        matched--;
                    }

                    charCountMap.put(leftChar, leftCharCount + 1);
                }

                left++;
            }
        }

        return false;
    }

    /**
     * Challenge 2
     * Given a string and a pattern, find all anagrams of the pattern in the given string.
     */
    public static List<Integer> findAllAnagrams(String str, String pattern) {
        List<Integer> anagrams = new ArrayList<>();

        // vars
        int left = 0;
        int matches = 0;

        // init hashmap counter
        HashMap<Character, Integer> charCounter = new HashMap<>();
        // Calculate chars in pattern and put a value for each char
        for (char patternChar : pattern.toCharArray()) {
            charCounter.put(patternChar, charCounter.getOrDefault(patternChar, 0) + 1);
        }

        // sliding window loop
        for (int right = 0; right < str.length(); right++) {
            // if pattern includes right char decrease char counter
            char rightChar = str.charAt(right);
            if (charCounter.containsKey(rightChar)) {
                Integer rightCharCounter = charCounter.get(rightChar);
                rightCharCounter--;
                charCounter.put(rightChar, rightCharCounter);
                // if char counter is 0 - we have a match. increase matches
                if (rightCharCounter == 0) {
                    matches++;
                }
            }

            // check if we have window is fully matched
            if (matches == charCounter.size()) {
                // left index is a start of a matched pattern add left index to anagrams
                anagrams.add(left);
            }

            // check if need to drop out left element of the window
            if (right >= pattern.length() - 1) {
                char leftChar = str.charAt(left);

                if (charCounter.containsKey(leftChar)) {
                    Integer leftCharCounter = charCounter.get(leftChar);
                    // check if left char counter is 0
                    if (leftCharCounter == 0) {
                        // decrease matches
                        matches--;
                    }

                    // increase left char counter
                    leftCharCounter++;
                    charCounter.put(leftChar, leftCharCounter);
                }
                // increase left
                left++;
            }
        }

        return anagrams;
    }

    /**
     * Challenge 3
     * Given a string and a pattern, find the smallest substring in the given string which has all the characters of the given pattern.
     */
    public static String findSmallestSubtring(String s, String t) {
        if (s.length() < t.length()) return "";
        if (s.equals(t)) return s;

        int left = 0;
        int matches = 0;
        int minLength = s.length();
        int minLenIndex = -1;
        HashMap<Character, Integer> charCounter = new HashMap<>();

        for (char patternChar : t.toCharArray()) {
            charCounter.put(patternChar, charCounter.getOrDefault(patternChar, 0) + 1);
        }

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (charCounter.containsKey(rightChar)) {
                Integer rightCharCounter = charCounter.get(rightChar);
                rightCharCounter--;
                charCounter.put(rightChar, rightCharCounter);
                if (rightCharCounter == 0)
                    matches++;
            }

            while (matches == charCounter.size()) {
                char leftChar = s.charAt(left);
                if (charCounter.containsKey(leftChar)) {
                    Integer leftCharCounter = charCounter.get(leftChar);

                    if (leftCharCounter == 0) {
                        matches--;
                        if (right - left < minLength) {
                            minLength = right - left;
                            minLenIndex = left;
                        }
                    }

                    leftCharCounter++;
                    charCounter.put(leftChar, leftCharCounter);
                }
                left++;
            }
        }

        return minLenIndex == -1 ? "" : s.substring(minLenIndex, minLenIndex + minLength + 1);
    }

    /**
     * Challenge 3
     * Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
     */
    public static int minimumWindowSort(int[] a) {
        int left = 0;
        int right = a.length-1;

        while (left < a.length-1 && a[left] <= a[left+1])
            left++;

        if (left == a.length-1)
            return 0;

        while (a[right] > 0 && a[right] >= a[right-1])
            right--;

        int minSub = Integer.MAX_VALUE;
        int maxSub = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) {
            minSub = Math.min(minSub, a[i]);
            maxSub = Math.max(maxSub, a[i]);
        }

        while (left > 0 && a[left-1] > minSub)
            left--;

        while (right < a.length-1 && a[right+1] < maxSub)
            right++;

        return right - left+1;
    }

    public static void main(String[] args) {
        System.out.println(findSmallestSubtring("abc", "ac"));
        System.out.println(findSmallestSubtring("aabdec", "abc"));
        System.out.println(findSmallestSubtring("abdabca", "abc"));
        System.out.println(findSmallestSubtring("adcad", "abc"));

        System.out.println(minimumWindowSort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(minimumWindowSort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(minimumWindowSort(new int[] { 1, 2, 3 }));
        System.out.println(minimumWindowSort(new int[] { 3, 2, 1 }));

    }

}
