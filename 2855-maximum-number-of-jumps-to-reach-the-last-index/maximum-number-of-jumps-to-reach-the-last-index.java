class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];

        // Use a very small value to represent unreachable
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MIN_VALUE / 2;
        }

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] < 0) continue; // unreachable
            for (int j = i + 1; j < n; j++) {
                long diff = (long) nums[j] - nums[i];
                if (Math.abs(diff) <= (long) target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[n - 1] < 0 ? -1 : dp[n - 1];
    }
}