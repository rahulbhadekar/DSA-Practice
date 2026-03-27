class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        k = k % n; // important

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int newIndex;

                if (i % 2 == 0) {
                    // even row → left shift
                    newIndex = (j + k) % n;
                } else {
                    // odd row → right shift
                    newIndex = (j - k + n) % n;
                }

                if (mat[i][j] != mat[i][newIndex]) {
                    return false;
                }
            }
        }

        return true;
    }
}