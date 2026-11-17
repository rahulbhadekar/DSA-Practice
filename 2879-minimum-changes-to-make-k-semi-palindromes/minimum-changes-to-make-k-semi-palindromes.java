
import java.util.Arrays;

class Solution {

    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000;

        int[][] cost = new int[n][n];

        // Step 1: Precompute substring costs
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;
                int minCost = INF;

                // Find valid divisors
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group for palindrome
                    for (int start = 0; start < d; start++) {

                        int left = start;
                        int right = len - d + start;

                        while (left < right) {

                            if (s.charAt(i + left) !=
                                s.charAt(i + right)) {

                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        // Step 2: DP
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {

            for (int i = 2 * parts; i <= n; i++) {

                for (int j = 2 * (parts - 1); j <= i - 2; j++) {

                    if (dp[j][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[j][parts - 1] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
