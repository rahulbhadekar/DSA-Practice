class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        // Total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }

        // If odd, not possible
        if (totalSum % 2 != 0) return false;

        long target = totalSum / 2;

        // 🔹 Check horizontal cut
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) { // ensure non-empty
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == target) return true;
        }

        // 🔹 Check vertical cut
        long[] colSum = new long[n];

        // Calculate column sums
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                colSum[j] += grid[i][j];
            }
        }

        long prefixCol = 0;
        for (int j = 0; j < n - 1; j++) { // ensure non-empty
            prefixCol += colSum[j];
            if (prefixCol == target) return true;
        }

        return false;
    }
}