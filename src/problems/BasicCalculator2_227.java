package problems;

import java.util.Stack;

public class BasicCalculator2_227 {

    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        int currentNum = 0;
        char ops = '+';

        for (int i = 0; i < s.length(); i++) {
            // Number
            if (isNum(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                currentNum = currentNum * 10 + num;

                // Operation
            } else if (s.charAt(i) == ' ') {
                if (currentNum > 0) {
                    if (isHighPriorityOps(ops)) {
                        currentNum = performOperation(nums.pop(), ops, currentNum);
                    } else if (isNegative(ops)) {
                        currentNum *= -1;
                    }
                    nums.push(currentNum);
                    currentNum = 0;
                }
            } else {
                if (currentNum > 0) {
                    if (isHighPriorityOps(ops)) {
                        currentNum = performOperation(nums.pop(), ops, currentNum);
                    } else if (isNegative(ops)) {
                        currentNum *= -1;
                    }
                    nums.push(currentNum);
                    currentNum = 0;
                }
                ops = s.charAt(i);
            }
        }
        if (currentNum > 0) {
            if (isHighPriorityOps(ops)) {
                currentNum = performOperation(nums.pop(), ops, currentNum);
            } else if (isNegative(ops)) {
                currentNum *= -1;
            }
            nums.push(currentNum);
        }

        int result = 0;

        while (!nums.isEmpty()) {
            result += nums.pop();
        }

        return result;
    }

    public int performOperation(int num1, char ops, int num2) {
        if (ops == '*') return num1 * num2;
        return num1 / num2;
    }

    public boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isHighPriorityOps(char ops) {
        return ops == '/' || ops == '*';
    }

    public boolean isNegative(char ops) {
        return ops == '-';
    }

    public static void main(String[] args) {
        BasicCalculator2_227 basicCalculator2_227 = new BasicCalculator2_227();
        int calculate = basicCalculator2_227.calculate("0*1");
        System.out.println(calculate);
    }
}
