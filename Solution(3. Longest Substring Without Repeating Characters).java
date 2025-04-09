
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

      
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
      
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

     
        ListNode current = prev.next;
        ListNode next = null;

        for (int i = 0; i < right - left; i++) {
            next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }
}
