package interview;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    public static int substringIndex(String a, String b) {
        if (a.length() < b.length())
            return -1;

        if (b.length() == 0)
            return 0;

        if (a.length() == b.length())
            if (a.equals(b))
                return 0;
            else
                return -1;

        int bh = b.hashCode();
        int ah = a.substring(0, b.length()).hashCode();

        char[] arr = a.toCharArray();
        for (int i = 0; i <= a.length() - b.length(); i++) {
            if (ah == bh && a.substring(i, i + b.length()).equals(b)) {
                return i;
            }
            if (i + b.length() < a.length())
                ah = (ah - (arr[i])*(int)Math.pow(31, b.length()-1))*31 + arr[i + b.length()];
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(substringIndex("abc", "abc"));
    }
}
