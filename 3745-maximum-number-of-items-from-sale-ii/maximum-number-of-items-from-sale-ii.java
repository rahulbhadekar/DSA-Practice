import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int[] free = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count eligible free items for each factor
        for (int i = 1; i <= n; i++) {
            if (freq[i] == 0) continue;

            for (int j = i; j <= n; j += i) {
                free[i] += freq[j];
            }

            free[i]--;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remaining = budget;
        long ans = 0;

        // Purchase items that provide one free copy
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (price >= 2L * minPrice) break;

            long count = Math.min(
                (long) free[factor],
                remaining / price
            );

            ans += count * 2;
            remaining -= count * price;
        }

        // Spend remaining budget on the cheapest item
        ans += remaining / minPrice;

        return (int) ans;
    }
}