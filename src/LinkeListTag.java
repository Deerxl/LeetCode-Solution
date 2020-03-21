import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class LinkeListTag {
    /**
     * 链表，求两个链表的和 -medium
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

    /**
     * 删除倒数第n个节点 -medium
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        int index = count - n;
        if (index == 0) return head.next;
        tmp = head;
        for (int i = 0; i < count; i++) {
            if (i == index - 1) {
                tmp.next = tmp.next.next;
                break;
            }
            tmp = tmp.next;
        }
        return head;
    }

    /**
     * 按序合并两list -easy 递归
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
        /**用l3来排序，比较麻烦
         * if (l2 == null) return l1;
         *         else if (l1 == null) return l2;
         *         ListNode l3 = new ListNode(0);
         *         ListNode tmp = l3;
         *         while (l1 != null || l2 != null) {
         *             if ((l1 != null && l2 != null && l1.val <= l2.val) || l2 == null) {
         *                 tmp.next = new ListNode(l1.val);
         *                 l1 = l1.next;
         *             } else {
         *                 tmp.next = new ListNode(l2.val);
         *                 l2 = l2.next;
         *             }
         *             tmp = tmp.next;
         *         }
         *         return l3.next;
         */

    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairs(second.next);
        second.next = first;

        return head;


        /** 用两个list分别存单双数节点序列，再进行拼接，很快，耗时大。
         *         ListNode tmp = head;
         *         ListNode l1 = new ListNode(-1);
         *         ListNode l2 = new ListNode(-1);
         *         ListNode h1 = l1, h2 = l2;
         *         ListNode result = new ListNode(-1);
         *         ListNode res = result;
         *
         *         while (tmp != null) {
         *             l1.next = new ListNode(tmp.val);
         *             l1 = l1.next;
         *             tmp = tmp.next;
         *
         *             if(tmp != null) {
         *                 l2.next = new ListNode(tmp.val);
         *                 l2 = l2.next;
         *                 tmp = tmp.next;
         *             }
         *
         *         }
         *
         *         while (h1.next != null && h2.next != null) {
         *             result.next = new ListNode(h2.next.val);
         *             h2 = h2.next;
         *             result = result.next;
         *
         *             result.next = new ListNode(h1.next.val);
         *             h1 = h1.next;
         *             result = result.next;
         *         }
         *         if (h1.next != null) {
         *             result.next = new ListNode(h1.next.val);
         *             h1 = h1.next;
         *             result = result.next;
         *         }
         *
         *         return res.next;
         */

    }

    /**
     * k个一组翻转链表 -hard
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 深拷贝链表 -medium 用hashMap来存储原节点和新节点
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dump = head;
        while (dump != null) {
            map.put(dump, new Node(dump.val));
            dump = dump.next;
        }
        dump = head;
        while (dump != null) {
            map.get(dump).next = map.get(dump.next);
            map.get(dump).random = map.get(dump.random);
            dump = dump.next;
        }
        return map.get(head);
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}