package grokking;

import java.util.*;

public class Part_11_Subsets {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Given a set with distinct elements, find all of its distinct subsets.
     * Time complexity O(N*2^N)
     * Space complexity O(2^N)
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        findSubsetsHelper(subsets, new ArrayList<>(), nums, 0);
        return subsets;
    }

    public static void findSubsetsHelper(List<List<Integer>> subsets, List<Integer> curr, int[] nums, int start) {
        subsets.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            findSubsetsHelper(subsets, curr, nums, i+1);
            curr.remove(curr.size()-1);
        }
    }

    public static List<List<Integer>> findSubsetsNonRecursive(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();

        subsets.add(new ArrayList<>());

        for (int num : nums) {
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(num);
                subsets.add(set);
            }
        }

        return subsets;
    }

    /**
     * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
     */
    public static List<List<Integer>> findSubsetWithDuplicates(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        subsets.add(new ArrayList<>());

        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            start = 0;
            if (i > 0 && nums[i] == nums[i-1]) {
                start = end+1;
            }
            end = subsets.size()-1;

            for (int j = start; j <= end; j++) {
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }

        return subsets;
    }

    /**
     * Given a set of distinct numbers, find all of its permutations.
     * Permutation is defined as the re-arranging of the elements of the set.
     * For example, {1, 2, 3} has the following six permutations:
     *
     * Time complexity: O(N*N!)
     * Space complexity: O(N*N!)
     */
    public static List<List<Integer>> findPermutationsNonRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());

        for (int num : nums) {
            int n = permutations.size();
            for (int i = 0; i < n; i++) {
                List<Integer> permutation = permutations.poll();
                for (int j = 0; j <= permutation.size(); j++) {
                    List<Integer> newPerm = new ArrayList<>(permutation);
                    newPerm.add(j, num);
                    if (newPerm.size() == nums.length)
                        result.add(newPerm);
                    else
                        permutations.add(newPerm);
                }
            }
        }

        return result;
    }

    public static List<List<Integer>> findPermutationsRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        findPermutationsHelper(nums, result, new ArrayList<>());
        return result;
    }

    public static void findPermutationsHelper(int[] nums, List<List<Integer>> perms, List<Integer> current) {
        if (current.size() == nums.length) {
            perms.add(new ArrayList<>(current));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (current.contains(nums[i]))
                    continue;
                current.add(nums[i]);
                findPermutationsHelper(nums, perms, current);
                current.remove(current.size() - 1);
                System.out.println("permutations: " + current);
            }
        }
    }

    /**
     * Given a string, find all of its permutations preserving the character sequence but changing case.
     *
     * Time complexity: O(2^N)
     * Space complexity: O(N*2^N) N-characters times number of permutations N^2
     */
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        permutations.add(str);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                c = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    String permutation = permutations.get(j);
                    char[] chars = permutation.toCharArray();
                    chars[i] = c;
                    permutations.add(new String(chars));
                }
            }
        }

        return permutations;
    }

    /**
     * For a given number ‘N’, write a function to generate all combination of ‘N’ pairs of balanced parentheses.
     */
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<>();

        Queue<ParenthesesSting> q = new LinkedList<>();
        q.add(new ParenthesesSting("", 0, 0));

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                ParenthesesSting p = q.poll();
                if (p.openBracketCount == num && p.closeBracketCount == num)
                    result.add(p.s);
                else {
                    if (p.openBracketCount < num)
                        q.add(new ParenthesesSting(p.s + "(", p.openBracketCount+1, p.closeBracketCount));
                    if (p.closeBracketCount < p.openBracketCount)
                        q.add(new ParenthesesSting(p.s + ")", p.openBracketCount, p.closeBracketCount+1));
                }
            }
        }

        return result;
    }

    public static class ParenthesesSting {
        String s;
        int openBracketCount;
        int closeBracketCount;

        public ParenthesesSting(String s, int openBracketCount, int closeBracketCount) {
            this.s = s;
            this.openBracketCount = openBracketCount;
            this.closeBracketCount = closeBracketCount;
        }
    }

    public static List<String> generateParenthesisRecursive(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisRecursiveHelper(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    public static void generateParenthesisRecursiveHelper(List<String> result, StringBuilder sb, int openCount, int closeCount, int n) {
        if (openCount == n && closeCount == n) {
            result.add(sb.toString());
        } else {
            if (openCount < n) {
                sb.append('(');
                generateParenthesisRecursiveHelper(result, sb, openCount+1, closeCount, n);
                sb.deleteCharAt(sb.length()-1);
            }
            if (closeCount < openCount) {
                sb.append(')');
                generateParenthesisRecursiveHelper(result, sb, openCount, closeCount+1, n);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    /**
     * Given a word, write a function to generate all of its unique generalized abbreviations.
     *
     * Generalized abbreviation of a word can be generated by replacing each substring of the word by the count of characters in the substring.
     * Take the example of “ab” which has four substrings: “”, “a”, “b”, and “ab”.
     * After replacing these substrings in the actual word by the count of characters we get all the generalized abbreviations: “ab”, “1b”, “a1”, and “2”.
     */
    public static List<String> generateGeneralizedAbbreviation(String word) {
        List<String> result = new ArrayList<>();
        generateGeneralizedAbbreviationRecursive(word, result, new StringBuilder(), 0, 0);
        return result;
    }

    public static void generateGeneralizedAbbreviationRecursive(String word, List<String> result, StringBuilder abbreviation, int start, int count) {
        if (start == word.length()) {
            if (count != 0)
                abbreviation.append(count);
            result.add(abbreviation.toString());
        } else {
            generateGeneralizedAbbreviationRecursive(word, result, new StringBuilder(abbreviation), start+1, count+1);

            if (count != 0)
                abbreviation.append(count);

            generateGeneralizedAbbreviationRecursive(word, result, new StringBuilder(abbreviation).append(word.charAt(start)), start+1, 0);
        }
    }

    /**
     * Given an expression containing digits and operations (+, -, *), find all possible ways in which the expression can be evaluated by grouping the numbers and operators using parentheses.
     */
    static Map<String, List<Integer>> memMap = new HashMap<>();
    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        if (memMap.containsKey(input))
            return memMap.get(input);

        List<Integer> result = new ArrayList<>();

        if (!input.contains("+") && !input.contains("*") && !input.contains("-")) {
            result.add(Integer.parseInt(input));
        } else {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (!Character.isDigit(c)) {
                    List<Integer> leftResults = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> rightResults = diffWaysToEvaluateExpression(input.substring(i + 1));

                    for (Integer leftResult : leftResults) {
                        for (Integer rightResult : rightResults) {
                            if (c == '+') {
                                result.add(leftResult + rightResult);
                            } else if (c == '-') {
                                result.add(leftResult - rightResult);
                            } else {
                                result.add(leftResult * rightResult);
                            }
                        }
                    }
                }
            }
        }
        memMap.put(input, result);
        return result;
    }

    /**
     * Given a number ‘n’, write a function to return all structurally unique Binary Search Trees (BST) that can store values 1 to ‘n’?
     */
    public static List<TreeNode> findUniqueTrees(int n) {
        if (n <= 0)
            return new ArrayList<>();
        return findUniqueTreesRecursive(1, n);
    }

    public static List<TreeNode> findUniqueTreesRecursive(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = findUniqueTreesRecursive(start, i - 1);
            List<TreeNode> right = findUniqueTreesRecursive(i+1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }

        return result;
    }

    /**
     * 47. Permutations II
     * https://leetcode.com/problems/permutations-ii/
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        Arrays.sort(nums);
        perm(nums, permutations, new ArrayList(), new boolean[nums.length]);
        return permutations;
    }

    private static void perm(int[] nums, List<List<Integer>> permutations, List<Integer> templist, boolean[] visited) {
        if (templist.size() == nums.length) {
            permutations.add(new ArrayList(templist));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i] || i > 0 && nums[i] == nums[i-1] && !visited[i-1])
                    continue;

                visited[i] = true;
                templist.add(nums[i]);
                perm(nums, permutations, templist, visited);
                templist.remove(templist.size()-1);
                visited[i] = false;
            }
        }
    }

    /**
     * Given a number ‘n’, write a function to return the count of
     * structurally unique Binary Search Trees (BST) that can store values 1 to ‘n’.
     */
    private static Map<Integer, Integer> m = new HashMap<>();
    public static int countTrees(int n) {
        if (n <= 1)
            return 1;
        if (m.containsKey(n))
            return m.get(n);

        int count = 0;
        for (int i = 1; i <= n; i++) {
            int l = countTrees(i-1);
            int r = countTrees(n-i);
            count += (l*r);
        }
        m.put(n, count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }
}
