package problems;

import java.util.ArrayList;
import java.util.List;

public class P0784_LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        int n = s.length();
        char[] str = s.toCharArray();

        int counter = 0;

        for (char c : str) {
            if (Character.isAlphabetic(c))
                counter++;
        }

        int permNum = 1 << counter;

        char[][] res = new char[permNum][];
        for (int i = 0; i < permNum; i++) {
            res[i] = s.toCharArray();
        }

        // a tst.A
        // ab Ab aB AB
        // abc Abc aBc ABc abC AbC aBC ABC
        int step = 1;
        for (int i = 0; i < n; i++) {
            if (Character.isAlphabetic(str[i])) {
                char c = str[i];
                char ci = c >= 'a' ? (char)(c - 32) : (char)(c + 32);

                for (int j = step; j < permNum; j += step * 2) {
                    for (int k = 0; k < step; k++) {
                        res[j + k][i] = ci;
                    }
                }

                step = step << 1;
            }
        }

        List<String> tmp = new ArrayList<>();
        for (char[] w : res)
            tmp.add(new String(w));

        return tmp;
    }

    public static void main(String[] args) {
        P0784_LetterCasePermutation p = new P0784_LetterCasePermutation();
        p.letterCasePermutation("C");
    }
}
