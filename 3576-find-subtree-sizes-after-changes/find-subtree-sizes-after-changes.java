import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] newParent = parent.clone();
        int[] previous = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (node >= 0) {
                int c = s.charAt(node) - 'a';

                previous[node] = last[c];

                if (last[c] != -1) {
                    newParent[node] = last[c];
                }

                last[c] = node;

                stack.push(~node);

                for (int child : tree[node]) {
                    stack.push(child);
                }
            } else {
                node = ~node;

                int c = s.charAt(node) - 'a';
                last[c] = previous[node];

                if (newParent[node] != -1) {
                    ans[newParent[node]] += ans[node];
                }
            }
        }

        return ans;
    }
}