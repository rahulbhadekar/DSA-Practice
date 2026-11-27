class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] freq = new int[1501];
        int[] dp = new int[budget + 1];
        int minPrice = 1501;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int gain = 0;

            for (int j = factor; j <= 1500; j += factor) {
                gain += freq[j];
            }

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + gain);
            }
        }

        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / minPrice);
        }

        return ans;
    }
}