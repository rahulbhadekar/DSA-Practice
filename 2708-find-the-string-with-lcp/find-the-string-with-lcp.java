import java.util.*;

class Solution {

    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa != pb) parent[pb] = pa;
        }
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Basic validation
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
            }
        }

        // Step 2: DSU to group equal characters
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] > 0) {
                    dsu.union(i, j);
                }
            }
        }

        // Step 3: Assign characters
        char[] word = new char[n];
        Map<Integer, Character> map = new HashMap<>();
        char ch = 'a';

        for (int i = 0; i < n; i++) {
            int p = dsu.find(i);
            if (!map.containsKey(p)) {
                if (ch > 'z') return "";
                map.put(p, ch++);
            }
            word[i] = map.get(p);
        }

        // Step 4: Validate LCP
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
            }
        }

        // Compare
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != lcp[i][j]) return "";
            }
        }

        return new String(word);
    }
}