package grokking;

import java.util.HashMap;

public class Part_04_FastAndSlow {

    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }

        return false;
    }

    public static int cycleLength(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int cycleLength = 0;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                cycleLength++;
                fast = fast.next;
                while (fast != slow) {
                    fast = fast.next;
                    cycleLength++;
                }
            }
        }

        return cycleLength;
    }

    public static boolean findHappyNumber(int num) {
        int slow = num;
        int fast = getSumSq(getSumSq(num));

        while (slow != fast) {
            fast = getSumSq(getSumSq(fast));
            slow = getSumSq(slow);
        }

        return slow == 1;
    }

    public static int getSumSq(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    public static ListNode findMiddleOfLinkedList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxlen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char charR = s.charAt(right);

            if (!map.containsKey(charR)) {
                map.put(charR, right);
                maxlen = Math.max(maxlen, right - left + 1);
            } else {
                int pos = map.remove(charR);
                while (left < pos) {
                    map.remove(s.charAt(left));
                    left++;
                }

                left = pos;
                map.put(charR, right);
            }
        }

        return maxlen;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int maxLen = 0;

        // Dictionary key: character from string s value: its position in string s
        HashMap<Character, Integer> charMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            if (!charMap.containsKey(rightChar)) {
                maxLen = Math.max(maxLen, right - left + 1);
            } else {
                // abcbabc
                int charPos = charMap.get(rightChar);
                while (left <= charPos) {
                    charMap.remove(s.charAt(left));
                    left++;
                }
            }

            charMap.put(rightChar, right);
        }

        return maxLen;
    }

    public static boolean isPalindrome(ListNode head) {
        // Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // In-place reverse half of the list
        ListNode rightHalfReversed = reverseList(slow);
        // compare
        ListNode right = rightHalfReversed;
        ListNode left = head;
        boolean isPalindrome = true;
        while (right != null && left != null) {
            if (right.value != left.value) {
                isPalindrome = false;
                break;
            }
            right = right.next;
            left = left.next;
        }

        // reverse list back to original
        reverseList(rightHalfReversed);

        return isPalindrome;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }

        return previous;
    }

    public static void rearrangeList(ListNode head) {
        // Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse second half
        ListNode reversedHead = reverseList(slow);

        // rearrange nodes
        while (head != null) {
            ListNode next = head.next;
            head.next = reversedHead;
            head = reversedHead;
            reversedHead = next;
        }
    }

    public ListNode reverseList2(ListNode head) {
        ListNode previous = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }

        return previous;
    }

    public static boolean circularArrayLoop(int[] arr) {
        int fast = 0;
        int slow = 0;

        do {
            fast = getNextIndex(arr, getNextIndex(arr, fast));
            slow = getNextIndex(arr, slow);
        } while (fast != slow);

        boolean isPostive = arr[slow] >= 0;

        do {
            fast = getNextIndex(arr, fast);
            if (arr[fast] >= 0 != isPostive)
                return false;
        } while (fast != slow);

        return true;
    }

    public static int getNextIndex(int[] a, int v) {
        v += a[v];
        v %= a.length;
        if (v < 0) {
            v += a.length - 1;
        }
        return v;
    }

    public static void main(String[] args) {
        System.out.println(circularArrayLoop(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println(circularArrayLoop(new int[] { 2, 2, -1, 2 }));
        System.out.println(circularArrayLoop(new int[] { 2, 1, -1, -2 }));
    }
}
