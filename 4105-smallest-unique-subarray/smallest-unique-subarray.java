import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int max = 2 * n;

        int[] len = new int[max];
        int[] link = new int[max];
        int[] count = new int[max];

        List<Map<Integer, Integer>> next = new ArrayList<>();

        next.add(new HashMap<>());
        link[0] = -1;

        int size = 1;
        int last = 0;

        for (int num : nums) {
            int cur = size++;
            next.add(new HashMap<>());

            len[cur] = len[last] + 1;
            count[cur] = 1;

            int p = last;

            while (p != -1 && !next.get(p).containsKey(num)) {
                next.get(p).put(num, cur);
                p = link[p];
            }

            if (p == -1) {
                link[cur] = 0;
            } else {
                int q = next.get(p).get(num);

                if (len[p] + 1 == len[q]) {
                    link[cur] = q;
                } else {
                    int clone = size++;

                    next.add(new HashMap<>(next.get(q)));
                    len[clone] = len[p] + 1;
                    link[clone] = link[q];

                    while (p != -1 &&
                           next.get(p).getOrDefault(num, -1) == q) {
                        next.get(p).put(num, clone);
                        p = link[p];
                    }

                    link[q] = clone;
                    link[cur] = clone;
                }
            }

            last = cur;
        }

        int[] freq = new int[n + 1];
        int[] order = new int[size];

        for (int i = 0; i < size; i++) {
            freq[len[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            freq[i] += freq[i - 1];
        }

        for (int i = size - 1; i >= 0; i--) {
            order[--freq[len[i]]] = i;
        }

        for (int i = size - 1; i > 0; i--) {
            int v = order[i];
            count[link[v]] += count[v];
        }

        int ans = n;

        for (int i = 1; i < size; i++) {
            if (count[i] == 1) {
                ans = Math.min(ans, len[link[i]] + 1);
            }
        }

        return ans;
    }
}