import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFSTag {
    /**
     * 求组合数 -medium
     * 示例：
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);

        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        dfs(candidates, result, stack, 0, sum, target);
        return result;
    }

    private void dfs(int[] candidates, List<List<Integer>> result, Stack<Integer> stack, int begin, int sum, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if ((sum + candidates[i]) > target) break;

            sum += candidates[i];
            stack.push(candidates[i]);
            dfs(candidates, result, stack, i, sum, target);
            sum -= stack.pop();
        }
    }

    /**
     * 组合数2 -medium
     * 示例：
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;

        Arrays.sort(candidates);
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        dfs2(candidates, target, sum, result, stack, 0);
        return result;
    }

    private void dfs2(int[] candidates, int target, int sum, List<List<Integer>> result, Stack<Integer> stack, int begin) {
        if (sum == target) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (candidates[i] + sum > target) break;

            if (i > begin && candidates[i] == candidates[i - 1]) continue;

            stack.push(candidates[i]);
            sum += candidates[i];
            dfs2(candidates, target, sum, result, stack, i + 1);

            sum -= stack.pop();
        }
    }


}
