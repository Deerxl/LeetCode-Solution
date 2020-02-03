import java.util.Hashtable;

public class LinkeListTag {
    /**
     * 链表，求两个链表的和 -medium
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = new ListNode(0);
        temp = head;
        int flag = 0;
        while (l1 != null || l2 != null) {
            temp.val += l1.val + l2.val;
            if (flag == 1) {
                temp.val += 1;
                flag = 0;
            }
            if (temp.val > 9) {
                flag = 1;
                temp.val -= 10;
            }
            if (l1.next != null)
                l1 = l1.next;
            if (l2.next != null)
                l2 = l2.next;
            temp.next = new ListNode(flag);
            temp = temp.next;
        }
        return head;

        /**
         * 方法二
         *
         *         ListNode h = new ListNode(0);
         *         ListNode tmp = new ListNode(0);
         *         h = tmp;
         *         int flag = 0; //进位
         *         while (l1 != null && l2 != null) {
         *             tmp.next = new ListNode((l1.val + l2.val + flag) % 10);
         *
         *             flag = (l1.val + l2.val + flag) / 10;
         *
         *             l1 = l1.next;
         *             l2 = l2.next;
         *             tmp = tmp.next;
         *         }
         *
         *         while (l1 != null && l2 == null) {
         *             tmp.next = new ListNode((l1.val + flag) % 10);
         *
         *             flag = (l1.val + flag) / 10;
         *             l1 = l1.next;
         *             tmp = tmp.next;
         *
         *         }
         *         while (l2 != null && l1 == null) {
         *             tmp.next = new ListNode((l2.val + flag) % 10);
         *
         *             flag = (l2.val + flag) / 10;
         *             l2 = l2.next;
         *             tmp = tmp.next;
         *         }
         *
         *         if (flag == 1) {
         *             tmp.next = new ListNode(flag);
         *             tmp = tmp.next;
         *         }
         *
         *         return h.next;
         */

    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}