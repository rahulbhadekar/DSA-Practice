import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums[nums.length - 1] + 2];
        int ans = 0;

        for (int x : nums) {
            // Increase current element by 1
            dp[x + 1] = dp[x] + 1;

            // Keep current element unchanged
            dp[x] = dp[x - 1] + 1;

            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }

        return ans;
    }
}