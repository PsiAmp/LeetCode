package problems;

public class RotateList61 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        if (k == 0) return head;

        int length = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            length++;
        }

        if (length == 1) return head;
        k %= length;
        if (k == 0) return head;

        ListNode current = head;

        int i = 1;
        while (i < length-k) {
            current = current.next;
            i++;
        }

        ListNode result = current.next;
        current.next = null;
        last.next = head;

        return result;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        new RotateList61().rotateRight(l, 2);
    }

}
