
class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;
        int LOG = 17;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Next Greater Element
        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {

            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? n : stack[top];

            stack[++top] = i;
        }

        // Binary lifting tables
        int[][] up = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {

            up[0][i] = next[i];

            cost[0][i] =
                (long) nums[i] * (next[i] - i)
                - (prefix[next[i]] - prefix[i]);
        }

        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                int mid = up[j - 1][i];

                if (mid < n) {
                    up[j][i] = up[j - 1][mid];

                    cost[j][i] =
                        cost[j - 1][i] + cost[j - 1][mid];
                } else {
                    up[j][i] = n;
                    cost[j][i] = cost[j - 1][i];
                }
            }
        }

        // Count valid subarrays
        long ans = 0;

        for (int right = 0; right < n; right++) {

            int low = 0;
            int high = right;

            while (low < high) {

                int mid = low + (high - low) / 2;

                if (getCost(mid, right, nums, prefix,
                            up, cost, LOG) <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            ans += right - low + 1;
        }

        return ans;
    }

    private long getCost(
        int left,
        int right,
        int[] nums,
        long[] prefix,
        int[][] up,
        long[][] cost,
        int LOG
    ) {

        long total = 0;
        int current = left;

        for (int j = LOG - 1; j >= 0; j--) {

            int nxt = up[j][current];

            if (nxt <= right) {
                total += cost[j][current];
                current = nxt;
            }
        }

        // Remaining partial block
        total += (long) nums[current] * (right - current + 1)
                 - (prefix[right + 1] - prefix[current]);

        return total;
    }
}
