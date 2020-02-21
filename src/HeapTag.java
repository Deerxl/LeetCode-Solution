import java.util.PriorityQueue;
import java.util.Queue;

public class HeapTag {

    /**
     * 合并 K个有序list -hard 分治法 堆
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int groupCount = lists.length / 2;
        int single = lists.length % 2;

        if (groupCount == 0 && single == 1) return lists[0];

        ListNode[] tmp = new ListNode[groupCount + single];

        if (groupCount + single > 0) {
            for (int i = 0; i < lists.length - single; i+=2) {
                tmp[i / 2] = (mergeTwoLists(lists[i], lists[i + 1]));
            }
            if (single == 1) tmp[groupCount] = lists[lists.length - 1];
        }

        return mergeKLists(tmp);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        else if (l2 == null) return l1;
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 寻找第 k 大的数 -medium 堆 PriorityQueue
     */
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<Integer>();

        for (int num : nums) {
            queue.add(num);

            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }
}
