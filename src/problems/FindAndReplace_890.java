package problems;

import java.util.*;

/**
 * You have a list of words and a pattern, and you want to know which words in words matches the pattern.
 * tst.A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
 * (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)
 * Return a list of the words in words that match the given pattern.
 * You may return the answer in any order.
 * https://leetcode.com/problems/find-and-replace-pattern/
 */
public class FindAndReplace_890 {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> results = new ArrayList<>();

        for (String word : words) {
            if (isMatched(word, pattern)) {
                results.add(word);
            }
        }

        return results;
    }

    public boolean isMatched(String word, String pattern) {
        if (word.length() != pattern.length())
            return false;

        HashMap<Character, Character> wordMap = new HashMap<>();
        HashMap<Character, Character> ppatterMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);

            if (!wordMap.containsKey(w)) {
                wordMap.put(w, p);
            }

            if (!ppatterMap.containsKey(p)) {
                ppatterMap.put(p, w);
            }

            if (wordMap.get(w) != p || ppatterMap.get(p) != w) {
                return false;
            }
        }

        return true;
    }

    public List<String> findAndReplacePattern2(String[] words, String pattern) {
        if(words == null || words.length == 0 || pattern == null) return Collections.emptyList();
        int plen = pattern.length();
        List<String> result = new ArrayList<>();

        int[] pRep = getRep(pattern, new int[plen]);
        //System.out.println(Arrays.toString(pRep));
        int[] wRep = new int[plen];
        //System.out.println(Arrays.toString(wRep));

        for(String word : words){
            if(word.length() != plen) continue;
            wRep = getRep(word, wRep);
            if(sameRep(wRep, pRep)) result.add(word);
        }

        return result;

    }

    int[] getRep(String str, int[] rep){
        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        for(int i = 0; i<str.length(); i++){
            char c = str.charAt(i);
            if(arr[c-'a'] == -1)
                arr[c-'a'] = i;
            //System.out.println(arr[c-'a']);
            rep[i] = arr[c-'a'];
        }
        return rep;
    }

    boolean sameRep(int[] w, int[] p){
        for(int i=0; i<w.length; i++){
            if(w[i]!=p[i]) return false;
        }
        return true;
    }


}
