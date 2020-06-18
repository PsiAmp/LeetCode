package problems;

import java.util.Stack;

public class BasicCalculator2_227 {

    public int calculate(String s) {
        int result = 0;

//        Stack<Integer> nums = new Stack<>();
//        Integer num = 0;
//        char sign = '+';
//
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            char c = chars[i];
//            if (Character.isDigit(c)) {
//                num = 10 * num + c - '0';
//            }
//
//            if ()
//            if (sign == '+') {
//                nums.push(num);
//            } else if (sign == '-') {
//                nums.push(-num);
//            } else if (sign == '*') {
//
//            }
//        }

        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        char[] chars = s.toCharArray();
        int currNum = 0;
        for (char c : chars) {
            if (c == ' ')
                continue;

            if (Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
            } else {
                if (ops.peek() == '*' || ops.peek() == '/') {
                    Character op = ops.pop();
                    if (op == '*') {
                        nums.push(nums.pop() * currNum);
                    } else {
                        nums.push(nums.pop() / currNum);
                    }
                } else if (ops.peek() == '-') {
                    ops.pop();
                    currNum = -currNum;
                }

                if (c == '-') {
                    ops.push('-');
                } else if (c == '+') {
                    ops.push('+');
                }

                nums.push(currNum);
                currNum = 0;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        BasicCalculator2_227 basicCalculator2_227 = new BasicCalculator2_227();
        int calculate = basicCalculator2_227.calculate("0*1");
        System.out.println(calculate);
    }
}
