package grokking;

public class Part_07_LinkedListReverse {

    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * Given the head of a Singly LinkedList, reverse the LinkedList.
     * Write a function to return the new head of the reversed LinkedList.
     */
    public static ListNode reverseLinkedList(ListNode head) {
        ListNode current = null;

        while (head != null) {
            ListNode t = head.next;
            head.next = current;
            current = head;
            head = t;
        }

        return current;
    }

    /**
     * Given the head of a LinkedList and two positions ‘p’ and ‘q’,
     * reverse the LinkedList from position ‘p’ to ‘q’.
     */
    public static ListNode reverseSubList(ListNode head, int m, int n) {
        if (n == m)
            return head;

        ListNode current = head;
        ListNode previous = null;
        while (m > 1 && current != null) {
            previous = current;
            current = current.next;
            m--;
            n--;
        }

        ListNode beforeSub = previous;
        ListNode firstSub = current;

        while (n > 0 && current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            n--;
        }

        if (beforeSub != null) {
            beforeSub.next = previous;
        } else {
            head = previous;
        }

        firstSub.next = current;

        return head;
    }

    /**
     * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
     * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
     */
    public static ListNode reverseEveryKElements(ListNode head, int k) {
        ListNode previous = null;
        ListNode endPrevious = null;
        ListNode current = head;

        while (current != null) {
            ListNode first = current;

            for (int i = 0; i < k && current != null; i++) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            if (endPrevious != null) {
                endPrevious.next = previous;
            } else {
                head = previous;
            }

            endPrevious = first;
        }

        endPrevious.next = null;

        return head;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode previous = null;
        ListNode first = null;
        ListNode current = head;
        ListNode prevLast = null;

        while (current != null) {
            // Remember first element
            first = current;

            int len = 0;
            ListNode curr = current;
            while (len < k && curr != null) {
                curr = curr.next;
                len++;
            }
            if (len == k) {
                // Swap elements loop
                for (int i = 0; i < k && current != null; i++) {
                    ListNode next = current.next;
                    current.next = previous;
                    previous = current;
                    current = next;
                }
            } else {
                break;
            }

            // 1 2 3 4 5 6 7 8
            // 3 2 1 6 5 4 7 8
            // Connect k-chains
            if (prevLast != null) {
                prevLast.next = previous;
            } else {
                // Get a new head after the first k-chain end
                head = previous;
            }

            prevLast = first;
        }

        if (current != null) {
            prevLast.next = first;
        } else {
            // Nullify the next element of reversed list last node
            first.next = null;
        }

        return head;
    }

    public static ListNode reverseEveryKElements2(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        ListNode previous = null;
        ListNode current = head;
        ListNode lastNodeOfCurrentSublist = null;

        while (current != null) {
            ListNode lastNodeOfPreviousPart = previous;
            lastNodeOfCurrentSublist = current;

            ListNode tmpCurr = current;
            for (int j = 0; j < k && tmpCurr != null; j++) {
                tmpCurr = tmpCurr.next;
            }
            if (tmpCurr == null)
                break;

            for (int i = 0; i < k && current != null; i++) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            if (lastNodeOfPreviousPart == null) {
                head = previous;
            } else {
                lastNodeOfPreviousPart.next = previous;
            }

            lastNodeOfCurrentSublist.next = current;

            previous = lastNodeOfCurrentSublist;
        }

        return head;
    }

    /**
     * Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized sub-list starting from the head.
     * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
     */
    public static ListNode reverseEveryKElementsAlternating(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        ListNode current = head;
        ListNode previous = null;
        ListNode previousEnd;
        ListNode currentEnd;

        while (current != null) {
            previousEnd = previous;
            currentEnd = current;

            for (int i = 0; i < k && current != null; i++) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            if (previousEnd == null) {
                head = previous;
            } else {
                previousEnd.next = previous;
            }

            currentEnd.next = current;

            for (int i = 0; i < k && current != null; i++) {
                previous = current;
                current = current.next;
            }

        }

        return head;
    }

    public static ListNode rotateList(ListNode head, int rotations) {
        ListNode last;
        ListNode current = head;
        ListNode previous = null;

        int len = 0;
        for (; current != null; len++) {
            previous = current;
            current = current.next;
        }

        int left = rotations % len;
        if (left == 0)
            return head;

        last = previous;

        current = head;
        previous = null;
        for (int j = 0; j < len - left; j++) {
            previous = current;
            current = current.next;
        }

        previous.next = null;
        last.next = head;

        return current;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = rotateList(head, 11);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
